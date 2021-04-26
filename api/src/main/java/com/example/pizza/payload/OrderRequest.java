package com.example.pizza.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequest {
    private Long clientId;
    private List<OrderMenuRequest> orderMenuRequestList;
}
