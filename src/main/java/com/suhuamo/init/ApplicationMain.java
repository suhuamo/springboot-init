package com.suhuamo.init;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) throws IOException {
        // TODO: 2024/3/20 添加分页查询xml语句,整理自动生成代码的util【生成vo和dto的通用提取】，重新设计CodeEnum和全局异常，DTO加上@NotNUll和接口加上@Valid;使用注解过滤当前用户权限数据；ThreadLocalUtil添加getUser方法 [by:yuanchuncheng]
        SpringApplication.run(ApplicationMain.class, args);
    }
}
