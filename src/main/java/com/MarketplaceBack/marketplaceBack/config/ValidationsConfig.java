package com.MarketplaceBack.marketplaceBack.config;

import com.MarketplaceBack.marketplaceBack.models.validation.UsuarioValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationsConfig {
    @Bean
    public UsuarioValidation userValidation() {
        return new UsuarioValidation();
    }
}
