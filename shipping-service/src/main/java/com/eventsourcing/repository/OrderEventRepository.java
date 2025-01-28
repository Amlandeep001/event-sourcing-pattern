package com.eventsourcing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eventsourcing.entity.OrderEvent;

public interface OrderEventRepository extends MongoRepository<OrderEvent, String>
{
}
