package com.example.controllers;

import com.example.dto.ProductDto;
import com.example.entities.Product;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping(produces = "application/json")
    public List<ProductDto> getAllProducts() {
        List<Product> origin = productService.findAll();
        log.info(origin.toString());
        return origin.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/checkAlive")
    public String checkAlive() {
        return "I'm alive: " + eurekaClient.getApplication(appName).getName();
    }
}
