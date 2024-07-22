package com.suhuamo.init.generate;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * @author suhuamo
 * @slogan 不待春风慢，我以明月宴群山。
 * @date 2023-12-31
 * @description
 *  自动生成器需要的参数
 */
@Data
@Accessors(chain = true)
public class GenerateProperties {
    /**
     * 数据库url
     * @version 1.0
     * @author suhuamo
     */
    private String url = "jdbc:mysql://localhost:3306/online_ide?userUnicode=true&characterEncoding=utf8&serverTimezone=UTC&nullCatalogMeansCurrent=true";
    /**
     * 数据库账号
     * @version 1.0
     * @author suhuamo
     */
    private String username = "root";
    /**
     * 数据库密码
     * @version 1.0
     * @author suhuamo
     */
    private String password = "123456";

    // region 全局配置参数
    /**
     * 作者名称
     * @version 1.0
     * @author suhuamo
     */
    private String author = "suhuamo";
    /**
     * 指定java的输出目录，默认为当前项目的java文件夹
     * @version 1.0
     * @author suhuamo
     */
    private String outputDirJava = System.getProperty("user.dir") + "\\src\\main\\java";
    /**
     * 指定resources的输出目录，默认为当前项目的resources文件夹
     * @version 1.0
     * @author suhuamo
     */
    private String outputDirResources = System.getProperty("user.dir") + "\\src\\main\\resources";

    /**
     * 模板的三种类型。0：最新版；1：实体类版；2：标准版
     * @version 1.0
     * @author suhuamo
     */
    private int templateType = 0;
    // endregion 全局配置参数

    // region 包路径参数
    /**
     * 父包名
     * @version 1.0
     * @author suhuamo
     */
    private String parent = "com.suhuamo.example";
    /**
     * Entity 实体类包名
     * @version 1.0
     * @author suhuamo
     */
    private String entity = "pojo";
    /**
     * Mapper 包名
     * @version 1.0
     * @author suhuamo
     */
    private String mapper = "mapper";
    /**
     * Mapper XML 包名
     * @version 1.0
     * @author suhuamo
     */
    private String mapperXml = "mapper";
    /**
     * Service 包名
     * @version 1.0
     * @author suhuamo
     */
    private String service = "service";
    /**
     * Service Impl 包名
     * @version 1.0
     * @author suhuamo
     */
    private String serviceImpl = "service.impl";
    /**
     * Controller 包名
     * @version 1.0
     * @author suhuamo
     */
    private String controller = "controller";
    // endregion 包路径参数

    // region 数据库表参数
    /**
     * 要生成的数据库表，可传数组,如 tbl_user, tbl_role
     * @version 1.0
     * @author suhuamo
     */
    private List<String> tables = Arrays.asList("ide_project", "ide_user");
    /**
     * 忽略表前缀，可传数组，如 tbl_, sys_
     * @version 1.0
     * @author suhuamo
     */
    private List<String> tablePrefix = Arrays.asList("ide_");
    // endregion 数据库表参数
}
