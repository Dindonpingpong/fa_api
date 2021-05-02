package com.example.pizza.controller;

import com.example.pizza.payload.*;
import com.example.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse addEmployeeToOrder(@PathVariable long orderId, @PathVariable long employeeId) {
        try {
            orderService.addEmployeeToOrder(orderId,employeeId);
            return new ApiResponse(true,"Курьер добавлен к заказу");
        } catch (Exception e) {
            return new ApiResponse(false,"Курьер не был добавлен");
        }
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse deleteOrder(@PathVariable long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ApiResponse(true,"Заказ удален");
        } catch (Exception e) {
            return new ApiResponse(false,e.getMessage());
        }
    }
}
