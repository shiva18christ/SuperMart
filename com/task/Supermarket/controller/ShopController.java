package com.task.Supermarket.controller;

import com.task.Supermarket.Services.ShopcontrollerServices;
import com.task.Supermarket.Services.UserServices;
import com.task.Supermarket.base.Shop;
import com.task.Supermarket.base.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.LambdaUtil.getAll;

@RestController
@RequestMapping("/mall")
public class ShopController {
    @Autowired
    public ShopcontrollerServices stuff;
    @Autowired
    public UserServices userServices;


    @GetMapping
    public ResponseEntity<?> getAllProductsOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findBYUserName(userName);
        List<Shop> all = user.getShop();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Shop> getEntry(@RequestBody Shop myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            stuff.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/id/{id_no}")
    public ResponseEntity<Shop> getValueById(@PathVariable ObjectId id_no) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findBYUserName(userName);
        List<Shop> collect = user.getShop().stream().filter(x -> x.getProduct_Id().equals(id_no)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            return new ResponseEntity<>(stuff.findById(id_no), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{id_no}")
    public ResponseEntity<?> deleteValueById(@PathVariable ObjectId id_no) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        stuff.deleteById(id_no, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id_no}")
    public ResponseEntity<Shop> updateById(@PathVariable ObjectId id_no, @RequestBody Shop body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findBYUserName(userName);
        List<Shop> collect = user.getShop().stream().filter(x -> x.getProduct_Id().equals(id_no)).collect(Collectors.toList());
        if (!collect.isEmpty()) {

            Shop existing_Data = stuff.findById(id_no);
            if (existing_Data == null) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            existing_Data.setProduct_Name(body.getProduct_Name());
            existing_Data.setProduct_price(body.getProduct_price());
            stuff.saveShopEntry(existing_Data);
            return new ResponseEntity<>(existing_Data, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
