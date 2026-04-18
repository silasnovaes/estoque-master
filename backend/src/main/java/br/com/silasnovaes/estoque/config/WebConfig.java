package br.com.silasnovaes.estoque.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração global da aplicação web.
 * Centraliza as regras de CORS (Cross-Origin Resource Sharing) para permitir
 * que o nosso frontend moderno se comunique de forma segura com a API.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a regra para todos os endpoints da API
                .allowedOrigins("*") // Em ambiente de produção, substituiríamos pelo domínio do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}