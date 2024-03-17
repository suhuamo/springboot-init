package com.suhuamo.init.config;

import com.suhuamo.init.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * jwt的web鉴权配置
 */
@Configuration
public class JwtWebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     *
     * @param registry
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor()) // 添加自定义拦截器
                .addPathPatterns("/**")// 设置映射规则,即允许哪些接口会被添加到拦截器中
                .excludePathPatterns("/user/login", "/user/register"); //设置排除的规则，即哪些接口不会被加入到拦截器中
    }
}
