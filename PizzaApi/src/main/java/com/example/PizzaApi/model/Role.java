package com.example.PizzaApi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 60)
    private RoleName name;

    public Role() {};

    public Role(RoleName name) { this.name = name; };

}
