package com.outbox.pattern.repository;

import com.outbox.pattern.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods can be defined here if needed
}
