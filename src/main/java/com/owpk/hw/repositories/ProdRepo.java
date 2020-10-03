package com.owpk.hw.repositories;

import com.owpk.hw.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
