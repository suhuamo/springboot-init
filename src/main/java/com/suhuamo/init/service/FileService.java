package com.suhuamo.init.service;

import com.suhuamo.init.pojo.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chenjing
 * @slogan 今天的早餐是：早苗的面包、秋子的果酱和观铃的果汁~
 * @date 2024-03-27
 * @description 文件服务层
 */
public interface FileService {
    /**
     * 校验上传的文件
     * @param multipartFile
     */
    void validUploadFile(MultipartFile multipartFile);

    /**
     * 保存图片到本地并返回连接
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile);

    /**
     * 获取所有的文件链接
     * @return
     */
    List<String> urlList();

    /**
     * 保存图片到本地并返回连接
     * @param fileDTO
     * @return
     */
    String upload(FileDTO fileDTO);
}
