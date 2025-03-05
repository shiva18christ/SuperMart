package com.task.Supermarket.Services;

import com.task.Supermarket.base.User;

import com.task.Supermarket.repository.ShopRepo;
import com.task.Supermarket.base.Shop;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ShopcontrollerServices {
    @Autowired
    private ShopRepo stuff;
    @Autowired
    private UserServices userServices;



    @Transactional
    public void saveEntry(Shop data, String userName) {
        User user = userServices.findBYUserName(userName);
        Shop saved = stuff.save(data);
        user.getShop().add(saved);
        userServices.saveEntry(user);
    }

    public void saveShopEntry(Shop data) {

        stuff.save(data);
    }

    public List<Shop> getAll() {
        return stuff.findAll();
    }

    public Shop findById(ObjectId id) {
        return stuff.findById(id).get();
    }

    @Transactional
    public void deleteById(ObjectId id, String userName) {
        User user = userServices.findBYUserName(userName);
        boolean removed = user.getShop().removeIf(x -> x.getProduct_Id().equals(id));
        if (removed) {
            userServices.saveEntry(user);
            stuff.deleteById(id);
        }
        System.out.println("An error occurred while deleting the list");
    }

    public Shop updateByID(ObjectId id, Shop updated_Data) {
        Shop existing_data = findById(id);
        existing_data.setProduct_Name(updated_Data.getProduct_Name());
        existing_data.setProduct_price(updated_Data.getProduct_price());
        existing_data.setDate(LocalDateTime.now());
        return stuff.save(existing_data);


    }


}