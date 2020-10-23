package com.owpk.hw.services;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo productRepo;

    public Page<Product> getProductBySpec(Specification<Product> specification, Pageable var) {
        return productRepo.findAll(specification, var);
    }

    public List<Product> getAllBySpec(Specification<Product> specification) {
        return productRepo.findAll(specification);
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }
}
