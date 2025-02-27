package org.shadcn.chatsvc.repository;

import java.util.List;

import org.shadcn.chatsvc.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);

    ChatMessage findFirstBySenderIdOrRecipientIdOrderByTimestampDesc(String senderId, String recipientId);
}
