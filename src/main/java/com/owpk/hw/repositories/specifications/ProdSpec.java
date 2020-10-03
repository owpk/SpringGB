package com.owpk.hw.repositories.specifications;

import com.owpk.hw.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProdSpec {

  public static Specification<Product> priceLessOrEqual(int max) {
    return (Specification<Product>) (root, query, builder) -> builder
        .lessThanOrEqualTo(root.get("price"), max);
  }

  public static Specification<Product> priceGreaterOrEqual(int min) {
    return (Specification<Product>) (root, query, builder) -> builder
        .greaterThanOrEqualTo(root.get("price"), min);
  }

  public static Specification<Product> titleLike(String titlePart) {
    return (Specification<Product>) (root, query, builder) -> builder
        .like(root.get("title"), String.format("%s%%", titlePart));
  }

}
