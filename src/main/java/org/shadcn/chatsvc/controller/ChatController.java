package org.shadcn.chatsvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.shadcn.chatsvc.dto.ApiResponse;
import org.shadcn.chatsvc.entity.ChatMessage;
import org.shadcn.chatsvc.entity.ChatNotification;
import org.shadcn.chatsvc.service.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private IChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(), savedMsg.getSenderId(), savedMsg.getRecipientId(), savedMsg.getContent()));

    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ApiResponse<List<ChatMessage>> findChatMessages(
            @PathVariable String senderId, @PathVariable String recipientId) {
        return ApiResponse.success(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
