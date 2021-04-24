package com.example.polls.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderMenuRequest {
    private Long id;
    private String name;
    private Integer price;
    private Integer weight;
    private boolean status;
    private Integer quantity;
    private Integer subtotal;
}
