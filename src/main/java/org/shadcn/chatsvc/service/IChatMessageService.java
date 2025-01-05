package org.shadcn.chatsvc.service;

import org.shadcn.chatsvc.entity.ChatMessage;

import java.util.List;

public interface IChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
