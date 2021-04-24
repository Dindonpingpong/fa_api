package com.example.polls.repository;

import com.example.polls.model.Order_items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_ItemsRepository extends JpaRepository<Order_items, Long> {

}
