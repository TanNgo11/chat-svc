package org.shadcn.chatsvc.service.impl;

import java.util.List;

import org.shadcn.chatsvc.entity.User;
import org.shadcn.chatsvc.enums.Status;
import org.shadcn.chatsvc.repository.UserRepository;
import org.shadcn.chatsvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService implements IUserService {
    //refactor cho nay gium nhen dont use autowired
    @Autowired
    private UserRepository repository;

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
}
