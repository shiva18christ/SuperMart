package com.task.Supermarket.Cache;

import com.task.Supermarket.base.ConfigEntity;
import com.task.Supermarket.repository.ConfigShopAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    public ConfigShopAppRepo conRepo;

    public Map<String, String> APP_CACHE ;

    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
        List<ConfigEntity> all = conRepo.findAll();
        for (ConfigEntity configEntity : all) {
            APP_CACHE.put(configEntity.getKey(), configEntity.getValue());

        }
    }

}
