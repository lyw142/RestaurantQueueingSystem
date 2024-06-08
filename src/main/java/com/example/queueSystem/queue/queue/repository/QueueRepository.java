package com.example.queueSystem.queue.queue.repository;

import com.example.queueSystem.queue.queue.entity.Queue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends MongoRepository<Queue, Integer>{
}
