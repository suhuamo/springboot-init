package com.suhuamo.init;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.suhuamo.init.generate.GenerateProperties;
import com.suhuamo.init.generate.GenerateUtil;
import com.suhuamo.init.generate.TemplateTypeEnum;
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
//        数据库地址配置
        generateProperties.setUrl("jdbc:mysql://192.168.16.117:3306/education-1.1.4?userUnicode=true&characterEncoding=utf8&serverTimezone=UTC&nullCatalogMeansCurrent=true");
//        数据库账号
        generateProperties.setUsername("root");
//        数据库密码
        generateProperties.setPassword("123456");
//        生成代码的包名
        generateProperties.setParent("com.yscz");
        generateProperties.setTemplateType(TemplateTypeEnum.STANDARD.getType());
//        需要生成代码的表名
        generateProperties.setTables(Arrays.asList(new String[]{"t_operation_info"}));
//        生成的PO对象需要忽略的前缀
        generateProperties.setTablePrefix(Arrays.asList(new String[]{"t_"}));
//        执行生成代码方法
        GenerateUtil.generatorCode(generateProperties);
    }

}