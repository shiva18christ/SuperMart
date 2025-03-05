package com.task.Supermarket.repository;

import com.task.Supermarket.base.ConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigShopAppRepo extends MongoRepository<ConfigEntity, ObjectId> {

}
