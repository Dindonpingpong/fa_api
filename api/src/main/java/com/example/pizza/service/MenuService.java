package com.example.pizza.service;

import com.example.pizza.model.Menu;
import com.example.pizza.model.Product;
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

    public List<MenuResponse> getAllMenu() {
        List<MenuResponse> menuResponseList = new ArrayList<>();

        menuRepository.findAll().forEach(menu -> {
            List<ProductResponse> productResponseList = new ArrayList<>();

            menu.getProductSet().forEach(product -> productResponseList.add(new ProductResponse(product.getId(),product.getName())));
            menuResponseList.add(new MenuResponse(menu.getId(),menu.getName(),menu.getPrice(),menu.getWeight(),menu.isStatus(),productResponseList));
        });

        return menuResponseList;
    }

    public PagedResponse<MenuResponse> getAllMenuSorted(int page, int size, boolean sortDesc) {
        var sort = (sortDesc) ? DESC : ASC;
        Pageable pageable = PageRequest.of(page, size, sort, "name");
        Page<Menu> menus = menuRepository.findAll(pageable);

        if (menus.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), page, size, 0, 0, true);
        }

        List<MenuResponse> menuResponseList = new ArrayList<>();
        menus.forEach(menu -> {
            List<ProductResponse> productResponseList = new ArrayList<>();

            menu.getProductSet().forEach(product -> productResponseList.add(new ProductResponse(product.getId(), product.getName())));
            MenuResponse menuResponse = new MenuResponse(menu.getId(), menu.getName(), menu.getPrice(), menu.getWeight(), menu.isStatus(), productResponseList);
            menuResponseList.add(menuResponse);
        });
        return new PagedResponse<>(menuResponseList, menus.getNumber(), menus.getSize(), menus.getTotalElements(), menus.getTotalPages(), menus.isLast());
    }

    public void createMenu(MenuRequest menuRequest) {
        Menu menu = new Menu(menuRequest.getName(), menuRequest.getPrice(), menuRequest.getWeight(), menuRequest.isStatus(),
                "https://img.freepik.com/free-vector/cute-pizza-cartoon-vector-icon-illustration-fast-food-icon-concept-flat-cartoon-style_138676-2588.jpg?size=338&ext=jpg");
        if (menuRequest.getProductList() != null) {
            List<Product> listProduct = new ArrayList<>();
            menuRequest.getProductList().forEach(product -> listProduct.add(productRepository.getOne(product)));
            menu.addProduct(listProduct);
        }
        menuRepository.save(menu);
    }

    public ApiResponse updateItemMenuStatus(Long Id) {
        Optional<Menu> menuFromDb = menuRepository.findById(Id);

        if (menuFromDb.isPresent()) {
            Menu updatedMenu = menuFromDb.get();
            updatedMenu.setStatus(true);
            menuRepository.save(updatedMenu);
            return new ApiResponse(true, "Menu item deleted");
        } else {
            return new ApiResponse(false, "Menu item not found");
        }
    }

    public ApiResponse restoreItemMenuStatus(Long Id) {
        Optional<Menu> menuFromDb = menuRepository.findById(Id);

        if (menuFromDb.isPresent()) {
            Menu updatedMenu = menuFromDb.get();
            updatedMenu.setStatus(false);
            menuRepository.save(updatedMenu);
            return new ApiResponse(true, "Menu item restores");
        } else {
            return new ApiResponse(false, "Menu item not found");
        }
    }
}
