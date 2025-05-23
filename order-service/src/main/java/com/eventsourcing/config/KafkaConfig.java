package com.eventsourcing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig
{
    private final String topicName;

    public KafkaConfig(@Value("${order.event.topicName}") String topicName)
    {
        this.topicName = topicName;
    }
    
    @Bean
    NewTopic createTopic()
    {
        return new NewTopic(topicName, 3, (short) 1);
    }
}
