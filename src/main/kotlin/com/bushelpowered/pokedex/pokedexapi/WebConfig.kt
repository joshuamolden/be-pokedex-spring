package com.bushelpowered.pokedex.pokedexapi

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")  // every route will match this path name
                .allowedOrigins("http://localhost:3000", "http://localhost:8080")   // allows frontend to receive request from backend when using react or vue.js
                .allowCredentials(true) // allows frontend to receive cookie
    }
}