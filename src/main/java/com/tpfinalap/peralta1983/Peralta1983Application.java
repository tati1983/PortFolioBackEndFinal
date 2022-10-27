package com.tpfinalap.peralta1983;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Peralta1983Application {

	public static void main(String[] args) {
		SpringApplication.run(Peralta1983Application.class, args);
	}
//        public WebMvcConfigurer corsConfigurer() {        return new WebMvcConfigurer() {
//                @Override
//                public void addCorsMappings(CorsRegistry registry) {
//                        registry.addMapping("/**").allowedOrigins("/**").allowedMethods("*").allowedHeaders("*");
//                }
//           };
//        }

}

