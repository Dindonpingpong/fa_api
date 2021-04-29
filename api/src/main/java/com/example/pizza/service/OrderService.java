package com.example.pizza.service;

import com.example.pizza.model.*;
import com.example.pizza.payload.ApiResponse;
import com.example.pizza.payload.OrderMenuRequest;
import com.example.pizza.payload.OrderRequest;
import com.example.pizza.repository.MenuRepository;
import com.example.pizza.repository.OrderRepository;
import com.example.pizza.repository.Order_ItemsRepository;
import com.example.pizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Order_ItemsRepository order_itemsRepository;

    public ResponseEntity<?> createOrder(OrderRequest orderRequest) {
        try {
            Client client = userRepository.getOne(orderRequest.getClientId());

            Order order = new Order(orderRequest.getOrderDate(), orderRequest.getAddress(), orderRequest.getPhone());

            client.addOrderItem(order);
            order.setClient(client);
            userRepository.save(client);

            List<Order_items> orderItems = new LinkedList<>();

            orderRequest.getOrderMenuRequestList().forEach(menuOrder -> {
                Menu curMenu = menuRepository.getOne(menuOrder.getId());
                Order_items order_item = new Order_items(curMenu, order, menuOrder.getQuantity(), menuOrder.getSubtotal());
//                order_itemsRepository.save(order_item);
                curMenu.addOrderItem(order_item);
                menuRepository.save(curMenu);
                orderItems.add(order_item);
            });

            order.setOrder_itemsSet(orderItems);

            orderRepository.save(order);
            order_itemsRepository.saveAll(orderItems);
            return new ResponseEntity<>(new ApiResponse(true, "Order created"), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), CONFLICT);
        }
    }

    public List<OrderRequest> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        List<OrderRequest> orderList = new ArrayList<>();
        orders.forEach(order ->
        {
            List<OrderMenuRequest> orderMenuRequestsList = new ArrayList<>();
            order.getOrder_itemsSet().forEach(item -> {
                OrderMenuRequest orderMenuRequest = new OrderMenuRequest(item.getId(), item.getQuantity(), item.getSubtotal());
                orderMenuRequestsList.add(orderMenuRequest);
            });
            OrderRequest orderRequest = new OrderRequest(order.getId(), order.getPhone(), order.getAddress(), order.getOrderDate(), orderMenuRequestsList);
            orderList.add(orderRequest);
        });

        return orderList;
    }
}
