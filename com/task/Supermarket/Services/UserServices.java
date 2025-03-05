package com.task.Supermarket.Services;

import com.task.Supermarket.base.User;
import com.task.Supermarket.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserServices {
    @Autowired
    private UserRepo service;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<User> getAll() {
        return service.findAll();
    }

    public void saveEntry(User data) {
        service.save(data);
    }

    public void saveNewUser(User data) {
        try {
            data.setPassword(passwordEncoder.encode(data.getPassword()));
            data.setRoles(Arrays.asList("User"));
            service.save(data);
        } catch (Exception e) {
            log.error("User with userName {} already exists", data.getUserName());
            log.debug("User with userName {} already exists", data.getUserName());
            log.info("User with userName {} already exists", data.getUserName());
            log.trace("User with userName {} already exists", data.getUserName());
            log.warn("User with userName {} already exists", data.getUserName());


        }
    }

    public void saveNewAdmin(User data) {
        data.setPassword(passwordEncoder.encode(data.getPassword()));
        data.setRoles(Arrays.asList("User", "ADMIN"));
        service.save(data);
    }

    public Optional<User> findById(ObjectId id) {
        return service.findById(id);
    }

    public void deleteById(ObjectId id) {
        service.deleteById(id);
    }

    public User findBYUserName(String userName) {
        return service.findByuserName(userName);

    }


}
