package com.suhuamo.init.generate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.suhuamo.init.util.FileUtil;
import com.suhuamo.init.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author yuanchuncheng
 * @slogan 耐心等，太阳总会升起来的。
 * @date 2023-12-31
 * @description 自动生成器工具类
 */
@Slf4j
public class GenerateUtil {

    /**
     * java文件的文件后缀
     */
    private final static String JAVA_FILE_SUFFIX = ".java";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static List<String> templatePath = new ArrayList<>();

    static {
        templatePath.add("/templates"); // 最新版
        templatePath.add("/templates/pojo"); // 只需要实体类版
        templatePath.add("/templates/stand"); // 标准版
    }

    /**
     * 自动生成代码
     *
     * @param generateProperties 自动生成代码需要的参数
     * @throws IOException
     */
    public static void generatorCode(GenerateProperties generateProperties) throws IOException {
        // 生成mybatis-plus定义的文件
        createFastAutoGenerator(generateProperties);
        // 创建其他自定义文件
        createOtherFile(generateProperties.getOutputDirJava(), generateProperties.getParent(), generateProperties.getEntity());
    }

    /**
     * 代码生成器
     *
     * @param generateProperties 基础参数
     * @return void
     */
    private static void createFastAutoGenerator(GenerateProperties generateProperties) {
        // 获取基础信息
        String url = generateProperties.getUrl();
        String username = generateProperties.getUsername();
        String password = generateProperties.getPassword();
        String author = generateProperties.getAuthor();
        String outputDirJava = generateProperties.getOutputDirJava();
        String outputDirResources = generateProperties.getOutputDirResources();
        int templateType = generateProperties.getTemplateType();
        String parent = generateProperties.getParent();
        String entity = generateProperties.getEntity();
        String mapper = generateProperties.getMapper();
        String mapperXml = generateProperties.getMapperXml();
        String service = generateProperties.getService();
        String serviceImpl = generateProperties.getServiceImpl();
        String controller = generateProperties.getController();
        List<String> tables = generateProperties.getTables();
        List<String> tablePrefix = generateProperties.getTablePrefix();
        //  开始生成
        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author(author)//设置作者名
                            .outputDir(outputDirJava)//设置文件输出路径
                            .commentDate(YYYY_MM_DD)//注释日期
                            .disableOpenDir();//运行完成不打开生成目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(parent)
                            .entity(entity)
                            .mapper(mapper)
                            //.xml(mapperXml) // 使用 pathInfo给xml文件重新指定路径了
                            .service(service)
                            .serviceImpl(serviceImpl)
                            .controller(controller)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputDirResources + "\\" + mapperXml));
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables)//需要生成的数据表，可传list
                            .addTablePrefix(tablePrefix)//忽略表前缀，可传list
                            //开启生成实体类
                            .entityBuilder()
                            .enableLombok()//开启 lombok 模型
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            //开启生成mapper
                            .mapperBuilder()
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .superClass(BaseMapper.class)//设置父类
                            .formatMapperFileName("%sMapper")//格式化 mapper 文件名称
                            .formatXmlFileName("%sMapper")//格式化 xml 实现类文件名称
                            //开启生成service及impl
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 实现类文件名称
                            //开启生成controller
                            .controllerBuilder()
                            .enableHyphenStyle()// 映射路径使用连字符格式，而不是驼峰
                            .formatFileName("%sController")//格式化文件名称
                            .enableRestStyle();
                })
                // 当未配置模板路径时，先找 templates文件下有没有对应的，没有就用 mybatis 自带的
                .templateConfig(builder -> {
                    builder.controller(templatePath.get(templateType) + "/controller.java.vm");
                    builder.entity(templatePath.get(templateType) + "/entity.java.vm");
                    builder.mapper(templatePath.get(templateType) + "/mapper.java.vm");
                    builder.xml(templatePath.get(templateType) + "/mapper.xml.vm");
                    builder.service(templatePath.get(templateType) + "/service.java.vm");
                    builder.serviceImpl(templatePath.get(templateType) + "/serviceImpl.java.vm");
                })
                .execute();
    }

    /**
     * 进行其他文件的自动生成
     *
     * @param outputDir
     * @param parent
     * @param entity
     * @return void
     */
    private static void createOtherFile(String outputDir, String parent, String entity) throws IOException {
        // 将包名转换为目录格式
        String replace = parent.replace(".", "/");
        // 获取 entity 文件夹
        File fileDirectory = new File(outputDir + "/" + replace + '/' + entity);
        // 创建vo
        File voFileDirectory = new File(fileDirectory + "/vo");
        voFileDirectory.mkdir();
        // 获取每一个文件
        for (File file : fileDirectory.listFiles()) {
            // 对 java文件操作，而不是文件夹
            if (!file.isDirectory()) {
                // 获取JAVA名
                String name = file.getName();
                name = name.substring(0, name.lastIndexOf(JAVA_FILE_SUFFIX));
                // 获取文件路径
                String pojoFile = file.getAbsolutePath();
                // 读取内容
                String content = FileUtil.readFile(pojoFile);
                // 创建vo
                creatVO(name, pojoFile, content, entity, "vo", "VO");
                // 首字母小写
                String firstLetter = name.substring(0, 1).toLowerCase();
                String restLetters = name.substring(1);
                String result = firstLetter + restLetters;
                // 创建dto文件夹
                File dtoDileDirectory = new File(fileDirectory + "/dto/" + result);
                dtoDileDirectory.mkdirs();
                // 创建dto文件
                creatAddDTO(name, pojoFile, content, entity, "dto/" + result, "AddDTO");
                creatUpdateDTO(name, pojoFile, content, entity, "dto/" + result, "UpdateDTO");
                creatQueryDTO(name, pojoFile, content, entity, "dto/" + result, "QueryDTO");
            }
        }
    }

    /**
     * 创建 vo 文件
     *
     * @param name
     * @param pojoFile
     * @param content
     * @param entity
     * @param directory
     * @param suffix
     * @return void
     */
    private static void creatVO(String name, String pojoFile, String content, String entity, String directory, String suffix) {
        // 创建vo类文件
        String voFile = pojoFile.replace(name + JAVA_FILE_SUFFIX, directory + "/" + name + suffix + JAVA_FILE_SUFFIX);
        // 如果文件已经存在，则不再修改
        if (FileUtil.fileExists(voFile)) {
            return;
        }
        // 对内容进行按行分类
        String[] split = content.split("\n");
        // vo内容
        List<String> voContentList = new ArrayList<>();
        // 进行内容处理，即删除不需要的和修改
        for (String line : split) {
            // 需要忽略的行
            if (line.contains("@Id") || line.contains("@TableId") || line.contains("@Column") || line.contains("@TableField") || line.contains("@Entity") || line.contains("@Table")) {
                continue;
            } else {
                // 包路径需要修改
                if (line.startsWith("package")) {
                    if (directory.contains("/")) {
                        directory = directory.replace("/", ".");
                    }
                    String vo_line = line.replace(entity, entity + "." + directory);
                    voContentList.add(vo_line);
                    voContentList.add("import org.springframework.format.annotation.DateTimeFormat;");
                    voContentList.add("\n");
                }
                // 类名需要修改
                else if (line.startsWith("public class " + name + " implements Serializable")) {
                    String vo_line = line.replace("public class " + name + " implements Serializable", "public class " + name + suffix + " implements Serializable");
                    voContentList.add(vo_line);
                }
                // 注释需要修改
                else if (line.contains("数据表实体类")) {
                    String vo_line = "";
                    // 如果是dto
                    if (suffix.equals("DTO")) {
                        vo_line = line.replace("数据表实体类", "前端->后端数据传输类");
                    } else {
                        vo_line = line.replace("数据表实体类", "后端->前端数据显示类");
                    }
                    voContentList.add(vo_line);
                }
                // 注解需要修改
                else if (line.startsWith("    @JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\",")) {
                    voContentList.add("    @DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
                    voContentList.add("\n");
                }
                // 其他都一样，无需处理，直接添加
                else {
                    voContentList.add(line);
                }
            }
        }
        // 进行线程处理
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : voContentList) {
                voContent.append(s);
            }
            try {
                FileUtil.writeFile(voFile, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * 创建 UpdateDTO 文件
     *
     * @param name
     * @param pojoFile
     * @param content
     * @param entity
     * @param directory
     * @param suffix
     * @return void
     */
    private static void creatUpdateDTO(String name, String pojoFile, String content, String entity, String directory, String suffix) {
        // 创建update类文件
        String updateDTOFile = pojoFile.replace(name + JAVA_FILE_SUFFIX, directory + "/" + name + suffix + JAVA_FILE_SUFFIX);
        // 如果文件已经存在，则不再修改
        if (FileUtil.fileExists(updateDTOFile)) {
            log.info(updateDTOFile + "文件已存在，要生成请先删除修改");
            return;
        }
        // 对内容进行按行分类
        String[] split = content.split("\n");
        // update内容
        List<String> updateContentList = new ArrayList<>();
        // 进行内容处理，即删除不需要的和修改
        for (String line : split) {
            // 需要忽略的行
            if (line.contains("@Id") || line.contains("@TableId") || line.contains("@Column") || line.contains("@TableField") || line.contains("@Entity") || line.contains("@Table")) {
                continue;
            } else {
                // 包路径需要修改
                if (line.startsWith("package")) {
                    if (directory.contains("/")) {
                        directory = directory.replace("/", ".");
                    }
                    String vo_line = line.replace(entity, entity + "." + directory);
                    updateContentList.add(vo_line);
                }
                // 类名需要修改
                else if (line.startsWith("public class " + name + " implements Serializable")) {
                    String vo_line = line.replace("public class " + name + " implements Serializable", "public class " + name + suffix + " implements Serializable");
                    updateContentList.add(vo_line);
                }
                // 注释需要修改
                else if (line.contains("数据表实体类")) {
                    String vo_line = "";
                    // 如果是dto
                    if (suffix.equals("DTO")) {
                        vo_line = line.replace("数据表实体类", "前端->后端数据传输类");
                    } else {
                        vo_line = line.replace("数据表实体类", "后端->前端数据显示类");
                    }
                    updateContentList.add(vo_line);
                }
                // 新增不需要创建时间
                else if (line.startsWith("    private LocalDateTime createTime;")) {
                    // 移除上面的四行注释
                    for (int i = 0; i < 4; i++) {
                        updateContentList.remove(updateContentList.size() - 1);
                    }
                }
                // 新增不需要更新时间
                else if (line.startsWith("    private LocalDateTime updateTime;")) {
                    // 移除上面的四行注释
                    for (int i = 0; i < 4; i++) {
                        updateContentList.remove(updateContentList.size() - 1);
                    }
                }
                // 新增不需要逻辑删除标识
                else if (line.startsWith("    private Boolean deleteFlag;")) {
                    // 移除上面的三行注释
                    for (int i = 0; i < 3; i++) {
                        updateContentList.remove(updateContentList.size() - 1);
                    }
                }
                // 其他都一样，无需处理，直接添加
                else {
                    updateContentList.add(line);
                }
            }
        }
        // 进行线程处理
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : updateContentList) {
                voContent.append(s);
            }
            try {
                FileUtil.writeFile(updateDTOFile, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 创建 AddDto 文件
     *
     * @param name
     * @param pojoFile
     * @param content
     * @param entity
     * @param directory
     * @param suffix
     * @return void
     */
    private static void creatAddDTO(String name, String pojoFile, String content, String entity, String directory, String suffix) {
        // 创建add类文件
        String addDTOFile = pojoFile.replace(name + JAVA_FILE_SUFFIX, directory + "/" + name + suffix + JAVA_FILE_SUFFIX);
        // 如果文件已经存在，则不再修改
        if (FileUtil.fileExists(addDTOFile)) {
            log.info(addDTOFile + "文件已存在，要生成请先删除修改");
            return;
        }
        // 对内容进行按行分类
        String[] split = content.split("\n");
        // add实体类内容
        List<String> addContentList = new ArrayList<>();
        // 进行内容处理，即删除不需要的和修改
        for (String line : split) {
            // 需要忽略的行
            if (line.contains("@Id") || line.contains("@TableId") || line.contains("@Column") || line.contains("@TableField") || line.contains("@Entity") || line.contains("@Table")) {
                continue;
            } else {
                // 包路径需要修改
                if (line.startsWith("package")) {
                    if (directory.contains("/")) {
                        directory = directory.replace("/", ".");
                    }
                    String vo_line = line.replace(entity, entity + "." + directory);
                    addContentList.add(vo_line);
                }
                // 类名需要修改
                else if (line.startsWith("public class " + name + " implements Serializable")) {
                    String vo_line = line.replace("public class " + name + " implements Serializable", "public class " + name + suffix + " implements Serializable");
                    addContentList.add(vo_line);
                }
                // 注释需要修改
                else if (line.contains("数据表实体类")) {
                    String vo_line = "";
                    // 如果是dto
                    if (suffix.equals("DTO")) {
                        vo_line = line.replace("数据表实体类", "前端->后端数据传输类");
                    } else {
                        vo_line = line.replace("数据表实体类", "后端->前端数据显示类");
                    }
                    addContentList.add(vo_line);
                }
                // 新增不需要主键
                else if (line.startsWith("    private Integer id;")) {
                    // 移除上面的三行注释
                    for (int i = 0; i < 3; i++) {
                        addContentList.remove(addContentList.size() - 1);
                    }
                }
                // 新增不需要创建时间
                else if (line.startsWith("    private LocalDateTime createTime;")) {
                    // 移除上面的四行注释
                    for (int i = 0; i < 4; i++) {
                        addContentList.remove(addContentList.size() - 1);
                    }
                }
                // 新增不需要更新时间
                else if (line.startsWith("    private LocalDateTime updateTime;")) {
                    // 移除上面的四行注释
                    for (int i = 0; i < 4; i++) {
                        addContentList.remove(addContentList.size() - 1);
                    }
                }
                // 新增不需要逻辑删除标识
                else if (line.startsWith("    private Boolean deleteFlag;")) {
                    // 移除上面的三行注释
                    for (int i = 0; i < 3; i++) {
                        addContentList.remove(addContentList.size() - 1);
                    }
                }
                // 其他都一样，无需处理，直接添加
                else {
                    addContentList.add(line);
                }
            }
        }
        // 进行线程处理
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : addContentList) {
                voContent.append(s);
            }
            try {
                FileUtil.writeFile(addDTOFile, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 创建 Querydto 文件
     *
     * @param name
     * @param pojoFile
     * @param content
     * @param entity
     * @param directory
     * @param suffix
     * @return void
     */
    private static void creatQueryDTO(String name, String pojoFile, String content, String entity, String directory, String suffix) {
        // 创建query类文件
        String queryFile = pojoFile.replace(name + JAVA_FILE_SUFFIX, directory + "/" + name + suffix + JAVA_FILE_SUFFIX);
        // 如果文件已经存在，则不再修改
        if (FileUtil.fileExists(queryFile)) {
            log.info(queryFile + "文件已存在，要生成请先删除修改");
            return;
        }
        // 对内容进行按行分类
        String[] split = content.split("\n");
        // query内容
        List<String> queryContentList = new ArrayList<>();
        // 进行内容处理，即删除不需要的和修改
        for (String line : split) {
            // 需要忽略的行
            if (line.contains("@Id") || line.contains("@TableId") || line.contains("@Column") || line.contains("@TableField") || line.contains("@Entity") || line.contains("@Table")) {
                continue;
            } else {
                // 包路径需要修改
                if (line.startsWith("package")) {
                    if (directory.contains("/")) {
                        directory = directory.replace("/", ".");
                    }
                    String vo_line = line.replace(entity, entity + "." + directory);
                    queryContentList.add(vo_line);
                    // 添加 PageProperties 的包
                    queryContentList.add("import com.suhuamo.init.common.PageProperties;\n");
                }
                // 类名需要修改
                else if (line.startsWith("public class " + name + " implements Serializable")) {
                    String vo_line = line.replace("public class " + name + " implements Serializable", "public class " + name + suffix + " extends PageProperties implements Serializable");
                    queryContentList.add(vo_line);
                }
                // 注释需要修改
                else if (line.contains("数据表实体类")) {
                    String vo_line = line.replace("数据表实体类", "前端->后端数据传输类");
                    queryContentList.add(vo_line);
                }
                // 查询不需要主键
                else if (line.startsWith("    private Integer id;")) {
                    // 移除上面的三行注释
                    for (int i = 0; i < 3; i++) {
                        queryContentList.remove(queryContentList.size() - 1);
                    }
                }
                // 查询不需要逻辑删除标识
                else if (line.startsWith("    private Boolean deleteFlag;")) {
                    // 移除上面的三行注释
                    for (int i = 0; i < 3; i++) {
                        queryContentList.remove(queryContentList.size() - 1);
                    }
                }
                // 其他都一样，无需处理，直接添加
                else {
                    queryContentList.add(line);
                }
            }
        }
        // 进行线程处理
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : queryContentList) {
                voContent.append(s);
            }
            try {
                FileUtil.writeFile(queryFile, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
