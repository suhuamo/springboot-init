package com.suhuamo.init.config;

import com.suhuamo.init.propertie.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 路径映射配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    FileProperties fileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将本地路径映射到前端显示
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + fileProperties.getImgAbsolutePath() + "\\");
    }
}
