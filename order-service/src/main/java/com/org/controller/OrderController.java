package com.org.controller;

import com.org.dto.request.OrderRequest;
import com.org.dto.response.OrderResponse;
import com.org.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController
{
    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    // Endpoint to place an order
    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest)
    {
        try
        {
            OrderResponse orderResponse = orderService.placeAnOrder(orderRequest);
            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to confirm an order
    @PutMapping("/confirm/{orderId}")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable String orderId)
    {
        try
        {
            OrderResponse orderResponse = orderService.confirmOrder(orderId);
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}