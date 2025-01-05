package org.shadcn.chatsvc.service;


import org.shadcn.chatsvc.entity.User;

import java.util.List;

public interface IUserService {
    void saveUser(User user);

    void disconnect(User user);

    List<User> findConnectedUsers();

}
