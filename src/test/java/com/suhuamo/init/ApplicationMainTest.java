package com.suhuamo.init;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.suhuamo.init.generate.GenerateProperties;
import com.suhuamo.init.generate.GenerateUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yuanchuncheng
 * @date 2024-03-18
 * @description 生成代码
 */
public class ApplicationMainTest {

    @Test
    public void generateCode() throws IOException {
        GenerateProperties generateProperties = new GenerateProperties();
        generateProperties.setUrl("jdbc:mysql://localhost:3306/freshdb?userUnicode=true&characterEncoding=utf8&serverTimezone=UTC&nullCatalogMeansCurrent=true");
        generateProperties.setUsername("root");
        generateProperties.setPassword("123456");
        generateProperties.setAuthor("suhuamo");
        generateProperties.setParent("com.example.test");
        generateProperties.setTables(Arrays.asList("t_order"));
        generateProperties.setTablePrefix(Arrays.asList("t_"));
        // 生成代码
        GenerateUtil.generatorCode(generateProperties);
    }

}