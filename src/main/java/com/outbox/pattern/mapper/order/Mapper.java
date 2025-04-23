package com.outbox.pattern.mapper.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.outbox.pattern.dto.OrderRequestDto;
import com.outbox.pattern.entity.Order;
import com.outbox.pattern.entity.Outbox;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Mapper {
    public Order orderRequestDtoToOrderEntity(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .name(orderRequestDto.getName())
                .customerId(orderRequestDto.getCustomerId())
                .productType(orderRequestDto.getProductType())
                .quantity(orderRequestDto.getQuantity())
                .price(orderRequestDto.getPrice())
                .build();
    }

    public Outbox orderEntityToOutboxEntity(Order order) {
        try {
            return Outbox.builder()
                    .aggregateId(order.getId().toString())
                    .payload(new ObjectMapper().writeValueAsString(order))
                    .createdAt(new Date())
                    .processed(false)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
