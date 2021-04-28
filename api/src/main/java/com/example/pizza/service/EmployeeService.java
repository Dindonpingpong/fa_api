package com.example.pizza.service;

import com.example.pizza.model.Employee;
import com.example.pizza.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
