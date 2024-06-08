package com.example.queueSystem.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.queueSystem.queue.queue.entity.Queue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Restaurant")
public class Restaurant {
    @Id
    private int restaurantID;
    private String restaurantName;
    //private Queue customerQueue;

    // Get the name of the restaurant
    public String getName() {
        return restaurantName;
    }

    // Set the name of the restaurant
    public void setName(String name) {
        this.restaurantName = name;
    }

    // Add a customer to the queue
    //public void addCustomer(int PhoneNumber, int numOfPax) {
    //    customerQueue.enqueue(PhoneNumber, numOfPax);
    //}

    // Remove a customer from the queue
    //public Boolean removeCustomer(int PhoneNumber) {
    //    return customerQueue.dequeue(PhoneNumber);
    //}

    // Check the current queue status
    //public void checkQueueStatus() {
    //    if (customerQueue.isEmpty()) {
    //        System.out.println("The queue is currently empty.");
    //    } else {
    //        System.out.println("Current queue: " + customerQueue);
    //    }
    //}
}
