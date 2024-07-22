package com.suhuamo.init.util;

import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import lombok.Data;

import java.io.*;

/**
 * @author suhuamo
 * @date 2024-04-21
 * @slogan 巨人给你鞠躬，是为了让阳光也照射到你。
 * @description
 * cmd操作相关工具类
 */
public class CmdUtil {

    /**
     * 执行cmd命令
     * @param directory 工作目录
     * @param commands 执行的命令
     * @return ProcessResultDTO
     * @version 1.0
     * @author suhuamo
     */
    public static ProcessResultDTO run(String directory, String ...commands) {
        ProcessResultDTO processResultDTO = new ProcessResultDTO();
        // 保存获取到的内容
        StringBuilder result = new StringBuilder();
        // 使用cmd执行命令
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            // 设置当前工作目录为当前目录
            processBuilder.directory(new File(directory));
            // 启动进程
            Process process = processBuilder.start();
            // 等待进程执行完毕
            int exitCode = process.waitFor();
            // 将命令执行结果存入对象中
            processResultDTO.setExitCode(exitCode);
            // 如果命令执行错误
            if(exitCode != 0) {
                // 获取错误流信息
                InputStream inputStream = process.getErrorStream();
                // 将流转换为 BufferedReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                // 读取输出内容
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } else {
                // 获取成功流信息
                InputStream inputStream = process.getInputStream();
                // 将流转换为 BufferedReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                // 读取输出内容
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new CustomException(CodeEnum.PARAM_ERROR, e.getMessage());
        }
        // 将获取到的内容存入对象中
        processResultDTO.setResult(result.toString());
        // 返回运行结果
        return processResultDTO;
    }

    /**
     * 执行cmd命令
     * @param directory 工作目录
     * @param input 输入内容
     * @param commands 执行的命令
     * @return ProcessResultDTO
     * @version 1.0
     * @author suhuamo
     */
    public static ProcessResultDTO runByInput(String directory,String input, String ...commands) {
        ProcessResultDTO processResultDTO = new ProcessResultDTO();
        // 保存获取到的内容
        StringBuilder result = new StringBuilder();
        // 使用cmd执行命令
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            // 设置当前工作目录为当前目录
            processBuilder.directory(new File(directory));
            // 启动进程
            Process process = processBuilder.start();
            //获取标准输出流进行内容输入
            PrintWriter stdInputWriter = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
            stdInputWriter.println(input); // 连续输入
            stdInputWriter.flush();
            // 等待进程执行完毕
            int exitCode = process.waitFor();
            // 将命令执行结果存入对象中
            processResultDTO.setExitCode(exitCode);
            // 如果命令执行错误
            if(exitCode != 0) {
                // 获取错误流信息
                InputStream inputStream = process.getErrorStream();
                // 将流转换为 BufferedReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                // 读取输出内容
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } else {
                // 获取成功流信息
                InputStream inputStream = process.getInputStream();
                // 将流转换为 BufferedReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                // 读取输出内容
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new CustomException(CodeEnum.PARAM_ERROR, e.getMessage());
        }
        // 将获取到的内容存入对象中
        processResultDTO.setResult(result.toString());
        // 返回运行结果
        return processResultDTO;
    }

    /**
     * 判断程序是否正常运行，异常返回true
     * @param processResultDTO
     * @return boolean
     * @version 1.0
     * @author suhuamo
     */
    public static boolean isError(ProcessResultDTO processResultDTO) {
        return 0 != processResultDTO.exitCode;
    }
    private CmdUtil() {};
}
