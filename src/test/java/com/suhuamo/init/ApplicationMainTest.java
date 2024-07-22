package com.suhuamo.init;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.suhuamo.init.generate.GenerateProperties;
import com.suhuamo.init.generate.GenerateUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author suhuamo
 * @date 2024-03-18
 * @description 生成代码
 */
public class ApplicationMainTest {

    @Test
    public void generateCode() throws IOException {
        GenerateProperties generateProperties = new GenerateProperties();
        generateProperties.setUrl("jdbc:mysql://localhost:3306/init?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B8");
        generateProperties.setUsername("root");
        generateProperties.setPassword("123456");
        generateProperties.setAuthor("suhuamo");
        generateProperties.setParent("com.example.test");
        generateProperties.setTemplateType(0);
        generateProperties.setTables(Arrays.asList("t_user"));
        generateProperties.setTablePrefix(Arrays.asList("t_"));
        // 生成代码
        GenerateUtil.generatorCode(generateProperties);
    }

}