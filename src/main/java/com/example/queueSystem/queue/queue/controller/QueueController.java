package com.example.queueSystem.queue.queue.controller;

import com.example.queueSystem.queue.queue.entity.Queue;
import com.example.queueSystem.queue.queue.repository.QueueRepository;

import java.util.List;
import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/queue")
public class QueueController {
    @Autowired
    private final QueueRepository queueRepository;

    public QueueController(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
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
}
