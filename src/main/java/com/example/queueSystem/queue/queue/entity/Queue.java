package com.example.queueSystem.queue.queue.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.queueSystem.restaurant.entity.Restaurant;
import com.example.queueSystem.user.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Queue")
public class Queue {
    @Id
    private int queueId;
    private int queueNo;
    private LocalDateTime queueDateTime;
    private String queueStatus;
    private Restaurant restaurantDetails;
    private User userDetails;
    
    private Map<Integer,Integer> list = new TreeMap<>();

    public Queue(int queueId, int queueNo, LocalDateTime queueDateTime, String queueStatus) {
        this.queueId = queueId;
        this.queueNo = queueNo;
        this.queueDateTime = queueDateTime;
        this.queueStatus = queueStatus;
    }

    // Getters and Setters
    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public int getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(int queueNo) {
        this.queueNo = queueNo;
    }

    public LocalDateTime getQueueDateTime() {
        return queueDateTime;
    }

    public void setQueueDateTime(LocalDateTime queueDateTime) {
        this.queueDateTime = queueDateTime;
    }

    public String getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(String queueStatus) {
        this.queueStatus = queueStatus;
    }

    // Return a string representation of the queue
    public String toString() {
        return list.toString();
    }
}