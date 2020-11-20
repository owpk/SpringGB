package com.example.controllers;

import com.example.ProductDto;
import com.example.entities.Product;
import com.example.dto.ProductDtoImpl;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping(produces = "application/json")
    public List<ProductDtoImpl> getAllProducts() {
        List<Product> origin = productService.findAll();
        log.info(origin.toString());
        return origin.stream()
                .map(ProductDtoImpl::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @GetMapping("/checkAlive")
    public String checkAlive() {
        return "I'm alive: " + eurekaClient.getApplication(appName).getName();
    }
}
