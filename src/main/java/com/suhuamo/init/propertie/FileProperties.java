package com.suhuamo.init.propertie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author chenjing
 * @slogan 五秒钟内说出三个你爱的人的名字...5..4..3..2..1..你是不是没有说自己，也要爱自己哦。
 * @date 2024-03-27
 * @description
 *  文件相关配置类
 */
@Component
@ConfigurationProperties(prefix = "project.file")
@Data
public class FileProperties {
    /**
     * 项目文件根目录
     */
    private String parentPath;
    /**
     * 图片文件夹名称
     */
    private String imgDirectoryName;

    /**
     * 获取图片文件夹的绝对路径
     * @return
     */
    public String getImgAbsolutePath() {
        return parentPath + "\\" + imgDirectoryName;
    }
}
