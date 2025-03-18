package org.shadcn.chatsvc.service;

import java.util.List;

import org.shadcn.chatsvc.dto.request.DisconnectedUserPayload;
import org.shadcn.chatsvc.dto.response.ConversationResponse;
import org.shadcn.chatsvc.entity.User;

public interface IUserService {
    ConversationResponse saveUser(User user);

    ConversationResponse disconnect(DisconnectedUserPayload userId);

    List<User> findConnectedUsers();


    List<User> findAllByUserIdIsIn(List<String> ids);

    List<ConversationResponse> findAllUsersInConversationList();
    
    User findByUserId(String senderId);
}
