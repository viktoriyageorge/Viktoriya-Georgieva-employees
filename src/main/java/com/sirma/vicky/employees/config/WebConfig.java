package com.sirma.vicky.employees.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("*") // 🔐 For development only!
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    /*
     🔐 CORS configuration is intentionally permissive to simplify local frontend-backend communication
     (e.g., from file://, localhost:63342, or local static HTML files).

     ⚠️ IMPORTANT: For production environments, restrict allowedOrigins() to trusted frontend domains only,
     such as:

         .allowedOrigins("https://your-frontend-domain.com")

     and limit allowed methods and headers accordingly to reduce attack surface.
     */
}
