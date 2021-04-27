package com.example.pizza.loader;

import com.example.pizza.model.Menu;
import com.example.pizza.model.Product;
import com.example.pizza.repository.MenuRepository;
import com.example.pizza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class MenuLoader implements CommandLineRunner {
    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;

    @Autowired
    private MenuLoader(MenuRepository menuRepository, ProductRepository productRepository) {
        this.menuRepository = menuRepository;
        this.productRepository = productRepository;
    }

    public void run(String... args) {
        Menu menuFirst = new Menu("Маргарита", 345, 620, false),
                menuSecond = new Menu("Пепперони", 395, 570, false),
                menuThird = new Menu("Ветчина и грибы", 345, 600, false);
        Product productOne = new Product("Пепперони"),
                productTwo = new Product("Ветчина"),
                productThird = new Product("Томат"),
                productFour = new Product("Шампишьон"),
                productFive = new Product("Красный лук"),
                productSex = new Product("Острый цыпленок"),
                productSeven = new Product("Сыр моцарелла");

        List<Product> listProductFirst = Arrays.asList(productThird, productSeven, productFive);
        List<Product> listProductSecond = Arrays.asList(productOne, productTwo, productThird, productFour);
        List<Product> listProductThird = Collections.singletonList(productSex);

        menuRepository.save(menuFirst);
        productRepository.saveAll(listProductFirst);
        menuFirst.addProduct(listProductFirst);
        menuRepository.save(menuFirst);

        menuRepository.save(menuSecond);
        productRepository.saveAll(listProductSecond);
        menuSecond.addProduct(listProductSecond);
        menuRepository.save(menuSecond);

        menuRepository.save(menuThird);
        productRepository.saveAll(listProductThird);
        menuThird.addProduct(listProductThird);
        menuRepository.save(menuThird);
    }
}
