package com.example.pizza.service;

import com.example.pizza.model.*;
import com.example.pizza.payload.ApiResponse;
import com.example.pizza.payload.IdRequest;
import com.example.pizza.payload.OrderMenuRequest;
import com.example.pizza.payload.OrderRequest;
import com.example.pizza.repository.*;
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

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<?> createOrder(OrderRequest orderRequest) {
        try {
            Client client = userRepository.getOne(orderRequest.getClientId());

            Order order = new Order(LocalDate.now());

            client.addOrderItem(order);
            order.setClient(client);
            userRepository.save(client);

            Set<Order_items> orderItems = new HashSet<>();

            orderRequest.getOrderMenuRequestList().forEach(menuOrder -> {
                Menu curMenu = menuRepository.getOne(menuOrder.getId());
                Order_items order_items = new Order_items(curMenu, order, menuOrder.getQuantity(), menuOrder.getSubtotal());
                orderItems.add(order_items);
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
            OrderRequest orderRequest = new OrderRequest(order.getId(), orderMenuRequestsList);
            orderList.add(orderRequest);
        });

        return orderList;
    }

    public ApiResponse updateOrder(Long id, IdRequest idRequest) {
        Optional<Order> orderFromDb = orderRepository.findById(id);
        Optional<Employee> employeeFromDb = employeeRepository.findById(idRequest.getId());

        if (orderFromDb.isPresent() && employeeFromDb.isPresent()) {
            Order newOrder = orderFromDb.get();
            Employee employee = employeeFromDb.get();
            newOrder.setEmployee(employee);
            orderRepository.save(newOrder);
            return new ApiResponse(true, "Order updated");
        } else {
            return new ApiResponse(false, "Order not found");
        }
    }
}
