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
    private LocalDate orderDate;
    private Long clientId;
    private List<MenuResponse> menuResponseList;
}
