package com.example.queueSystem.restaurant.repository;

import com.example.queueSystem.restaurant.entity.Restaurant;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String>{
     /**
     * Find restaurant by name.
     * @param name to search for.
     * @return A restaurant matching the given name.
     */
    @Query("{ 'restaurantName' : ?0 }")
    Optional<Restaurant> findRestaurantByName(String name);
}
