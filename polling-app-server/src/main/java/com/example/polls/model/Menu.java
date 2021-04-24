package com.example.polls.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    public Menu(){}
    public Menu(String name, Integer price, Integer weight, boolean status) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.status = status;
    }

    @OneToMany(mappedBy = "menu")
    private Set<BasketGoods> basketGoodsSet;

    @OneToMany(mappedBy = "menu")
    private Set<Composition> compositions;

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
