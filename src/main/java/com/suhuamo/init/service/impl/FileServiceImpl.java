package com.suhuamo.init.service.impl;

import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.exception.CustomException;
import com.suhuamo.init.propertie.FileProperties;
import com.suhuamo.init.service.FileService;
import com.suhuamo.init.util.DateUtil;
import com.suhuamo.init.util.ThreadPoolUtil;
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
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @author chenjing
 * @slogan 今天的早餐是：早苗的面包、秋子的果酱和观铃的果汁~
 * @date 2024-03-27
 * @description
 * 文件服务实现层
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
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            try {
                System.out.println("fileProperties.getImgAbsolutePath() = " + fileProperties.getImgAbsolutePath());
                System.out.println("fileName = " + fileName);
                multipartFile.transferTo(new File(fileProperties.getImgAbsolutePath(), fileName));
                log.info("文件{}下载成功", fileName);
            } catch (IOException e) {
                throw new CustomException(CodeEnum.PARAM_ERROR, e.getMessage());
            }
        });
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

    /**
     * 返回文件的网络链接
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
        if(port == 0) {
            port = 8080;
        }
        return "http://" + ip + ":" + port + "/files/" + fileName;
    }

    /**
     * 获取图片文件的文件后缀
     * @param picName
     * @return
     */
    private String getSuffix(String picName) {
        return picName.substring(picName.lastIndexOf("."));
    }
}
