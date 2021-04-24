package com.example.polls.service;

import com.example.polls.model.Composition;
import com.example.polls.model.Menu;
import com.example.polls.model.Product;
import com.example.polls.payload.*;
import com.example.polls.repository.CompositionRepository;
import com.example.polls.repository.MenuRepository;
import com.example.polls.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            updatedMenu.setCompositions(null);

            Set<Composition> updatedCompositionList = new HashSet<>();

            menuRequest.getProductResponseList().forEach(productResponse -> {
                updatedCompositionList.add(new Composition(productRepository.findById(productResponse.getId()).get(),updatedMenu));
            });

            compositionRepository.deleteByMenu_id(Id);

            compositionRepository.saveAll(updatedCompositionList);

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
            return new PagedResponse<>(Collections.emptyList(), page, size, 0, 0, false);
        }

        List<Long> menuIds = menus.map(Menu::getId).getContent();

        List<Composition> compositionList = compositionRepository.findByMenu_IdIn(menuIds);

        Set<Long> productSet = getProductsId(compositionList);

        Map<Long, String> productsMap = getProductsMap(productSet);

        List<MenuResponse> menuResponse = getListMenuResponses(menus, compositionList, productsMap);

        return new PagedResponse<>(menuResponse, menus.getNumber(), menus.getSize(), menus.getTotalElements(), menus.getTotalPages(), menus.isLast());
    }

    private List<MenuResponse> getListMenuResponses(Page<Menu> menus, List<Composition> compositionList, Map<Long, String> productsMap) {
        List<MenuResponse> menuResponses = new ArrayList<>();

        menus.forEach(menu -> {
            List<ProductResponse> productResponseList = new LinkedList<>();

            compositionList.stream()
                    .filter(composition -> composition.getMenu().getId().equals(menu.getId()))
                    .forEach(composition -> productResponseList.add(new ProductResponse(composition.getProduct().getId(), composition.getProduct().getName())));

            menuResponses.add(new MenuResponse(menu.getId(), menu.getName(), menu.getPrice(), menu.getWeight(), menu.isStatus(), productResponseList));
        });

        return menuResponses;
    }

    private Map<Long, String> getProductsMap(Set<Long> productSet) {
        List<Product> productList = productRepository.findByIdIn(productSet);

        return productList.stream().collect(Collectors.toMap(Product::getId, Product::getName));
    }

    private Set<Long> getProductsId(List<Composition> compositionList) {
        Set<Long> productsId = new HashSet<>();

        compositionList.forEach(composition -> productsId.add(composition.getMenu().getId()));

        return productsId;
    }

}
