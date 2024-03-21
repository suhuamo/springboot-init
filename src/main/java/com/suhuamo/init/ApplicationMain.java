package com.suhuamo.init;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) throws IOException {
        // TODO: 2024/3/20 添加分页查询xml语句,整理自动生成代码的util  [by:yuanchuncheng]
        SpringApplication.run(ApplicationMain.class, args);
    }
}
