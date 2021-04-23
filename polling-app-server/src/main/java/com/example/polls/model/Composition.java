package com.example.polls.model;

import lombok.Data;

import javax.persistence.*;
import java.awt.*;

@Data
@Entity
@Table(name="composition")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Composition(Product product, Menu menu) {
        this.product = product;
        this.menu = menu;
    }

    public Composition() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private Menu menu ;

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                '}';
    }
}
