package org.shadcn.chatsvc.repository;

import org.shadcn.chatsvc.entity.User;
import org.shadcn.chatsvc.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;



import java.util.List;



public interface UserRepository extends MongoRepository<User, String>{
    List<User> findAllByStatus(Status status);
}