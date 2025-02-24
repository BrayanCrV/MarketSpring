package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.Chat;
import com.MarketplaceBack.marketplaceBack.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteById(int id) {
        chatRepository.deleteById(id);

    }
}

