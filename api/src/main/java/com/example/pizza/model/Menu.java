package com.example.pizza.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer weight;
    private boolean status;

    public Menu() {}

    public Menu(String name, Integer price, Integer weight, boolean status) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.status = status;
    }

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "menu_product",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> productSet = new HashSet<>();

    @OneToMany(mappedBy = "menu")
    Set<Order_items> order_itemsSet = new HashSet<>();

    public void addProduct(List<Product> products) {
        productSet.addAll(products);
        products.forEach(product -> product.getMenuSet().add(this));
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weith=" + weight +
                ", status=" + status +
                '}';
    }
}
