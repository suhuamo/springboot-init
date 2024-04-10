package com.suhuamo.init.service.impl;

import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import com.suhuamo.init.pojo.dto.file.FileUploadDTO;
import com.suhuamo.init.propertie.FileProperties;
import com.suhuamo.init.service.FileService;
import com.suhuamo.init.util.DateUtil;
import com.suhuamo.init.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenjing
 * @slogan 今天的早餐是：早苗的面包、秋子的果酱和观铃的果汁~
 * @date 2024-03-27
 * @description 文件服务实现层
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    FileProperties fileProperties;
    @Autowired
    ServerProperties serverProperties;

    @Override
    public void validUploadFile(MultipartFile multipartFile) {
        // 1：判断上传文件是否为空
        if (multipartFile.isEmpty()) {
            throw new CustomException(CodeEnum.PARAM_ERROR, "不可上传空文件");
        }
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        // 1.生成文件名：当前时间.格式后缀
        String fileName = DateUtil.getNameByDate() + getSuffix(multipartFile.getOriginalFilename());
        // 2.进行下载文件
        try {
            multipartFile.transferTo(new File(fileProperties.getImgAbsolutePath(), fileName));
            log.info("文件{}下载成功", fileName);
        } catch (IOException e) {
            throw new CustomException(CodeEnum.PARAM_ERROR, e.getMessage());
        }
        return getFileUrl(fileName);
    }

    @Override
    public List<String> urlList() {
        // 1. 获取文件夹
        File imgAbsolutePath = new File(fileProperties.getImgAbsolutePath());
        // 2. 获取每一个文件
        File[] files = imgAbsolutePath.listFiles();
        // 3. 将文件转换为地址链接
        return Arrays.stream(files).map(file -> this.getFileUrl(file.getName())).collect(Collectors.toList());
    }

    @Override
    public String upload(FileUploadDTO fileUploadDTO) {
        // 1.生成文件名：文件名称.格式后缀
        String fileName = getFileName(fileUploadDTO) + getSuffix(fileUploadDTO.getMultipartFile().getOriginalFilename());
        // 2.进行下载文件
        try {
            File file = new File(fileProperties.getImgAbsolutePath(), fileName);
            // 3. 防止该文件名称对应的文件已存在造成文件覆盖，当文件存在时，给则当前文件名称（1）、（2）....
            file = renameFile(file);
            // 4. 生成文件
            fileUploadDTO.getMultipartFile().transferTo(file);
            log.info("文件{}下载成功", fileName);
        } catch (IOException e) {
            throw new CustomException(CodeEnum.PARAM_ERROR, e.getMessage());
        }
        return getFileUrl(fileName);
    }

    /**
     *
     * @param file
     * @return File
     * @version
     * @author yuanchuncheng
     */
    private File renameFile(File file) {
        int idx = 0; // 标记当前是第几个重复的文件
        String removeSuffix = removeSuffix(file.getName()); // 获取文件名称，不带后缀
        String suffix = getSuffix(file.getName()); // 获取文件后缀
        // 判断该文件是否已经存在
        while(FileUtil.fileExists(file.getAbsolutePath())) {
            // 如果存在，则名称重命名: 名称(idx).后缀
            file = new File(file.getParent(), removeSuffix + "(" + (++idx) + ")" + suffix);
        }
        // 返回重命名后的文件
        return file;
    }

    public static void main(String[] args) {
        File file = new File("D:\\test\\test\\a.txt");
        int idx = 0;
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getName() = " + file.getName());
        System.out.println("file.getParent() = " + file.getParent());
        String name = file.getName();
        String removeSuffix = removeSuffix(name);
        String suffix = getSuffix(name);
        while(FileUtil.fileExists(file.getAbsolutePath())) {
            file = new File(file.getParent(), removeSuffix + "(" + (++idx) + ")" + suffix);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  获取文件上传对象的文件名称
     * @param fileUploadDTO 文件上传对象
     * @return String
     * @author suhuamo
     */
    private String getFileName(FileUploadDTO fileUploadDTO) {
        return fileUploadDTO.getName() != null ? removeSuffix(fileUploadDTO.getName()) : removeSuffix(fileUploadDTO.getMultipartFile().getOriginalFilename());
    }

    /**
     * 返回文件的网络链接
     *
     * @param fileName 文件名称
     * @return
     */
    private String getFileUrl(String fileName) {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            ip = "127.0.0.1";
        }
        Integer port = serverProperties.getPort();
        if (port == 0) {
            port = 8080;
        }
        return "http://" + ip + ":" + port + "/files/" + fileName;
    }

    /**
     * 获取图片文件的文件后缀
     *
     * @param picName
     * @return
     */
    private static String getSuffix(String picName) {
        return picName.substring(picName.lastIndexOf("."));
    }

    /**
     *  去掉文件名称的文件后缀
     * @param fileName 文件名称
     * @return String
     * @author suhuamo
     */
    private static String removeSuffix(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
