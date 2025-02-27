package org.shadcn.chatsvc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.shadcn.chatsvc.dto.response.ConversationResponse;
import org.shadcn.chatsvc.dto.response.UserProfileResponse;
import org.shadcn.chatsvc.entity.ChatMessage;
import org.shadcn.chatsvc.entity.ChatRoom;
import org.shadcn.chatsvc.entity.User;
import org.shadcn.chatsvc.enums.Status;
import org.shadcn.chatsvc.repository.UserRepository;
import org.shadcn.chatsvc.repository.httpclient.IdentityClient;
import org.shadcn.chatsvc.service.IChatMessageService;
import org.shadcn.chatsvc.service.IChatRoomService;
import org.shadcn.chatsvc.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    //refactor cho nay gium nhen dont use autowired
    UserRepository repository;
    IChatRoomService chatRoomService;
    UserRepository userRepository;
    IdentityClient identityClient;
    IChatMessageService chatMessageService;


    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        User storedUser = repository.findById(user.getId()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllByUserIdIsIn(List<Long> ids) {
        return userRepository.findAllByUserIdIsIn(ids);
    }

    @Override
    public List<ConversationResponse> findAllUsersInConversationList() {
        UserProfileResponse currentUser = identityClient.getCurrentUserProfile().getResult();
        Long currentUserId = Long.valueOf(currentUser.getId());

        List<ChatRoom> listChatRooms = chatRoomService
                .findChatRoomsBySenderIdOrRecipientId(currentUser.getId(), currentUser.getId());

        Set<Long> userIds = listChatRooms.stream()
                .flatMap(chatRoom -> Stream.of(chatRoom.getSenderId(), chatRoom.getRecipientId()))
                .map(Long::valueOf)
                .filter(userId -> !Objects.equals(userId, currentUserId))
                .collect(Collectors.toSet());

        List<User> users = userRepository.findAllByUserIdIsIn(new ArrayList<>(userIds));

        List<ConversationResponse> conversationResponses = users.stream()
                .map(user -> {
                    ChatMessage lastMessage = chatMessageService.findLastMessageByUserId((user.getUserId()));
                    return new ConversationResponse(user.getUserId(), user.getFullName(), user.getStatus(), user.getAvatar(), lastMessage);
                })
                .collect(Collectors.toList());

        return conversationResponses;
    }

  

    @Override
    public User findByUserId(String senderId) {
        return userRepository.findByUserId(senderId);
    }

}
