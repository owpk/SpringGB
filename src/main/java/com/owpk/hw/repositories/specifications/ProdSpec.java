package com.owpk.hw.repositories.specifications;

import com.owpk.hw.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProdSpec {
    public static Specification<Product> priceLessOrEquals(Integer max) {
        return (Specification<Product>) (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), max);
    }
    public static Specification<Product> priceGreaterOrEqual(Integer min) {
        return (Specification<Product>) (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), min);
    }
    public static Specification<Product> titleLike(String title) {
        return (Specification<Product>) (root, query, builder) -> builder.like(root.get("title"), title);
    }
    public static Specification<Product> categoryLike(String categoryName) {
        return (Specification<Product>) (root, query, builder) -> builder.like(root.get("category").get("title"), categoryName);
    }
}
