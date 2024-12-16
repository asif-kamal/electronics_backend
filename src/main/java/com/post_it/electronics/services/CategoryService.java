package com.post_it.electronics.services;

import com.post_it.electronics.dto.CategoryDTO;
import com.post_it.electronics.dto.CategoryTypeDTO;
import com.post_it.electronics.entities.Category;
import com.post_it.electronics.entities.CategoryType;
import com.post_it.electronics.exceptions.ResourceNotFoundException;
import com.post_it.electronics.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // instead of using field injection
public class CategoryService {

    private CategoryRepository categoryRepository;


    public Category getCategory(UUID categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        return category.orElse(null);
    }

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);

        return categoryRepository.save(category);
    }

    private Category mapToEntity(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .code(categoryDTO.getCode())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();

        if (null != categoryDTO.getCategoryTypeDTOList()) {
            List<CategoryType> categoryTypeList = mapToCategoryTypesList(categoryDTO.getCategoryTypeDTOList(), category);
            category.setCategoryTypeList(categoryTypeList);
        }

        return category;
    }

    private List<CategoryType> mapToCategoryTypesList(List<CategoryTypeDTO> categoryTypeDTOList, Category category) {
        return categoryTypeDTOList.stream().map(categoryTypeDTO -> {
            CategoryType categoryType = new CategoryType();
            categoryType.setCode(categoryTypeDTO.getCode());
            categoryType.setName(categoryTypeDTO.getName());
            categoryType.setDescription(categoryTypeDTO.getDescription());
            categoryType.setCategory(category);
            return categoryType;
        }).collect(Collectors.toList());
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(CategoryDTO categoryDTO, UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID "+ categoryDTO.getId()));

        if (null != categoryDTO.getName()) {
            category.setName(categoryDTO.getName());
        }
        if (null != categoryDTO.getDescription()) {
            category.setDescription(categoryDTO.getDescription());
        }
        if (null != categoryDTO.getCode()) {
            category.setCode(categoryDTO.getCode());
        }
        if (categoryDTO.getCategoryTypeDTOList() != null) {
            List<CategoryType> list = mapToCategoryTypesList(categoryDTO.getCategoryTypeDTOList(), category);
            category.setCategoryTypeList(list);
        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
