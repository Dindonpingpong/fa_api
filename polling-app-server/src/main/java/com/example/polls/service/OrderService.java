package com.example.polls.service;

import com.example.polls.model.Client;
import com.example.polls.model.Order;
import com.example.polls.payload.ApiResponse;
import com.example.polls.payload.OrderRequest;
import com.example.polls.repository.MenuRepository;
import com.example.polls.repository.OrderRepository;
import com.example.polls.repository.Order_ItemsRepository;
import com.example.polls.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Order_ItemsRepository order_itemsRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserRepository userRepository;

}
