package com.example.polls.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateStart;

    public Basket(){}
    public Basket(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    @OneToMany(mappedBy = "basket")
    private Set<BasketGoods> basketGoodsSet;

    @OneToMany(mappedBy = "basket")
    private Set<Order> orders;

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                '}';
    }
}
