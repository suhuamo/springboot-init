package com.suhuamo.init.config;

import com.suhuamo.init.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * jwt的web鉴权配置
 */
@Configuration
public class JwtWebConfig implements WebMvcConfigurer {

    /**
     * swagger文档相关路径
     */
    private final String[] swaggerUrl = new String[]{"/doc*/**","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
            "/api", "/api-docs", "/api-docs/**", "/doc.html/**" };

    /**
     * 用户登录、注册路径
     */
    private final String[] userUrl = new String[]{"/users/login", "/users/register"};

    /**
     * 添加拦截器
     *
     * @param registry
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatternList = new ArrayList<>();
        excludePathPatternList.add("/index.html"); //普通页面--后端首页
        excludePathPatternList.add("/upload/index.html"); //普通页面--测试上传文件页面
        excludePathPatternList.add("/redirect/*"); // 路径跳转接口
        excludePathPatternList.add("/files/**"); // 文件相关接口
        excludePathPatternList.addAll(Arrays.asList(userUrl));
        excludePathPatternList.addAll(Arrays.asList(swaggerUrl));
        registry.addInterceptor(new JwtInterceptor()) // 添加自定义拦截器
                .addPathPatterns("/**")// 设置映射规则,即允许哪些接口会被添加到拦截器中
                .excludePathPatterns(excludePathPatternList); //设置排除的规则，即哪些接口不会被加入到拦截器中

    }
}
