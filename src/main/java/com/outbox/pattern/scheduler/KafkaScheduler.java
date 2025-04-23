package com.outbox.pattern.scheduler;

import com.outbox.pattern.producer.KafkaProducer;
import com.outbox.pattern.repository.OutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaScheduler {
    @Autowired
    private OutboxRepository outboxRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Scheduled(fixedRate = 20000) // Every 60 seconds
    public void retrieveOutboxMessageAndPublish() {
        // Retrieve unprocessed outbox messages
        var unprocessedMessages = outboxRepository.findByProcessedFalse();
        log.info("unprocessed messages count: {}", unprocessedMessages.size());

        unprocessedMessages.forEach(unprocessedMessage -> {
            try {
                kafkaProducer.send(unprocessedMessage.getPayload());

                unprocessedMessage.setProcessed(true);
                outboxRepository.save(unprocessedMessage);
            } catch (Exception e) {
                log.error("Error processing message: {}", e.getMessage());
            }
        });
    }
}
