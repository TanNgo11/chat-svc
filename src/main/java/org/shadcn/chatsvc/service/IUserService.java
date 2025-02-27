package org.shadcn.chatsvc.service;

import java.util.List;

import org.shadcn.chatsvc.dto.response.ConversationResponse;
import org.shadcn.chatsvc.entity.User;

public interface IUserService {
    void saveUser(User user);

    void disconnect(User user);

    List<User> findConnectedUsers();


    List<User> findAllByUserIdIsIn(List<Long> ids);

    List<ConversationResponse> findAllUsersInConversationList();
    
    User findByUserId(String senderId);
}
