package com.post_it.electronics.services;

import com.post_it.electronics.dto.ProductDTO;
import com.post_it.electronics.entities.Product;
import com.post_it.electronics.repositories.ProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(ProductDTO product) {

        return null;
    }

    @Override
    public List<Product> getAllProducts(UUID categoryId, UUID typeId) {
        //To-do mapping of products to productDTOs
        return productRepository.findAll();
    }

    private Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setBrand(productDTO.getBrand());
        product.setIsNewArrival(productDTO.isNewArrival());
        product.setPrice(productDTO.getPrice());

        return product;
    }
}
