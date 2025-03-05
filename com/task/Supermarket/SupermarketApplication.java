package com.task.Supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "com.task.Supermarket.repository")
@EnableScheduling
public class SupermarketApplication {

    public static void main(String[] args) {

        SpringApplication.run(SupermarketApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
