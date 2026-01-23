package com.corelabs.profile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String rootPath = System.getProperty("user.dir");

        String profilePath = "file:///" + rootPath + File.separator + "uploads" + File.separator + "profile" + File.separator;
        registry.addResourceHandler("/upload/profile/**")
                .addResourceLocations(profilePath);

        String filePath = "file:///" + rootPath + File.separator + "uploads" + File.separator + "files" + File.separator;
        registry.addResourceHandler("/upload/files/**")
                .addResourceLocations(filePath);
    }
}
