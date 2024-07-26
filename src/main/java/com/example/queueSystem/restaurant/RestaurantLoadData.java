package com.example.queueSystem.restaurant;

import jakarta.annotation.PostConstruct;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.queueSystem.restaurant.entity.OperationHours;
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
            
            OperationHours op1 = new OperationHours(DayOfWeek.of(1), LocalTime.of(11, 00), LocalTime.of(22,00));
            OperationHours op2 = new OperationHours(DayOfWeek.of(2), LocalTime.of(11, 00), LocalTime.of(22,00));
            OperationHours op3 = new OperationHours(DayOfWeek.of(3), LocalTime.of(11, 00), LocalTime.of(22,00));
            OperationHours op4 = new OperationHours(DayOfWeek.of(4), LocalTime.of(11, 00), LocalTime.of(22,00));
            OperationHours op5 = new OperationHours(DayOfWeek.of(5), LocalTime.of(11, 00), LocalTime.of(22,00));
            OperationHours op6 = new OperationHours(DayOfWeek.of(6), LocalTime.of(10, 30), LocalTime.of(22,30));
            OperationHours op7 = new OperationHours(DayOfWeek.of(7), LocalTime.of(10, 30), LocalTime.of(22,30));

            List<OperationHours> opList = new ArrayList<>();
            opList.add(op1);
            opList.add(op2);
            opList.add(op3);
            opList.add(op4);
            opList.add(op5);
            opList.add(op6);
            opList.add(op7);

            String restDescription = "Din Tai Fung is a globally renowned restaurant chain known for its exquisite Taiwanese cuisine, particularly its xiao long bao (soup dumplings). Founded in 1972 in Taipei, the restaurant is celebrated for its meticulous preparation and high-quality ingredients. With locations worldwide, Din Tai Fung offers an elegant dining experience, combining traditional flavors with modern presentation.";
            restaurantRepository.save(new Restaurant("Din Tai Fung",restDescription,opList,"238 Thomson Rd, #01-05, Singapore 307683","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.facebook.com%2Fdintaifungsg%2F&psig=AOvVaw1wt80Ejhdl49e5OeJSDXDu&ust=1722048830921000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIDVpPnZw4cDFQAAAAAdAAAAABAE"));
            restaurantRepository.save(new Restaurant("Swensen","",opList,"",""));
            restaurantRepository.save(new Restaurant("Pappa Rich","",opList,"",""));
        } catch (Exception e) {
            // Handle any exceptions that may occur during data loading
            // Log the error or perform any necessary cleanup
            e.printStackTrace();
        }
    }
}
