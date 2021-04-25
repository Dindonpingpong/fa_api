package com.example.pizza.service;

import com.example.pizza.model.Product;
import com.example.pizza.payload.ApiResponse;
import com.example.pizza.payload.PagedResponse;
import com.example.pizza.payload.ProductResponse;
import com.example.pizza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public PagedResponse<ProductResponse> getSortProduct(int page, int size, boolean sortType) {
        var sort = (sortType) ? DESC : ASC;
        Pageable pageable = PageRequest.of(page,size,sort,"name");
        Page<Product> products = productRepository.findAll(pageable);

        List<ProductResponse> productResponseList = new ArrayList<>();
        products.forEach(product -> productResponseList.add(new ProductResponse(product.getId(),product.getName())));
        Collections.reverse(productResponseList);

        return new PagedResponse<ProductResponse>(productResponseList, products.getNumber(), products.getSize(), products.getTotalElements(), products.getTotalPages(), products.isLast());

    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ApiResponse updateProduct(String newName, Long id) {
        Optional<Product> productFromDb = productRepository.findById(id);

        if (productFromDb.isPresent()) {
            Product newProduct = productFromDb.get();
            newProduct.setName(newName);
            productRepository.save(newProduct);
            return new ApiResponse(true, "Product updated");
        } else {
            return new ApiResponse(false, "Product not found");
        }
    }
}