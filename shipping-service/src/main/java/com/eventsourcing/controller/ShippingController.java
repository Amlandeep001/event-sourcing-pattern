package com.eventsourcing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventsourcing.service.ShippingEventService;

@RestController
@RequestMapping("/shipping")
public class ShippingController
{
	private final ShippingEventService shippingEventService;

	public ShippingController(ShippingEventService shippingEventService)
	{
		this.shippingEventService = shippingEventService;
	}

	// Endpoint to ship an order
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<String> shipOrder(@PathVariable String orderId)
	{
		shippingEventService.shipOrder(orderId);
		return ResponseEntity.ok("Order shipped successfully.");
	}

	// Endpoint to deliver an order
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<String> deliverOrder(@PathVariable String orderId)
	{
		shippingEventService.deliverOrder(orderId);
		return ResponseEntity.ok("Order delivered successfully.");
	}
}
