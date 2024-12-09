package com.post_it.electronics.controllers;

import com.post_it.electronics.dto.CategoryDTO;
import com.post_it.electronics.entities.Category;
import com.post_it.electronics.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") UUID categoryId) {
        Category category = categoryService.getCategory(categoryId);

        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);

        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }
}
