package com.eventsourcing.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.eventsourcing.entity.OrderEvent;

@Component
public class OrderEventKafkaPublisher
{
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String topicName;

    public OrderEventKafkaPublisher(KafkaTemplate<String, OrderEvent> kafkaTemplate,
                                    @Value("${order.event.topicName}") String topicName)
    {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }


    public void sendOrderEvent(OrderEvent orderEvent)
    {
        kafkaTemplate.send(topicName, orderEvent);
    }
}
