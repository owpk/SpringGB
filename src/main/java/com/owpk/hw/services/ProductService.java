package com.owpk.hw.services;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.ProdRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProdRepo prodRepo;

    public Optional<Product> findById(Long id) {
        return prodRepo.findById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return prodRepo.findAll(spec, PageRequest.of(page, size));
    }

    public void deleteById(Long id) {
        prodRepo.deleteById(id);
    }

    public Product addNew(Product product) {
        return prodRepo.save(product);
    }
}
