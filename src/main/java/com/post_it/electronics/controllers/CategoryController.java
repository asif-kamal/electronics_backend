package com.post_it.electronics.controllers;

import com.post_it.electronics.dto.CategoryDTO;
import com.post_it.electronics.entities.Category;
import com.post_it.electronics.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") UUID categoryId) {
        Category category = categoryService.getCategory(categoryId);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") UUID categoryId) {
        Category updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") UUID categoryId) {
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok().build();
    }
}
