package com.example.polls.controller;

import com.example.polls.model.Product;
import com.example.polls.payload.ApiResponse;
import com.example.polls.payload.ProductRequest;
import com.example.polls.repository.ProductRepository;
import com.example.polls.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/product")
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody String name) {
        try {
            productService.createProduct(new Product(name));
            return new ResponseEntity(new ApiResponse(true, "Product created"), CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity(new ApiResponse(false, "Product did not create"), INTERNAL_SERVER_ERROR);
        }
    };

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    };

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable long id, @RequestBody String name) {
        return new ResponseEntity(productService.updateProduct(name,id), OK);
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@PathVariable long id) {
        return new ResponseEntity(productService.deleteById(id), OK);
    }
}
