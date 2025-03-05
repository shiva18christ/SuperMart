package com.task.Supermarket.repository;

import com.task.Supermarket.base.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByuserName(String userName);
    void  deleteByuserName(String userName);
}
