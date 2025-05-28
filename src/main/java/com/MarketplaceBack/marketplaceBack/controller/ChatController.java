package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Chat;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatMessage;
import com.MarketplaceBack.marketplaceBack.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
/**
 *
 */
public class ChatController {
    @Autowired
    ChatService chatService;

    /**
     * Recibe un Mensaje
     * @param chat Mensaje a guardar
     * @return
     */
    @PostMapping("/chat")
    public ResponseEntity<?> addChat(@RequestBody Chat chat){ //guargar mensaJE
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        chat.setIdUsuario1(idUsuario);
        chatService.addChat(chat);
        return ResponseEntity.ok(chat);
    }


    @GetMapping("/chatList")
    public ResponseEntity<?> getAllChats(){ //Lista de todas las conversaciones del usuario
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        return ResponseEntity.ok(chatService.chatList(idUsuario));
    }

    @GetMapping("/conversacion/{nickname2}")
    public ResponseEntity<?> getConversasion(@PathVariable String nickname2){ // obtiene la conversacion de un usuario con una persona en especifico
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(); //Optiene el id del usuario
        Integer idUsuario = Integer.valueOf(id);
        return ResponseEntity.ok(chatService.getConversacion(idUsuario, nickname2));
    }
}
