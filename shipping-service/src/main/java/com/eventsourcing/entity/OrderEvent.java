package com.eventsourcing.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.eventsourcing.dto.enums.OrderStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Document(collection = "OrderEvents")
public class OrderEvent
{
	@Id
	String id;
	String orderId;
	OrderStatus status; // SHIPPED, DELIVERED
	String details;
	LocalDateTime eventTimestamp;

	public OrderEvent(String orderId, OrderStatus status, String details, LocalDateTime eventTimestamp)
	{
		this.orderId = orderId;
		this.status = status;
		this.details = details;
		this.eventTimestamp = eventTimestamp;
	}
}
