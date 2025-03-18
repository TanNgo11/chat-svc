package org.shadcn.chatsvc.repository;

import java.util.List;

import org.apache.el.stream.Stream;
import org.shadcn.chatsvc.entity.User;
import org.shadcn.chatsvc.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);

    List<User> findAllByUserIdIsIn(List<String> userId);

    User findByUserId(String senderId);
}
