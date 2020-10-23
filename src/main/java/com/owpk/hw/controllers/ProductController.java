package com.owpk.hw.controllers;

import com.owpk.hw.entities.Product;
import com.owpk.hw.services.ProductService;
import com.owpk.hw.utils.Cart;
import com.owpk.hw.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private Cart cart;

    @GetMapping
    private Page<Product> getAllProducts(@RequestParam(name = "p", defaultValue = "1", required = false) Integer page,
                                         @RequestParam Map<String, String> params) {
        if (page == null || page < 1) page = 1;
        ProductFilter productSpec = new ProductFilter(params);
        Specification<Product> specification = productSpec.getSpec();
        return productService.getProductBySpec(specification, PageRequest.of(page - 1, 5));
    }


}
