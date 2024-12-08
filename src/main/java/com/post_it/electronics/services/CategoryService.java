package com.post_it.electronics.services;

import com.post_it.electronics.dto.CategoryDTO;
import com.post_it.electronics.dto.CategoryTypeDTO;
import com.post_it.electronics.entities.Category;
import com.post_it.electronics.entities.CategoryType;
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
        return Category.builder()
                .code(categoryDTO.getCode())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .categoryTypeList(mapToCategoryTypesList(categoryDTO.getCategoryTypeDTOList()))
                .build();

    }

    private List<CategoryType> mapToCategoryTypesList(List<CategoryTypeDTO> categoryTypeDTOList) {
        return categoryTypeDTOList.stream().map(categoryTypeDTO -> {
            CategoryType categoryType = new CategoryType();
            categoryType.setCode(categoryTypeDTO.getCode());
            categoryType.setName(categoryTypeDTO.getName());
            categoryType.setDescription(categoryType.getDescription());
            return categoryType;
        }).collect(Collectors.toList());
    }
}
