package com.example.queueSystem.restaurant.repository;

import com.example.queueSystem.restaurant.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Integer>{
    
}
