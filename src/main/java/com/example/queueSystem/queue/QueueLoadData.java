package com.example.queueSystem.queue;

import jakarta.annotation.PostConstruct;

import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.queueSystem.queue.queue.entity.Queue;
import com.example.queueSystem.queue.queue.repository.QueueRepository;
import com.example.queueSystem.restaurant.entity.Restaurant;
import com.example.queueSystem.restaurant.repository.RestaurantRepository;
import com.example.queueSystem.user.entity.User;
import com.example.queueSystem.user.repository.UserRepository;

@Component
public class QueueLoadData {
    @Autowired
    private QueueRepository queueRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    public QueueLoadData(QueueRepository queueRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostConstruct
    public void run() {
        try {
            queueRepository.deleteAll();
            Restaurant restaurant1 = restaurantRepository.findById(1).get();
            Restaurant restaurant2 = restaurantRepository.findById(2).get();
            //User user1 = userRepository.findById(93293819).get();
            //User user2 = userRepository.findById(82371231).get();
            queueRepository.save(new Queue(1, LocalDate.now(), LocalTime.now() , 2, "Completed", restaurant1, "98856231"));
            queueRepository.save(new Queue(2, LocalDate.now(), LocalTime.now() , 3, "Completed", restaurant1, "98765432"));
            queueRepository.save(new Queue(1, LocalDate.now(), LocalTime.now() , 5, "In Queue", restaurant2, "65432109"));
            //queueRepository.save(new Queue(4, LocalDate.now(), LocalTime.now() ,"In Queue"));
            //queueRepository.save(new Queue(5, LocalDate.now(), LocalTime.now() ,"Cancelled"));
        } catch (Exception e) {
            // Handle any exceptions that may occur during data loading
            // Log the error or perform any necessary cleanup
            e.printStackTrace();
        }
    }
}
