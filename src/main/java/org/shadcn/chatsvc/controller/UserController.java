package org.shadcn.chatsvc.controller;

import org.shadcn.chatsvc.dto.ApiResponse;
import org.shadcn.chatsvc.dto.response.ConversationResponse;
import org.shadcn.chatsvc.entity.User;
import org.shadcn.chatsvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;


    @MessageMapping("/user.addUser")
    public User addUser(@Payload User user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnectUser(@Payload User user) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/api/v1/chat/users")
    public ApiResponse<List<User>> findConnectedUsers() {
        return ApiResponse.success(userService.findConnectedUsers());
    }

    @GetMapping("/api/v1/chat/conversations")
    public ApiResponse<List<ConversationResponse>> findConversations() {
        return ApiResponse.success(userService.findAllUsersInConversationList());
    }
}
