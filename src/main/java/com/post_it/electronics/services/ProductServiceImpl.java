package com.post_it.electronics.services;

import com.post_it.electronics.dto.ProductDTO;
import com.post_it.electronics.entities.Category;
import com.post_it.electronics.entities.CategoryType;
import com.post_it.electronics.entities.Product;
import com.post_it.electronics.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product = mapToProductEntity(productDTO);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(UUID categoryId, UUID typeId) {
        //To-do mapping of products to productDTOs
        return productRepository.findAll();
    }

    private Product mapToProductEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setBrand(productDTO.getBrand());
        product.setIsNewArrival(productDTO.isNewArrival());
        product.setPrice(productDTO.getPrice());

        Category category = categoryService.getCategory(productDTO.getCategoryId());
        if (null != category) {
            product.setCategory(category);
            UUID categoryTypeId = productDTO.getCategoryTypeId();
            Optional<CategoryType> categoryTypeOptional = category.getCategoryTypeList().stream()
                    .filter(categoryType1 -> categoryType1.getId().equals(categoryTypeId)).findFirst();
            if (categoryTypeOptional.isPresent()) {
                CategoryType categoryType = categoryTypeOptional.get();
                product.setCategoryType(categoryType);
            }
        }
        return product;
    }
}
