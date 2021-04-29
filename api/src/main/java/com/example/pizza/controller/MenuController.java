package com.example.pizza.controller;

import com.example.pizza.payload.*;
import com.example.pizza.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.pizza.util.AppConstants.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public PagedResponse<MenuResponse> getMenu(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                               @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
                                               @RequestParam(value = "sort", defaultValue = DEFAULT_SORT_TYPE) boolean sortDesc) {
        return menuService.getAllMenu(page, size, sortDesc);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateMenu(@PathVariable long id, @RequestBody MenuRequest menuRequest) {
        return new ResponseEntity<>(menuService.updateMenu(id, menuRequest), OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewItemMenu(@RequestBody MenuRequest menuRequest) {
        try {
            menuService.createMenu(menuRequest);
            return new ResponseEntity<>(new ApiResponse(true, "New item menu created"), CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(new ApiResponse(false, "New item menu did not create"), INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteItemMenu(@PathVariable long id) {
        return new ResponseEntity<>(menuService.updateItemMenuStatus(id), OK);
    }

}
