package org.shadcn.chatsvc.service;

import java.util.List;

import org.shadcn.chatsvc.entity.ChatMessage;

public interface IChatMessageService {
    ChatMessage save(ChatMessage chatMessage);

    List<ChatMessage> findChatMessages(String senderId, String recipientId);

    ChatMessage findLastMessageByUserId(String userId);
}
