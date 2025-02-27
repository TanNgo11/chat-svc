package org.shadcn.chatsvc.service;

import java.util.List;
import java.util.Optional;

import org.shadcn.chatsvc.entity.ChatRoom;

public interface IChatRoomService {
    Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists);

    List<ChatRoom> findChatRoomsBySenderIdOrRecipientId(String senderId, String recipientId);
}
