package com.example.pizza.service;

import com.example.pizza.model.Client;
import com.example.pizza.model.Menu;
import com.example.pizza.model.Order;
import com.example.pizza.model.Order_items;
import com.example.pizza.payload.ApiResponse;
import com.example.pizza.payload.OrderRequest;
import com.example.pizza.repository.MenuRepository;
import com.example.pizza.repository.OrderRepository;
import com.example.pizza.repository.Order_ItemsRepository;
import com.example.pizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
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

    @Autowired
    Order_ItemsRepository order_itemsRepository;

    public ResponseEntity createOrder(OrderRequest orderRequest) {
        try {
            Client client = userRepository.getOne(orderRequest.getClientId());

            Order order = new Order(LocalDate.now());

            client.addOrderItem(order);
            order.setClient(client);
            userRepository.save(client);

            Set<Order_items> orderItems = new HashSet<>();

            orderRequest.getOrderMenuRequestList().forEach(menuOrder -> {
                Menu curMenu = menuRepository.getOne(menuOrder.getId());
                Order_items order_items = new Order_items(curMenu,order,menuOrder.getQuantity(),menuOrder.getSubtotal());
                orderItems.add(order_items);
            });

            order.setOrder_itemsSet(orderItems);

            orderRepository.save(order);
            order_itemsRepository.saveAll(orderItems);
            return new ResponseEntity(new ApiResponse(true,"Order created"), CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(false,e.getMessage()), CONFLICT);
        }
    }
}