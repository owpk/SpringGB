package com.owpk.hw.services;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
  private ProductRepo productRepo;

  public Page<Product> getAll(Pageable var, Specification<Product> specification) {
    return productRepo.findAll(specification, var);
  }

  public void deleteById(Long id) {
    productRepo.deleteById(id);
  }
}
