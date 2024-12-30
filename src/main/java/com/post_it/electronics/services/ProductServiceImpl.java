package com.post_it.electronics.services;

import com.post_it.electronics.dto.ProductDTO;
import com.post_it.electronics.dto.ProductResourceDTO;
import com.post_it.electronics.dto.ProductVariantDTO;
import com.post_it.electronics.entities.*;
import com.post_it.electronics.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        if (null != productDTO.getId()) {
            product.setId(productDTO.getId());
        }
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setBrand(productDTO.getBrand());
        product.setIsNewArrival(productDTO.isNewArrival());
        product.setPrice(productDTO.getPrice());

        // Get the category first and validate it exists
        Category category = categoryService.getCategory(productDTO.getCategoryId());
        if (category == null) {
            throw new IllegalArgumentException("Category not found for id: " + productDTO.getCategoryId());
        }

        // Set the category
        product.setCategory(category);

        // Handle category type
        UUID categoryTypeId = productDTO.getCategoryTypeId();
        if (categoryTypeId != null) {
            Optional<CategoryType> categoryTypeOptional = category.getCategoryTypeList().stream()
                    .filter(categoryType1 -> categoryType1.getId().equals(categoryTypeId))
                    .findFirst();

            categoryTypeOptional.ifPresent(product::setCategoryType);
        }

        // Handle variants
        if (null != productDTO.getProductVariantDTOList()) {
            product.setProductVariantList(mapToProductVariant(productDTO.getProductVariantDTOList(), product));
        }

        // Handle resources
        if (null != productDTO.getProductResourceDTOList()) {
            product.setResourceList(mapToProductResources(productDTO.getProductResourceDTOList(), product));
        }

        // Return the product without saving it
        return product;
    }

    private List<Resource> mapToProductResources(List<ProductResourceDTO> productResourceDTOList, Product product) {
        return productResourceDTOList.stream().map(productResourceDTO -> {
            Resource resource = new Resource();
            resource.setName(productResourceDTO.getName());
            resource.setType(productResourceDTO.getType());
            resource.setUrl(productResourceDTO.getUrl());
            resource.setIsPrimary(productResourceDTO.isPrimary());
            resource.setProduct(product);
            return resource;
        }).collect(Collectors.toList());
    }

    private List<ProductVariant> mapToProductVariant(List<ProductVariantDTO> productVariantDTOList, Product product) {
        return productVariantDTOList.stream().map(productVariantDTO -> {
            ProductVariant productVariant = new ProductVariant();
            productVariant.setColor(productVariantDTO.getColor());
            productVariant.setSize(productVariantDTO.getSize());
            productVariant.setStockQuantity(productVariantDTO.getStockQuantity());
            productVariant.setProduct(product);
            return productVariant;
        }).collect(Collectors.toList());
    }
}
