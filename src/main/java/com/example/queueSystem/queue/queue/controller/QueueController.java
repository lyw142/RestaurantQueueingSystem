package com.example.queueSystem.queue.queue.controller;

import com.example.queueSystem.message.TwilioConfig;
import com.example.queueSystem.queue.queue.entity.Queue;
import com.example.queueSystem.queue.queue.repository.QueueRepository;
import com.example.queueSystem.restaurant.entity.Restaurant;
import com.example.queueSystem.restaurant.repository.RestaurantRepository;
import com.example.queueSystem.user.entity.User;
import com.example.queueSystem.user.repository.UserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    public QueueController(QueueRepository queueRepository, UserRepository userRepository,
            RestaurantRepository restaurantRepository) {
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Queue> fetchAllQueue() {
        return queueRepository.findAll();
    }

    @GetMapping("/byDate")
    public List<Queue> fetchQueueByDate(@RequestBody Map<String, Object> details) {
        String dateString = (String) details.get("date");
        String restaurantName = (String) details.get("restaurantName");

        LocalDate date = LocalDate.parse(dateString); // Parse the date string from the URL

        List<Queue> queuelist = queueRepository.findQueueByQueueDate(date);

        Iterator<Queue> iterator = queuelist.iterator();
        while (iterator.hasNext()) {
            Queue queue = iterator.next();
            Restaurant restaurant = queue.getRestaurantDetails();
            if (restaurant == null || !(restaurant.getRestaurantName().equals(restaurantName))) {
                iterator.remove();
            }
        }

        return queuelist;
    }

    @GetMapping("/latestQueueNumber/{restaurantName}")
    public int fetchLatestQueueNumber(@PathVariable("restaurantName") String restaurantName) {
        List<Queue> queues = queueRepository.findQueueByQueueDate(LocalDate.now());

        if (queues.isEmpty()) {
            return 0; // Return 0 if no queues are found
        }

        int latestQueueNumber = 0;

        for (Queue queue : queues) {
            Restaurant restaurant = queue.getRestaurantDetails();
            if (restaurantName.equals(restaurant.getRestaurantName())) {
                if (queue.getQueueNo() > latestQueueNumber) {
                    latestQueueNumber = queue.getQueueNo();
                }
            }
        }

        return latestQueueNumber;
    }

    @PostMapping("/enQueue")
    public ResponseEntity<Map<String, Object>> enQueue(@RequestBody Map<String, Object> details) {
        Map<String, Object> result = new HashMap<>();

        String phoneNumber = (String) details.get("phoneNumber");
        String restaurantName = (String) details.get("restaurantName");
        int numOfPax = (int) details.get("numOfPax");
        List<Queue> queues = queueRepository.findQueueByQueueDate(LocalDate.now());

        if (!checkQueueByCustomer(phoneNumber)) {
            result.put("message", "Customer already has an existing queue and cannot join another.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        int latestQueueNumber = 0;

        if (!queues.isEmpty()) {
            for (Queue queue : queues) {
                Restaurant restaurant = queue.getRestaurantDetails();
                if (restaurantName.equals(restaurant.getName())) {
                    if (queue.getQueueNo() > latestQueueNumber) {
                        latestQueueNumber = queue.getQueueNo();
                    }
                }
            }
        }

        // User user = userRepository.findById(phoneNumber).get();

        Restaurant restaurant = restaurantRepository.findRestaurantByName(restaurantName).get();

        Queue newQueue = new Queue(latestQueueNumber + 1, LocalDate.now(), LocalTime.now(), numOfPax, "Waiting",
                restaurant, phoneNumber);

        newQueue = queueRepository.save(newQueue);

        if (newQueue != null && newQueue.getQueueId() != null) {
            // Add the user to the queue
            // TwilioConfig twilio = new TwilioConfig();
            // twilio.SendSMS(restaurant.getRestaurantName(), String.valueOf(newQueue.getQueueNo()), "10 minutes",
            //         "+65" + phoneNumber);
            // twilio.SendWhatsappMessage(restaurant.getRestaurantName(), String.valueOf(newQueue.getQueueNo()),
            //         "10 minutes", "+65" + phoneNumber);
            result.put("queueNo", newQueue.getQueueNo());
            result.put("waitingTime", newQueue.getWaitingTime());
            result.put("status", newQueue.getQueueStatus());
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    public boolean checkQueueByCustomer(String phoneNumber) {
        // Possible value of queue status:
        // Waiting, In Service, Completed, Cancelled, No Show

        // Define the statuses we're interested in
        List<String> statuses = Arrays.asList("In Service", "Waiting");

        // Query the database for these statuses
        List<Queue> queue = queueRepository.findQueueByPhoneNumberAndStatus(statuses, phoneNumber);
        System.out.println(queue.isEmpty());

        // If the list is empty, return true, otherwise return false
        return queue.isEmpty();
    }

    @PutMapping("/updateQueue")
    public ResponseEntity<String> updateQueue(@RequestBody Map<String, Object> details) {
        String queueId = (String) details.get("queueId");

        // Retrieve the existing queue from the repository
        Optional<Queue> optionalQueue = queueRepository.findById(queueId);
        if (!optionalQueue.isPresent()) {
            return ResponseEntity.badRequest().body("Queue ID not found");
        }

        Queue existingQueue = optionalQueue.get();

        // Check and update numOfPax if present
        if (details.containsKey("numOfPax")) {
            int numOfPax = (int) details.get("numOfPax");
            if (numOfPax > 0) {
                existingQueue.setQueuePax(numOfPax);
            }
        }

        // Check and update status if present
        if (details.containsKey("queueStatus")) {
            String status = (String) details.get("queueStatus");
            if (status != null && !status.isEmpty()) {
                existingQueue.setQueueStatus(status);
            }
        }

        queueRepository.save(existingQueue);

        return ResponseEntity.ok("Update Successful");
    }
}
