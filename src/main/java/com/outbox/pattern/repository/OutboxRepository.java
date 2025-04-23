package com.outbox.pattern.repository;

import com.outbox.pattern.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
    List<Outbox> findByProcessedFalse(); // Custom query to find unprocessed outbox messages
}
