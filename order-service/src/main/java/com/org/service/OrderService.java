package com.org.service;

import com.org.dto.enums.OrderStatus;
import com.org.dto.request.OrderRequest;
import com.org.dto.response.OrderResponse;
import com.org.entity.OrderEvent;
import com.org.publisher.OrderEventKafkaPublisher;
import com.org.repository.OrderEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService
{
    private final OrderEventRepository repository;
    private final OrderEventKafkaPublisher publisher;

    public OrderService(OrderEventRepository repository, OrderEventKafkaPublisher publisher)
    {
        this.repository = repository;
        this.publisher = publisher;
    }

    // Handle order creation
    public OrderResponse placeAnOrder(OrderRequest orderRequest)
    {
        String orderId = UUID.randomUUID().toString().split("-")[0];
        orderRequest.setOrderId(orderId);
        //do request validation and real business logic
        //save that event and publish kafka messages
        OrderEvent orderEvent = createOrderEvent(orderId, OrderStatus.CREATED, "Order created successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CREATED);
    }

    // Handle order confirmation
    public OrderResponse confirmOrder(String orderId)
    {
        OrderEvent orderEvent = createOrderEvent(orderId, OrderStatus.CONFIRMED, "Order confirmed successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CONFIRMED);
    }

    private OrderEvent createOrderEvent(String orderId, OrderStatus status, String details, LocalDateTime eventTimestamp)
    {
        return OrderEvent.builder()
                .orderId(orderId)
                .status(status)
                .details(details)
                .eventTimestamp(eventTimestamp)
                .build();
    }

    private void saveAndPublishEvents(OrderEvent orderEvent)
    {
        repository.save(orderEvent);
        publisher.sendOrderEvent(orderEvent);
    }

}
