package com.example.polls.loader;

import com.example.polls.model.Menu;
import com.example.polls.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class MenuLoader implements CommandLineRunner {
    private final MenuRepository menuRepository;

    @Autowired
    private MenuLoader(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.menuRepository.save(new Menu("Пепперони", 395, 570, false));
        this.menuRepository.save(new Menu("Маргарита", 345, 620, false));
        this.menuRepository.save(new Menu("Ветчина и грибы", 345, 600, false));
        this.menuRepository.save(new Menu("Ветчина и сыр", 295, 500, false));
        this.menuRepository.save(new Menu("Кисло-сладкий цыпленок", 295, 540, false));
        this.menuRepository.save(new Menu("Цыпленок блю чиз", 445, 610, false));
        this.menuRepository.save(new Menu("Цыпленок барбекю", 445, 660, false));
        this.menuRepository.save(new Menu("Четыре сыра", 445, 550, false));
        this.menuRepository.save(new Menu("Мясная", 445, 600, false));
        this.menuRepository.save(new Menu("Гавайская", 395, 650, false));
        this.menuRepository.save(new Menu("Карбонара", 395, 610, false));
        this.menuRepository.save(new Menu("Мексиканская", 445, 710, false));
        this.menuRepository.save(new Menu("Четыре сезона", 395, 640, false));
        this.menuRepository.save(new Menu("Песто", 445, 610, false));
        this.menuRepository.save(new Menu("Аррива!", 395, 590, false));
        this.menuRepository.save(new Menu("Сырный цыпленок", 395, 620, false));
        this.menuRepository.save(new Menu("Чизбургер-пицца", 395, 660, false));
        this.menuRepository.save(new Menu("Чиззи чеддер", 395, 480, false));
    }
}
