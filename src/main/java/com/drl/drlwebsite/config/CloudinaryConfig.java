package com.drl.drlwebsite.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dguzmpzl8");
        config.put("api_key", "359251357672132");
        config.put("api_secret", "FSHqaIJ9ibW1XKoijSXj9rW33qw");

        return new Cloudinary(config);
    }
}