package com.project.configuration;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary geCloudinary(){

        Map config = new HashMap();

        config.put("cloud_name","dtwzjkapl");
        config.put("api_key","191329267478154");
        config.put("api_secret","HQp5_f1ahhs-4lg3RBBkRq7YtlI");
        config.put("secure", true);

        return new Cloudinary(config);
    }
}
