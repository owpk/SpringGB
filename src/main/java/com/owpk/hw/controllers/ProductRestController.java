package com.owpk.hw.controllers;

import com.owpk.hw.dto.ProdDTO;
import com.owpk.hw.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductRestController {
  private ProductService productService;

  @GetMapping
  public List<ProdDTO> getAllProds() {
    return productService.findAll();
  }

}
