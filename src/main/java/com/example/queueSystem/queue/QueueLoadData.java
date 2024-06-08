package com.example.queueSystem.queue;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.queueSystem.queue.queue.entity.Queue;
import com.example.queueSystem.queue.queue.repository.QueueRepository;

@Component
public class QueueLoadData {
    @Autowired
    private QueueRepository queueRepository;

    @PostConstruct
    public void run() {
        try {
            //restaurantRepository.deleteAll();
            queueRepository.save(new Queue(1, 1, LocalDateTime.now(), "Pending"));
            queueRepository.save(new Queue(2, 2, LocalDateTime.now(), "Pending"));
        } catch (Exception e) {
            // Handle any exceptions that may occur during data loading
            // Log the error or perform any necessary cleanup
            e.printStackTrace();
        }
    }
}
