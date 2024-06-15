package com.example.queueSystem.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Restaurant")
public class Restaurant {
    @Id
    private int restaurantID;
    private String restaurantName;

    // Get the name of the restaurant
    public String getName() {
        return restaurantName;
    }

    // Set the name of the restaurant
    public void setName(String name) {
        this.restaurantName = name;
    }
}
