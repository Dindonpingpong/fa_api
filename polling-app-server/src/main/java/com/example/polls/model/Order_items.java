package com.example.polls.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class Order_items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public Order_items() {};

    public Order_items(Order order, Menu menu) {
        this.order = order;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Order_items{" +
                "id=" + id +
                '}';
    }
}
