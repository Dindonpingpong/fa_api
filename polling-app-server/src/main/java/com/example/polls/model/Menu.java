package com.example.polls.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer weith;
    private boolean status; //is in stop-list

    public Menu(){}
    public Menu(String name, Integer price, Integer weith, boolean status) {
        this.name = name;
        this.price = price;
        this.weith = weith;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWeith() {
        return weith;
    }

    public void setWeith(Integer weith) {
        this.weith = weith;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
                ", weith=" + weith +
                ", status=" + status +
                '}';
    }
}
