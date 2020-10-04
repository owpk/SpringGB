package com.owpk.hw.services;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.ProdRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdService {

  private ProdRepo prodRepo;

  public Page<Product> getAllProds(Specification<Product> spec, Pageable pageable) {
    return prodRepo.findAll(spec, pageable);
  }

  public Product findById(Long id) {
    return prodRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
  }

  public void saveOrUpdate(Product product) {
    prodRepo.save(product);
  }
}
