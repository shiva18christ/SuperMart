package com.task.Supermarket.repository;

import com.task.Supermarket.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForWA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("weatherAnalysis").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
    public List<User> getUsersForPriceUpdates(){
        Query priceQuery=new Query();
        priceQuery.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        List<User> usersForPriceUpdate = mongoTemplate.find(priceQuery, User.class);
        return usersForPriceUpdate;
    }

}
