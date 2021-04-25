package com.example.pizza.controller;

import com.example.pizza.model.Product;
import com.example.pizza.payload.ApiResponse;
import com.example.pizza.payload.PagedResponse;
import com.example.pizza.payload.ProductRequest;
import com.example.pizza.payload.ProductResponse;
import com.example.pizza.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import static com.example.pizza.util.AppConstants.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/product")
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest) {
        try {
            productService.createProduct(new Product(productRequest.getName()));
            return new ResponseEntity(new ApiResponse(true, "Product created"), CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity(new ApiResponse(false, "Product did not create"), INTERNAL_SERVER_ERROR);
        }
    };

    @GetMapping
    public PagedResponse<ProductResponse> getProduct(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                     @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
                                                     @RequestParam(value = "sort", defaultValue = DEFAULT_SORT_TYPE) String sortType) {
        boolean sortDesc = sortType.equalsIgnoreCase("desc");
        return productService.getSortProduct(page, size, sortDesc);
    };

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable long id, @RequestBody String name) {
        return new ResponseEntity(productService.updateProduct(name,id), OK);
    }

}
