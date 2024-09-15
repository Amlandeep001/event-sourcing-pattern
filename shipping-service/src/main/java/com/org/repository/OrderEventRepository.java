package com.org.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.org.entity.OrderEvent;

public interface OrderEventRepository extends MongoRepository<OrderEvent, String>
{
}
