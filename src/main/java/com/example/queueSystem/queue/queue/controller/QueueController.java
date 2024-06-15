package com.example.queueSystem.queue.queue.controller;

import com.example.queueSystem.queue.queue.entity.Queue;
import com.example.queueSystem.queue.queue.repository.QueueRepository;
import com.example.queueSystem.restaurant.entity.Restaurant;
import com.example.queueSystem.restaurant.repository.RestaurantRepository;
import com.example.queueSystem.user.entity.User;
import com.example.queueSystem.user.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/queue")
public class QueueController {
    @Autowired
    private final QueueRepository queueRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    public QueueController(QueueRepository queueRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Queue> fetchAllQueue(){
        return queueRepository.findAll();
    }

    @GetMapping("/byDate/{date}")
    public List<Queue> fetchQueueByDate(@PathVariable("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString); // Parse the date string from the URL
        return queueRepository.findQueueByQueueDate(date);
    }

    @GetMapping("/latestQueueNumber/{restaurantID}")
    public int fetchLatestQueueNumber(@PathVariable("restaurantID") Integer restaurantID) {
        List<Queue> queues =  queueRepository.findQueueByQueueDate(LocalDate.now());
        
        if (queues.isEmpty()) {
            return 0; // Return 0 if no queues are found
        }

        int latestQueueNumber = 0;

        for (Queue queue : queues) {
            Restaurant restaurant = queue.getRestaurantDetails();
            if (restaurantID == restaurant.getRestaurantID()) {
                if (queue.getQueueNo() > latestQueueNumber) {
                    latestQueueNumber = queue.getQueueNo();
                }
            }
        }

        return latestQueueNumber;
    }

    @GetMapping("/enQueue")
    public ResponseEntity<Integer> enQueue(@RequestBody Map<String, Object> details) {
        int phoneNumber = (int) details.get("phoneNumber");
        int restaurantID = (int) details.get("restaurantID");
        System.out.println(phoneNumber);
        System.out.println(restaurantID);
        List<Queue> queues =  queueRepository.findQueueByQueueDate(LocalDate.now());
        
        int latestQueueNumber = 0;

        if (!queues.isEmpty()) {
            for (Queue queue : queues) {
                Restaurant restaurant = queue.getRestaurantDetails();
                if (restaurantID == restaurant.getRestaurantID()) {
                    if (queue.getQueueNo() > latestQueueNumber) {
                        latestQueueNumber = queue.getQueueNo();
                    }
                }
            }
        }

        User user = userRepository.findById(phoneNumber).get();

        Restaurant restaurant = restaurantRepository.findById(restaurantID).get();

        Queue newQueue = new Queue(latestQueueNumber + 1, LocalDate.now() , LocalTime.now() ,"Pending" , restaurant , user);
        
        newQueue = queueRepository.save(newQueue);

        if (newQueue != null && newQueue.getQueueId() != null) {
            // Add the user to the queue
            return ResponseEntity.ok(newQueue.getQueueNo());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);
    }
}
