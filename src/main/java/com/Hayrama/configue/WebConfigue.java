package com.Hayrama.configue;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfigue implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("backHayrama/api/**")
				.allowedOrigins("http://localhost:8721")
				.allowedMethods("OPTIONS", "POST", "GET", "PUT", "DELETE")
				.allowedHeaders("*")
                .allowCredentials(true);
	}

}
