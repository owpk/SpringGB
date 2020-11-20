package com.example.services;

import com.example.entities.Product;
import com.example.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

}
