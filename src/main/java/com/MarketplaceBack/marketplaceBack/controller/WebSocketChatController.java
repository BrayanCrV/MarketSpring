package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Chat;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatList;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatMessage;
import com.MarketplaceBack.marketplaceBack.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;

@Controller
public class WebSocketChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChatMessage(@Payload ChatMessage chatMessage) {
        // Guardar mensaje en la base de datos
        System.out.println("aca danamos");
        System.out.println(chatMessage);
        Chat chat = new Chat();
        chat.setMensaje(chatMessage.getMensaje());
        chat.setFecha(new Timestamp(System.currentTimeMillis()));
        chat.setIdUsuario1(chatService.getUserId(chatMessage.getRemitenteNickname()));
        chat.setIdUsuario2(chatService.getUserId(chatMessage.getReceptorNickname()));
        System.out.println("Despues chat Message");
        System.out.println(chat);
        chatService.addChat(chat);
        ChatList chatList = new ChatList();
        chatList.setEnviadoPor(chatMessage.getRemitenteNickname());
        chatList.setFecha(chat.getFecha());
        chatList.setNickname2(chatMessage.getRemitenteNickname());
        chatList.setOtroUsuario(chat.getIdUsuario1());
        chatList.setMensaje(chatMessage.getMensaje());
        // Enviar mensaje a los usuarios suscritos
        System.out.println("/topic/chat/" + chatMessage.getReceptorNickname());
       // messagingTemplate.convertAndSend("/topic/chat/" + chatMessage.getReceptorNickname(), chat);
        messagingTemplate.convertAndSend("/topic/chat/" + chatMessage.getRemitenteNickname() + "-" + chatMessage.getReceptorNickname(), chat);
        messagingTemplate.convertAndSend("/topic/chat/R/" + chatMessage.getReceptorNickname(), chatList);
    }
}
