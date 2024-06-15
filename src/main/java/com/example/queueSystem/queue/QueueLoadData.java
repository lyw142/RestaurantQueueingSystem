package com.example.queueSystem.queue;

import jakarta.annotation.PostConstruct;

import java.time.*;

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
            queueRepository.deleteAll();
            queueRepository.save(new Queue(1, LocalDate.now(), LocalTime.now() ,"Completed"));
            queueRepository.save(new Queue(2, LocalDate.now(), LocalTime.now() ,"Completed"));
            queueRepository.save(new Queue(3, LocalDate.now(), LocalTime.now() ,"In Queue"));
            queueRepository.save(new Queue(4, LocalDate.now(), LocalTime.now() ,"In Queue"));
            queueRepository.save(new Queue(5, LocalDate.now(), LocalTime.now() ,"Cancelled"));
        } catch (Exception e) {
            // Handle any exceptions that may occur during data loading
            // Log the error or perform any necessary cleanup
            e.printStackTrace();
        }
    }
}
