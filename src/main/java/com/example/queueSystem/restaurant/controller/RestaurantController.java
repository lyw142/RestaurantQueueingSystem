package com.example.queueSystem.restaurant.controller;

import com.example.queueSystem.restaurant.entity.OperationHours;
import com.example.queueSystem.restaurant.entity.Restaurant;
import com.example.queueSystem.restaurant.repository.RestaurantRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> fetchAllRestaurant(){
        return restaurantRepository.findAll();
    }

    @PostMapping("/createRestaurant")
    public ResponseEntity<String> createRestaurant(@RequestBody Map<String,Object> details) {
        
        String restaurantName = (String) details.get("restaurantName");
        String restaurantDescription = (String) details.get("restaurantDescription");
        String restaurantAddress = (String) details.get("restaurantAddress");
        String restaurantLogo = (String) details.get("restaurantLogo");

        // Extract operation hours
        List<Map<String, Object>> operationHoursList = (List<Map<String, Object>>) details.get("operationHours");
        List<OperationHours> operationHours = new ArrayList<>();

        for (Map<String, Object> operationHour : operationHoursList) {
            DayOfWeek day = DayOfWeek.valueOf((String) operationHour.get("day"));
            LocalTime openingTime = LocalTime.parse((String) operationHour.get("openingTime"));
            LocalTime closingTime = LocalTime.parse((String) operationHour.get("closingTime"));

            operationHours.add(new OperationHours(day, openingTime, closingTime));
        }
        
        Restaurant newRestaurant = new Restaurant(restaurantName,restaurantDescription,operationHours,restaurantAddress,restaurantLogo);

        newRestaurant = restaurantRepository.save(newRestaurant);

        if (newRestaurant != null) {
            return ResponseEntity.ok("Created Successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
    }
    
    @GetMapping("/getRestaurant/{restaurantName}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable String restaurantName) {
        Optional<Restaurant> restaurant;

        restaurant = restaurantRepository.findRestaurantByName(restaurantName);

        if(restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restaurant.get());
    }

    @PutMapping("/updateRestaurant/{name}")
    public ResponseEntity<String> updateRestaurant(@PathVariable String name, @RequestBody Map<String, Object> details) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantByName(name);

        if (!restaurantOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }

        Restaurant restaurant = restaurantOptional.get();

        // Update basic details
        if (details.containsKey("restaurantName")) {
            restaurant.setName((String) details.get("restaurantName"));
        }
        if (details.containsKey("restaurantDescription")) {
            restaurant.setDescription((String) details.get("restaurantDescription"));
        }
        if (details.containsKey("restaurantAddress")) {
            restaurant.setAddress((String) details.get("restaurantAddress"));
        }
        if (details.containsKey("restaurantLogo")) {
            restaurant.setLogo((String) details.get("restaurantLogo"));
        }

         // Update operation hours if provided
        if (details.containsKey("operationHours")) {
            List<Map<String, Object>> operationHoursList = (List<Map<String, Object>>) details.get("operationHours");
            Map<DayOfWeek, OperationHours> operationHoursMap = new HashMap<>();

            // Create a map of existing operation hours
            for (OperationHours existingOpHour : restaurant.getOperatingHours()) {
                operationHoursMap.put(existingOpHour.getDay(), existingOpHour);
            }

            // Update the map with new operation hours
            for (Map<String, Object> operationHour : operationHoursList) {
                DayOfWeek day = DayOfWeek.valueOf((String) operationHour.get("day"));
                LocalTime openingTime = LocalTime.parse((String) operationHour.get("openingTime"));
                LocalTime closingTime = LocalTime.parse((String) operationHour.get("closingTime"));

                operationHoursMap.put(day, new OperationHours(day, openingTime, closingTime));
            }

            // Convert map back to list and set it
            List<OperationHours> updatedOperationHours = new ArrayList<>(operationHoursMap.values());
            restaurant.setOperatingHours(updatedOperationHours);
        }

        // Save the updated restaurant
        restaurant = restaurantRepository.save(restaurant);

        if (restaurant != null) {
            return ResponseEntity.ok("Updated Successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update");
    }
}
