package com.example.polls.service;

import com.example.polls.model.Client;
import com.example.polls.model.Menu;
import com.example.polls.model.Order;
import com.example.polls.model.Order_items;
import com.example.polls.payload.ApiResponse;
import com.example.polls.payload.OrderRequest;
import com.example.polls.repository.MenuRepository;
import com.example.polls.repository.OrderRepository;
import com.example.polls.repository.Order_ItemsRepository;
import com.example.polls.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public ResponseEntity createOrder(OrderRequest orderRequest) {
        try {
            Client client = userRepository.getOne(orderRequest.getClientId());

            Order order = new Order(LocalDate.now(), client);

            Set<Order_items> orderItems = new HashSet<>();

            orderRequest.getOrderMenuRequestList().forEach(menuOrder -> {
                Menu curMenu = menuRepository.getOne(menuOrder.getId());
                orderItems.add(new Order_items(curMenu,order,menuOrder.getQuantity(),menuOrder.getSubtotal()));
            });

            order.setOrder_itemsSet(orderItems);
            orderRepository.save(order);

            return new ResponseEntity(new ApiResponse(true,"Order created"), CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(false,"Order did not create"), CONFLICT);
        }
    }
}
