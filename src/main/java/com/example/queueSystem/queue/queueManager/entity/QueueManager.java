package com.example.queueSystem.queue.queueManager.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.queueSystem.queue.queue.entity.Queue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "QueueManager")
public class QueueManager {
    private List<Queue> queueList = new ArrayList<>();

    // Create
    public void addQueue(Queue queue) {
        queueList.add(queue);
    }

    // Read
    public Queue getQueue(int queueId) {
        for (Queue queue : queueList) {
            if (queue.getQueueId() == queueId) {
                return queue;
            }
        }
        return null;
    }

    public List<Queue> getAllQueues() {
        return new ArrayList<>(queueList);
    }

    // Update
    public boolean updateQueue(int queueId, int queueNo, LocalDateTime queueDateTime, String queueStatus) {
        for (Queue queue : queueList) {
            if (queue.getQueueId() == queueId) {
                queue.setQueueNo(queueNo);
                queue.setQueueDateTime(queueDateTime);
                queue.setQueueStatus(queueStatus);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean deleteQueue(int queueId) {
        Iterator<Queue> iterator = queueList.iterator();
        while (iterator.hasNext()) {
            Queue queue = iterator.next();
            if (queue.getQueueId() == queueId) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
