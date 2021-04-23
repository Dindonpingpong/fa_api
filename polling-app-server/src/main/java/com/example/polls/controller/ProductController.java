package com.example.polls.controller;

import com.example.polls.model.Product;
import com.example.polls.payload.ApiResponse;
import com.example.polls.payload.ProductRequest;
import com.example.polls.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/product")
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping()
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest) {
        try {
            Product product = new Product(productRequest.getName());
            productRepository.save(product);
            return new ResponseEntity(new ApiResponse(true, "Product created"), CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity(new ApiResponse(false, "Product did not create"), INTERNAL_SERVER_ERROR);
        }
    }
}
