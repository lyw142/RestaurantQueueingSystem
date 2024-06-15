package com.example.queueSystem.queue.queue.repository;

import com.example.queueSystem.queue.queue.entity.Queue;

import java.time.*;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends MongoRepository<Queue, Integer>{
     /**
     * Find queue by date.
     * @param date The date to search for.
     * @return A list of queues matching the given date.
     */
    @Query("{ 'queueDate' : ?0 }")
    List<Queue> findQueueByQueueDate(LocalDate date);
}
