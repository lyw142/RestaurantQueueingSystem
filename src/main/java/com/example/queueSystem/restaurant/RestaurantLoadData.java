package com.example.queueSystem.restaurant;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.queueSystem.restaurant.entity.Restaurant;
import com.example.queueSystem.restaurant.repository.RestaurantRepository;

@Component
public class RestaurantLoadData {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostConstruct
    public void run() {
        try {
            restaurantRepository.deleteAll();
            restaurantRepository.save(new Restaurant(1, "Din Tai Fung"));
            restaurantRepository.save(new Restaurant(2, "Swensen"));
            restaurantRepository.save(new Restaurant(3, "Pappa Rich"));
        } catch (Exception e) {
            // Handle any exceptions that may occur during data loading
            // Log the error or perform any necessary cleanup
            e.printStackTrace();
        }
    }
}
