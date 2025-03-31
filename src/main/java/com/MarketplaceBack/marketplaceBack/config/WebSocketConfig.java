package com.MarketplaceBack.marketplaceBack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Habilita un simple broker en /topic para chats públicos y /queue para mensajes privados
        config.enableSimpleBroker("/topic", "/queue");

        // Prefijo de destino de los mensajes enviados desde el frontend al backend
        config.setApplicationDestinationPrefixes("/api/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/ws") // Endpoint WebSocket para conexiones STOMP
                .setAllowedOrigins("http://localhost:3000") // Solo permite conexiones desde el frontend
                .withSockJS(); // Habilita SockJS para compatibilidad con navegadores sin WebSockets nativos
    }
}
