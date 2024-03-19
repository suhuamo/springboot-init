# springboot-init

#### 介绍
springboot的初始化项目，带有CRUD的代码生成器，也带有基础的配置，如mybaits的分页配置，cros的跨域的配置，jwt的认证配置等。

# 使用教程
## 一、只用于生成CRUD代码
### 1. 创建一个空的maven项目
### 2. 在pom.xml中配置依赖
```xml
<!--生成CRUD代码依赖-->
 <dependency>
    <groupId>com.suhuamo</groupId>
    <artifactId>springboot-init</artifactId>
    <version>1.0</version>
</dependency>
<!--以下四个依赖是用于自动生成代码的而外依赖-->
<!--        mybatis-plus启动器 -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.3</version>
</dependency>
<!--        mybatis-plus代码自动生成器 -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.5.3</version>
</dependency>
<!--  配合mybatis-plus代码自动生成器使用      -->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.2</version>
</dependency>
<!--        mysql驱动 -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.31</version>
</dependency>
```
### 3. 创建运行类，配置参数然后运行
```java
package com.suhuamo.test;
import java.io.IOException;

import java.util.Arrays;

import com.suhuamo.init.generate.GenerateProperties;
import com.suhuamo.init.generate.GenerateUtil;

/**
 * @author yuanchuncheng
 * @slogan 巨人给你鞠躬，是为了让阳光也照射到你。
 * @date 2023-12-27
 * @description
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GenerateProperties generateProperties = new GenerateProperties();
//        数据库地址配置
        generateProperties.setUrl("jdbc:mysql://localhost:3306/temp_ycc?userUnicode=true&characterEncoding=utf8&serverTimezone=UTC&nullCatalogMeansCurrent=true");
//        数据库账号
        generateProperties.setUsername("root");
//        数据库密码
        generateProperties.setPassword("");
//        生成代码的包名
        generateProperties.setParent("org.yscz.demo");
//        需要生成代码的表名
        generateProperties.setTables(Arrays.asList(new String[]{"tbl_person"}));
//        生成的PO对象需要忽略的前缀
        generateProperties.setTablePrefix(Arrays.asList(new String[]{"tbl_"}));
//        执行生成代码方法
        GenerateUtil.generatorCode(generateProperties);
    }
}
```
### 4.点击运行即可生成代码
#### 1.生成前结构
![生成前.png](assert/img1.png)
#### 2.生成后结构
![生成后.png](assert/img2.png)

### 注意：如果要将生成的代码粘贴到新项目中（没有引用`springboot-init` 依赖的项目），那么要将`springboot-init` 项目中的 `com.suhuamo.init.common` 目录复制到新项目中，否则会报错【有两个类找不到】。
![common目录.png](assert/img3.png)


## 二、用于生成CRUD代码并且作为基础项目使用
### 1. 下载代码
```shell
git clone https://gitee.com/suhuamo/springboot-init.git
```
### 2. 生成CRUD代码
操作和第一种一样，只不过不需要修改依赖了，直接运行`src/test/java/com/suhuamo/init/ApplicationMainTest.java`文件生成代码即可。
### 3. 修改`application.yml`配置
- mysql配置：10、11、12； 配置数据库、账号、密码
- mybatis配置：24~41；xml文件映射位置、表名前缀、逻辑删除字段、实体类位置
- springdoc配置：44~61：配置swagger的相关信息，主要修改61行的包名位置
- jwt配置： `com.suhuamo.init.config.JwtWebConfig` 中配置拦截规则和放行的接口
- cors配置：`com.suhuamo.init.config.CorsConfig` 中允许跨域的ip、方法、请求头、是否携带cookie等。
### 4. 配置完成，正常开发即可

### 注意
    由于配置了该插件`classifier`，那么打包之后，应该运行的jar包是 `springboot-init-1.0-exec.jar`,而不是 `springboot-init-1.0.jar`。因为配置了这个插件之后，自动生成的`springboot-init-1.0.jar`是不含任何依赖的，只有该项目的源代码，故可以被其他项目引用，而`springboot-init-1.0-exec.jar`是含有所有依赖的，可以直接运行。
![注意1.png](assert/img4.png)