package com.example.polls.repository;

import com.example.polls.model.Composition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {
    List<Composition> findByProduct_id(Long id);
    List<Composition> findByMenu_IdIn(List<Long> listId);

    void deleteByMenu_id(Long id);
}
