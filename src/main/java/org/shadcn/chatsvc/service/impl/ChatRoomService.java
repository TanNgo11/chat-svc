package org.shadcn.chatsvc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.shadcn.chatsvc.entity.ChatRoom;
import org.shadcn.chatsvc.repository.ChatRoomRepository;
import org.shadcn.chatsvc.service.IChatRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatRoomService implements IChatRoomService {
    // refactor cho nay gium nhen dont use autowired
    ChatRoomRepository chatRoomRepository;

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

    @Override
    public List<ChatRoom> findChatRoomsBySenderIdOrRecipientId(String senderId, String recipientId) {
        return chatRoomRepository.findBySenderIdOrRecipientId(senderId, recipientId);
    }

    private String createChatId(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }
}
