package com.example.queueSystem.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Restaurant")
public class Restaurant {
    @Id
    private String restaurantID;
    private String restaurantName;
    private String restaurantDescription;
    private List<OperationHours> restaurantOperationHours;
    private String restaurantAddress;
    private String restaurantLogo;
    
    @LastModifiedDate
    private LocalDateTime writeTime;

    public Restaurant(String restaurantName, String restaurantDescription,
            List<OperationHours> restaurantOperationHours, String restaurantAddress, String restaurantLogo) {
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantOperationHours = restaurantOperationHours;
        this.restaurantAddress = restaurantAddress;
        this.restaurantLogo = restaurantLogo;
        this.writeTime = LocalDateTime.now();
    }

    private void updateWriteTime() {
        this.writeTime = LocalDateTime.now();
    }

    // Get the name of the restaurant
    public String getName() {
        return restaurantName;
    }

    // Set the name of the restaurant
    public void setName(String name) {
        this.restaurantName = name;
        updateWriteTime();
    }
    
    // Get the description of the restaurant
    public String getDescription() {
        return restaurantDescription;
    }

    // Set the description of the restaurant
    public void setDescription(String description) {
        this.restaurantDescription = description;
        updateWriteTime();
    }

     // Get the operation hours of the restaurant
     public List<OperationHours> getOperatingHours() {
        return restaurantOperationHours;
    }

    // Set the operation hours of the restaurant
    public void setOperatingHours(List<OperationHours> restaurantOperationHours) {
        this.restaurantOperationHours = restaurantOperationHours;
        updateWriteTime();
    }

    // Get the address of the restaurant
    public String getAddress() {
        return restaurantAddress;
    }

    // Set the address of the restaurant
    public void setAddress(String address) {
        this.restaurantAddress = address;
        updateWriteTime();
    }

    // Get the logo of the restaurant
    public String getLogo() {
        return restaurantLogo;
    }

    // Set the logo of the restaurant
    public void setLogo(String logo) {
        this.restaurantLogo = logo;
        updateWriteTime();
    }
}
