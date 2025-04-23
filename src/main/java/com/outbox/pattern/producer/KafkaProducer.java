package com.outbox.pattern.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private NewTopic createTopic;

    public void send(String payload) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(String.valueOf(createTopic.name()), payload);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Error sending message: {}" + ex.getMessage());
            } else {
                System.out.println("Message " + result.getProducerRecord().value() + " sent successfully with offset " + result.getRecordMetadata().offset());
            }
        });
    }
}
