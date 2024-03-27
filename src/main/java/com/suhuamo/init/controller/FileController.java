package com.suhuamo.init.controller;

import com.suhuamo.init.common.ResponseResult;
import com.suhuamo.init.enums.CodeEnum;
import com.suhuamo.init.service.FileService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chenjing
 * @slogan 耐心等，太阳总会升起来的。
 * @date 2024-03-27
 * @description
 * 文件相关接口
 */
@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 上传单个文件并获取文件的链接地址
     * @param multipartFile
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult<String> upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        // 1. 校验上传的文件
        fileService.validUploadFile(multipartFile);
        // 2. 上传图片
        String fileUrl = fileService.upload(multipartFile);
        // 3. 返回图片的url
        return ResponseResult.ok(fileUrl);
    }

    /**
     * 获取所有的文件链接
     * @return
     */
    @GetMapping
    public ResponseResult<List<String>> list() {
        // 1. 获取所有的文件链接
        List<String> fileUrlList = fileService.urlList();
        // 2. 返回文件的url
        return ResponseResult.ok(fileUrlList);
    }

    // todo： 1.删除文件 2.修改文件名称
}