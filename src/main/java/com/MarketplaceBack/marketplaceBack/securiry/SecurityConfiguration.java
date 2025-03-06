package com.MarketplaceBack.marketplaceBack.securiry;

import com.MarketplaceBack.marketplaceBack.service.IJWTUtilityService;
import com.MarketplaceBack.marketplaceBack.service.impl.JWTUtilityServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() //Permitir Options
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JWTAuthorizationFilter((JWTUtilityServiceImpl) jwtUtilityService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> {
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                                }))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

