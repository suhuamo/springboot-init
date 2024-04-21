package com.suhuamo.init;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.io.IOException;
// 注释数据库
//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class ,
//        DruidDataSourceAutoConfigure.class})
@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) throws IOException {
        // TODO: 2024/3/20 如下 [by:yuanchuncheng]
        /*
         1. 添加分页查询xml语句

         3. 重新设计CodeEnum和全局异常
         4. DTO加上@NotNUll和接口加上@Valid
         5. 使用注解过滤当前用户权限数据
         6. ThreadLocalUtil添加getUser方法
         7. Integer使用 NotNull， String使用NotEmpty 注解
         8. vo,dto
         9. processUtil工具类
         */
        SpringApplication.run(ApplicationMain.class, args);
    }
}
