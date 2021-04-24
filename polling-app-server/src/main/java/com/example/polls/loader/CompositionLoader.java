package com.example.polls.loader;

import com.example.polls.model.Composition;
import com.example.polls.model.Menu;
import com.example.polls.model.Product;
import com.example.polls.repository.CompositionRepository;
import com.example.polls.repository.MenuRepository;
import com.example.polls.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CompositionLoader implements CommandLineRunner {
    private final CompositionRepository compositionRepository;
    private final ProductRepository productRepository;
    private final MenuRepository menuRepository;

    @Autowired
    private CompositionLoader(CompositionRepository compositionRepository, ProductRepository productRepository, MenuRepository menuRepository) {
        this.compositionRepository = compositionRepository;
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(String... args) {
//        this.compositionRepository.save(new Composition(new Product("Пепперони"), new Menu("Чиззи чеддер", 395, 480, false)));
        this.compositionRepository.save(new Composition(productRepository.findByName("Пепперони"), menuRepository.findByName("Чизбургер-пицца")));
    }
}
