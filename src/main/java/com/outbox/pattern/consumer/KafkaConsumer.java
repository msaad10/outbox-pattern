package com.outbox.pattern.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "unprocessed-orders", groupId = "outbox-group")
    public void consume(String payload) {
        System.out.println("Consumed message: " + payload);
    }
}
