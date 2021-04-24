package com.example.polls.service;

import com.example.polls.model.Menu;
import com.example.polls.payload.MenuResponse;
import com.example.polls.payload.PagedResponse;
import com.example.polls.repository.CompositionRepository;
import com.example.polls.repository.MenuRepository;
import com.example.polls.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    @Autowired
    CompositionRepository compositionRepository;

    @Autowired
    ProductRepository productRepository;

    public PagedResponse<MenuResponse> getAllMenu(int page, int size, boolean sortDesc) {
        var sort = (sortDesc == true) ? DESC : ASC;
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Menu> menus = menuRepository.findAll(pageable);

        return null;
    }
}
