package com.suhuamo.init;


import com.suhuamo.init.generate.GenerateProperties;
import com.suhuamo.init.generate.GenerateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) throws IOException {
        // TODO: 2024/3/10 模板项目的配置跨域，接口层的返回结果修改和生成的DTO字段修改，Query字段的增加，mysql连接地址的时区配置,CrosConfig的命名，生成实体类时不能覆盖已经有的文件
        // TODO: 2024/3/11 待完成所有的新增校验
        // TODO: 2024/3/13 逻辑处理：如停车位不能同时预约，一个停车位只能有一个车
        // 1. 先自动生成代码
        // 2. 在运行项目
        boolean run = true;
        if(run) {
            SpringApplication.run(ApplicationMain.class, args);
        } else {
            GenerateProperties generateProperties = new GenerateProperties();
            generateProperties.setParent("com.suhuamo.init");
            GenerateUtil.generatorCode(generateProperties);
        }
    }
}
