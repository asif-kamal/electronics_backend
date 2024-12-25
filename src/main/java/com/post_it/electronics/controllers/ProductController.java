package com.post_it.electronics.controllers;

import com.post_it.electronics.dto.ProductDTO;
import com.post_it.electronics.entities.Product;
import com.post_it.electronics.services.ProductService;
import com.post_it.electronics.services.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(UUID categoryId, UUID typeId) {
        List<Product> productList = productService.getAllProducts(categoryId, typeId);
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
