package com.suhuamo.init.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 文件处理工具
 */
@Slf4j
public class FileUtil {

    /**
     *  返回项目resources目录下的对应文件的绝对路径
     * @param fileName 文件路径/名称
     * @return String
     */
    public static String getResourceFilePath(String fileName) throws ClassNotFoundException {
        return Objects.requireNonNull(Class.forName(FileUtil.class.getName()).getClassLoader().getResource(fileName)).getPath();
    }

    /**
     *  返回项目resources目录下的对应文件的文件输入流
     * @param fileName 文件路径/名称
     * @return String
     */
    public static InputStream getResourceFileInputStream(String fileName) {
        return FileUtil.class.getResourceAsStream(fileName);
    }

    /**
     * 读取文件的所有内容
     *
     * @param path
     * @return String
     */
    public static String readFile(String path) {
        // 获取文件对象
        File file = new File(path);
        // 读取文件内容 (输入流)
        try (InputStreamReader isr = new InputStreamReader(Files.newInputStream(file.toPath()))){
            // 进入读取数据
            StringBuilder content = new StringBuilder(); // 返回文件的所有内容
            int ch = 0; // 用来接收输入流的每一次数据
            while ((ch = isr.read()) != -1) {
                content.append((char) ch);
            }
            return content.toString();
        } catch (Exception e) {
            log.error("读取文件内容失败：{}", e.getMessage());
            return "";
        }
    }


    /**
     * 将 content 内容以覆盖的形式写入 path 文件中
     *
     * @param path
     * @param content
     * @return void
     */
    public static void writeFile(String path, String content) throws IOException {
        writeFile(path, content, false);
    }

    /**
     * 将 content 内容以覆盖的形式写入 path 文件中,但使用线程
     *
     * @param path
     * @param contentList
     * @return void
     */
    public static void writeFileByThread(String path, List<String> contentList) {
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : contentList) {
                voContent.append(s);
            }
            try {
                FileUtil.writeFile(path, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 判断文件是否存在，存在返回true
     *
     * @param path 文件路径
     * @return boolean
     */
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     *  判断文件夹是否存在，如果不存在，则创建文件夹
     * @param path
     * @return boolean 返回true代表创建成功，返回false代表创建失败【即不需要创建】
     * @version 1.0
     * @author suhuamo
     */
    public static boolean mkdirIfNotExists(String path) {
        if(!FileUtil.fileExists(path)) {
            File file = new File(path);
            return file.mkdirs();
        }
        return false;
    }

    public static boolean createIfNotExists(String path) {
        if(!FileUtil.fileExists(path)) {
            File file = new File(path);
            try {
                return file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * 将 content 内容以append的形式写入 path 文件中
     * 1.append为true则代表追加
     * 2.append为false则代表覆盖
     *
     * @param path
     * @param content
     * @return void
     */
    public static void writeFile(String path, String content, Boolean append) throws IOException {
        // 创建文件
        File file = new File(path);
        file.createNewFile();
        // 进行写入数据
        byte bt[] = content.getBytes();
        // 以覆盖的形式，加上true参数代表追加
        FileOutputStream in = new FileOutputStream(file, append);
        in.write(bt, 0, bt.length);
        // 关闭流
        in.close();
    }
}
