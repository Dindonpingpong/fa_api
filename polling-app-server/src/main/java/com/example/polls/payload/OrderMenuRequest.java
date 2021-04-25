package com.example.polls.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderMenuRequest {
    private Long id;
    private Integer quantity;
    private Integer subtotal;
}
