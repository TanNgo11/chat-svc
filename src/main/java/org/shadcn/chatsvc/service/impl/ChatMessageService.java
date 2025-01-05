package org.shadcn.chatsvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.shadcn.chatsvc.entity.ChatMessage;
import org.shadcn.chatsvc.repository.ChatMessageRepository;
import org.shadcn.chatsvc.service.IChatMessageService;
import org.shadcn.chatsvc.service.IChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChatMessageService implements IChatMessageService {
    //refactor cho nay gium nhen dont use autowired
    @Autowired
    private ChatMessageRepository repository;
    @Autowired
    private IChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new IllegalArgumentException("Chat room doesn't exist"));
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }

}