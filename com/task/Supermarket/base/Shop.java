package com.task.Supermarket.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import java.time.LocalDateTime;

@Document("Shops")
@Data
@NoArgsConstructor

public class Shop {
    @Id
    private ObjectId product_Id;
    private String product_Name;
    private double product_price;
    private LocalDateTime date;


}
