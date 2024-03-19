package com.suhuamo.init;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) throws IOException {
        // TODO: 2024/3/10 生成的DTO字段修改，Query字段的增加，mysql连接地址的时区配置，生成实体类时不能覆盖已经有的文件
        SpringApplication.run(ApplicationMain.class, args);
    }
}
