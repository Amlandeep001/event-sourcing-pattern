package com.org.service;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.org.dto.enums.OrderStatus;
import com.org.entity.OrderEvent;
import com.org.repository.OrderEventRepository;

@Service
public class ShippingEventService
{
	private final OrderEventRepository repository;

	public ShippingEventService(OrderEventRepository repository)
	{
		this.repository = repository;
	}

	@KafkaListener(topics = "order-events", groupId = "shipping-service")
	public void consumeOrderEvent(OrderEvent orderEvent)
	{
		if(orderEvent.getStatus().equals(OrderStatus.CONFIRMED))
		{
			// Automatically ship after order confirmation
			shipOrder(orderEvent.getOrderId());
		}
	}

	// Ship the order
	public void shipOrder(String orderId)
	{
		OrderEvent orderEvent = createOrderEvent(orderId, OrderStatus.SHIPPED, "Order Shipped successfully", LocalDateTime.now());
		repository.save(orderEvent);
	}

	// Deliver the order
	public void deliverOrder(String orderId)
	{
		OrderEvent orderEvent = createOrderEvent(orderId, OrderStatus.DELIVERED, "Order delivered successfully", LocalDateTime.now());
		repository.save(orderEvent);
	}

	/*private void saveAndPublishShippingEvent(Object event)
	{
	}*/

	private OrderEvent createOrderEvent(String orderId, OrderStatus status, String details, LocalDateTime eventTimestamp)
	{
		return OrderEvent.builder()
				.orderId(orderId)
				.status(status)
				.details(details)
				.eventTimestamp(eventTimestamp)
				.build();
	}
}
