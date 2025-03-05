package com.task.Supermarket.Schduler;

import com.task.Supermarket.Services.EmailService;
import com.task.Supermarket.base.Shop;
import com.task.Supermarket.base.User;
import com.task.Supermarket.repository.UserRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSchduler {
    private static final Logger log = LoggerFactory.getLogger(UserSchduler.class);
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;

    @Scheduled(cron = "0 0 10 * * SUN")
//    @Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSendMail() {
        try {
            List<User> userList = userRepository.getUsersForPriceUpdates();
            for (User user : userList) {
                List<Shop> shopList = user.getShop();
                List<Double> lastWeekShopListPrice = shopList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getProduct_price()).collect(Collectors.toList());
                double totalPrice = lastWeekShopListPrice.stream().mapToDouble(Double::doubleValue).sum();
                List<String> lastWeekShopListName = shopList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getProduct_Name()).collect(Collectors.toList());
                emailService.mailSender(user.getEmail(), "Total cost of shopping lastWeek", "Your last week shopping list is:" +
                        lastWeekShopListName + " " +
                        "the total price of all the shopping you did last week was:" + totalPrice);
            }


        } catch (Exception e) {
            log.error("Can't perform functions" + e.getMessage());
        }
    }
}
