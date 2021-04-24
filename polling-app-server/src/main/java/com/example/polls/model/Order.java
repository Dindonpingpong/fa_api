package com.example.polls.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(128) default 'ordered'")
    private String status;

    private LocalDate orderDate;

    public Order(){};

    public Order(LocalDate orderDate, Client client) {
        this.orderDate = orderDate;
        this.client = client;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order")
    Set<Order_items> order_itemsSet;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}
