package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.Chat;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatList;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatMessage;
import com.MarketplaceBack.marketplaceBack.repository.ChatRepository;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsuarioRepository userRepository;

    public void addChat(Chat chat) {
        chatRepository.save(chat);
    }

    public List<ChatList> chatList(Integer userId) {
        return chatRepository.findUltimosMensajesPorUsuario(userId).orElse(List.of());
    }

    public List<ChatMessage> getConversacion(Integer userId, String nickname2) {
        return chatRepository.findConversacion(userId, nickname2).orElse(List.of());
    }

    public Integer getUserId(String nickname) {
        return userRepository.findByNickname(nickname)
                .map(Usuario -> Usuario.getIdUsuario())
                .orElse(null);
    }
}
