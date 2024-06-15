package com.example.queueSystem.queue.queue.entity;

import java.time.*;
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
    private String queueId;
    private int queueNo;
    private LocalDate queueDate;
    private LocalTime queueTime;
    private String queueStatus;
    private Restaurant restaurantDetails;
    private User userDetails;

    public Queue(int queueNo, LocalDate queueDate, LocalTime queueTime, String queueStatus) {
        this.queueId = String.valueOf(queueDate) + String.valueOf(queueNo) + String.valueOf(queueTime);
        this.queueNo = queueNo;
        this.queueDate = queueDate;
        this.queueTime = queueTime;
        this.queueStatus = queueStatus;
    }

    public Queue(int queueNo, LocalDate queueDate, LocalTime queueTime, String queueStatus, Restaurant restaurant, User user) {
        this.queueId = String.valueOf(queueDate) + String.valueOf(queueNo) + String.valueOf(queueTime);
        this.queueNo = queueNo;
        this.queueDate = queueDate;
        this.queueTime = queueTime;
        this.queueStatus = queueStatus;
        this.userDetails = user;
        this.restaurantDetails = restaurant;
    }

    // Getters and Setters
    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public int getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(int queueNo) {
        this.queueNo = queueNo;
    }

    public LocalDate getQueueDate() {
        return queueDate;
    }

    public void setQueueDate(LocalDate queueDate) {
        this.queueDate = queueDate;
    }

    public LocalTime getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(LocalTime queueTime) {
        this.queueTime = queueTime;
    }

    public String getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(String queueStatus) {
        this.queueStatus = queueStatus;
    }
}