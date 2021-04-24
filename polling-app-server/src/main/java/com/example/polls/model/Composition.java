package com.example.polls.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;

@Data
@Entity
@Table(name = "composition")
@Setter
@Getter
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Composition(Product product, Menu menu) {
        this.product = product;
        this.menu = menu;
    }

    public Composition() {
    }

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                '}';
    }
}
