package com.example.pizza.service;

import com.example.pizza.model.Menu;
import com.example.pizza.payload.*;
import com.example.pizza.repository.MenuRepository;
import com.example.pizza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ProductRepository productRepository;

    public Optional<Menu> getMenuById(Long Id) {
        return menuRepository.findById(Id);
    }

    public ApiResponse updateMenu(Long Id, MenuRequest menuRequest) {
        Optional<Menu> menuFromDb = menuRepository.findById(Id);

        if (menuFromDb.isPresent()) {
            Menu updatedMenu = menuFromDb.get();
            updatedMenu.setName(menuRequest.getName());
            updatedMenu.setPrice(menuRequest.getPrice());
            updatedMenu.setWeight(menuRequest.getWeight());
            updatedMenu.setStatus(menuRequest.isStatus());

            menuRepository.save(updatedMenu);

            return new ApiResponse(true, "Menu updated");
        } else {
            return new ApiResponse(false, "Menu not found");
        }
    }

    public PagedResponse<MenuResponse> getAllMenu(int page, int size, boolean sortDesc) {
        var sort = (sortDesc) ? DESC : ASC;
        Pageable pageable = PageRequest.of(page, size, sort, "name");
        Page<Menu> menus = menuRepository.findAll(pageable);

        if (menus.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), page, size, 0, 0, true);
        }

        List<MenuResponse> menuResponseList = new ArrayList<>();

        menus.forEach(menu -> {
            List<ProductResponse> productResponseList = new ArrayList<>();

            menu.getProductSet().forEach(product -> productResponseList.add(new ProductResponse(product.getId(),product.getName())));

            MenuResponse menuResponse = new MenuResponse(menu.getId(),menu.getName(),menu.getPrice(),menu.getWeight(),menu.isStatus(), productResponseList);

            menuResponseList.add(menuResponse);
        });


        return new PagedResponse<>(menuResponseList, menus.getNumber(), menus.getSize(), menus.getTotalElements(), menus.getTotalPages(), menus.isLast());
    }

}
