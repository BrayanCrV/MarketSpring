package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.Chat;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatList;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatMessage;
import com.MarketplaceBack.marketplaceBack.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }
    public void addChat(Chat chat) {
        chatRepository.save(chat);
    }

    public void deleteById(Integer id) {
        chatRepository.deleteById(id);

    }

    public Optional<List<ChatList>> chatList (Integer Id){
        return chatRepository.findUltimosMensajesPorUsuario(Id);
    }
    public Optional<List<ChatMessage>> getConversacion (Integer idUsuario, String Nickname2){
        return chatRepository.findConversacion(idUsuario, Nickname2);
    }
}

