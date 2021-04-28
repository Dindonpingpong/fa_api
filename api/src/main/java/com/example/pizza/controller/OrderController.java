package com.example.pizza.controller;

import com.example.pizza.payload.IdRequest;
import com.example.pizza.payload.OrderMenuRequest;
import com.example.pizza.payload.OrderRequest;
import com.example.pizza.payload.ProductResponse;
import com.example.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderRequest> getOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody IdRequest idRequest) {
        return new ResponseEntity<>(orderService.updateOrder(id, idRequest), HttpStatus.OK);
    }
}
