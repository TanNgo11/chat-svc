package org.shadcn.chatsvc.repository;

import java.util.Optional;

import org.shadcn.chatsvc.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
