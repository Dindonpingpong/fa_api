package com.example.pizza.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String telephone;
    private LocalDateTime hireDate;
}
