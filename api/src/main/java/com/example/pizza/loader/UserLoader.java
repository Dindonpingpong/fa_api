package com.example.pizza.loader;

import com.example.pizza.model.Client;
import com.example.pizza.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Лоадер для генерации пользователей
 */
@Component
public class UserLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(new Client("Администартор", "Адм", "admin", "+7977-845-4512",
                "adm@adm.com", "Мск", this.passwordEncoder.encode("1234")));
    }
}
