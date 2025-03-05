package com.task.Supermarket.repository;


import com.task.Supermarket.base.Shop;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends MongoRepository<Shop, ObjectId> {

}
