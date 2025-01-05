package org.shadcn.chatsvc.service.impl;
import java.util.Optional;

import org.shadcn.chatsvc.entity.ChatRoom;
import org.shadcn.chatsvc.repository.ChatRoomRepository;
import org.shadcn.chatsvc.service.IChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChatRoomService implements IChatRoomService {
    //refactor cho nay gium nhen dont use autowired
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);

        if (existingChatRoom.isPresent()) {
            return existingChatRoom.map(ChatRoom::getChatId);
        } else {
            if (createNewRoomIfNotExists) {
                String chatId = createChatId(senderId, recipientId);
                return Optional.of(chatId);
            } else {
                return Optional.empty();
            }
        }
    }

    private String createChatId(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder().chatId(chatId).senderId(senderId).recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder().chatId(chatId).senderId(recipientId).recipientId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }

}