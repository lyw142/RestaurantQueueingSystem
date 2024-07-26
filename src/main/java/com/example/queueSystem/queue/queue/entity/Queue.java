package com.example.queueSystem.queue.queue.entity;

import java.time.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.queueSystem.restaurant.entity.Restaurant;
import com.example.queueSystem.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int waitingTime;
    private int queuePax;
    private String queueStatus;
    private String userPhoneNum;

    @DBRef(lazy = true)
    @JsonIgnore
    private Restaurant restaurantDetails;
    
    // @DBRef(lazy = true)
    // @JsonIgnore
    // private User userDetails;

    public Queue(int queueNo, LocalDate queueDate, LocalTime queueTime, int queuePax, String queueStatus, Restaurant restaurant, String userPhoneNum) {
        this.queueId = String.valueOf(queueDate) + String.valueOf(queueNo) + String.valueOf(queueTime);
        this.queueNo = queueNo;
        this.queueDate = queueDate;
        this.queueTime = queueTime;
        this.queuePax = queuePax;
        this.queueStatus = queueStatus;
        this.waitingTime = 10;
        this.userPhoneNum = userPhoneNum;
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

    public int getQueuePax() {
        return queuePax;
    }

    public void setQueuePax(int queuePax) {
        this.queuePax = queuePax;
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

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(String queueStatus) {
        this.queueStatus = queueStatus;
    }

    public String getPhoneNum() {
        return userPhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.userPhoneNum = phoneNum;
    }

    public Restaurant getRestaurantDetails() {
        return restaurantDetails;
    }
}