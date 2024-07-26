package com.example.queueSystem.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.queueSystem.queue.queue.entity.Queue;
import com.example.queueSystem.restaurant.entity.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    private Integer phoneNumber;
    private String email;

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
