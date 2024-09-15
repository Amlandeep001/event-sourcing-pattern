package com.org.entity;

import com.org.dto.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@Document(collection = "OrderEvents")
public class OrderEvent
{
    @Id
    String id;
    String orderId;
    OrderStatus status;  // CREATED, CONFIRMED
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
