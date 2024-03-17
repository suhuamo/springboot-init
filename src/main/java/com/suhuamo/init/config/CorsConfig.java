package com.suhuamo.init.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 添加跨域请求映射--全局Cors配置
     *
     * @param registry
     * @return void
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //映射服务器中哪些http接口运行跨域访问
                .allowedOrigins("http://localhost:8080") // 配置哪些来源有权跨域，可以防止其他人的跨域攻击。
                .allowedMethods("*") // 允许跨域的方法，可以单独配置
                .allowedHeaders("*") // 允许跨域的请求头，
                .allowCredentials(true) // 允许请求头中携带参数
                .maxAge(3600);
    }
}
