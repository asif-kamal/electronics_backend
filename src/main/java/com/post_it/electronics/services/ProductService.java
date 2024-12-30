package com.post_it.electronics.services;

import com.post_it.electronics.dto.ProductDTO;
import com.post_it.electronics.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    public Product addProduct(ProductDTO productDTO);

    public List<Product> getAllProducts(UUID categoryId, UUID typeId);
}
