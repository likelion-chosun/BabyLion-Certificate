package com.baby.lions.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
							.allowedOrigins(
											"http://localhost:8080",
											"http://localhost:3000",
											"http://localhost:5173"
							)
							.allowedHeaders("*")
							.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS" , "PATCH")
							.allowCredentials(true)
							.maxAge(3000);
		}
}
