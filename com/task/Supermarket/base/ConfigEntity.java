package com.task.Supermarket.base;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_shop_app")
@Data
public class ConfigEntity {
    private String key;
    private String value;

}
