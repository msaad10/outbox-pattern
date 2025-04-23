package com.outbox.pattern.service;

import com.outbox.pattern.dto.OrderRequestDto;
import com.outbox.pattern.entity.Order;
import com.outbox.pattern.mapper.order.Mapper;
import com.outbox.pattern.repository.OrderRepository;
import com.outbox.pattern.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private Mapper mapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OutboxRepository outboxRepository;

    @Transactional
    public Order createOrder(OrderRequestDto orderRequestDto) {
       Order order = orderRepository.save(mapper.orderRequestDtoToOrderEntity(orderRequestDto));
        outboxRepository.save(mapper.orderEntityToOutboxEntity(order));
        return order;
    }
}
