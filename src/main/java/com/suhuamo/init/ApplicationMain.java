package com.suhuamo.init;


import com.suhuamo.init.generate.GenerateProperties;
import com.suhuamo.init.generate.GenerateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) throws IOException {
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
