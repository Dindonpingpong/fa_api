package com.example.polls.service;

import com.example.polls.model.Composition;
import com.example.polls.model.Product;
import com.example.polls.payload.ApiResponse;
import com.example.polls.repository.CompositionRepository;
import com.example.polls.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompositionRepository compositionRepository;

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
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

    public ApiResponse deleteById(Long id) {
        List<Composition> compositionList = compositionRepository.findByProduct_id(id);

        if (compositionList.isEmpty()) {
            productRepository.deleteById(id);
            return new ApiResponse(true, "Product deleted");
        } else {
            return new ApiResponse(false, "Product can't be deleted");
        }
    }
}
