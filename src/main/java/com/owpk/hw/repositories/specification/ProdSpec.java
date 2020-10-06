package com.owpk.hw.repositories.specification;


import com.owpk.hw.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProdSpec {
  public static Specification<Product> getProdByMinPrice(Integer min) {
    return (Specification<Product>) (root, query, builder) -> builder
        .greaterThanOrEqualTo(root.get("price"), min);
  }

  public static Specification<Product> getProdByMaxPrice(Integer max) {
    return (Specification<Product>) (root, query, builder) -> builder
        .lessThanOrEqualTo(root.get("price"), max);
  }

  public static Specification<Product> getProdByTitlePart(String titlePart) {
    return (Specification<Product>) (root, query, builder) -> builder
        .like(root.get("title"), String.format("%s%%", titlePart));
  }
}
