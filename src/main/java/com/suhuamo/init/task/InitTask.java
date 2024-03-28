package com.suhuamo.init.task;

import com.suhuamo.init.propertie.FileProperties;
import com.suhuamo.init.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;

/**
 * @author chenjing
 * @slogan 巨人给你鞠躬，是为了让阳光也照射到你。
 * @date 2024-03-27
 * @description
 * 初始话相关
 * 1. 创建本地图片目录文件夹
 */
@Slf4j
@Configuration
public class InitTask implements EnvironmentAware {

    @Autowired
    FileProperties fileProperties;
    @Autowired
    MultipartProperties multipartProperties;

    @Override
    public void setEnvironment(Environment environment) {
        // 1. 判断图片文件夹是否存在，如果不存在，则创建
        FileUtil.mkdirIfNotExists(fileProperties.getImgAbsolutePath());
        // 2. 判断缓冲区文件夹是否存在，如果不存在，则创建
        FileUtil.mkdirIfNotExists(multipartProperties.getLocation());
    }
}
