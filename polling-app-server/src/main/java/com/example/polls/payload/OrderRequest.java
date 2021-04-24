package com.example.polls.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequest {
    private Long clientId;
    private List<OrderMenuRequest> orderMenuRequestList;
}
