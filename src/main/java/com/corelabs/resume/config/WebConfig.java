package com.corelabs.resume.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 프로젝트 루트의 uploads/resume/ 폴더를 /upload/resume/** URL로 매핑
        String rootPath = System.getProperty("user.dir");
        String path = "file:///" + rootPath + File.separator + "uploads" + File.separator + "resume" + File.separator;

        registry.addResourceHandler("/upload/resume/**")
                .addResourceLocations(path);
    }
}
