package com.owpk.hw.utils;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.specifications.ProdSpec;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class ProdFilter {

  private String attributes;

  private Specification<Product> productSpecification;

  public String getAttributes(){
    return attributes;
  }

  public Specification<Product> getSpecAndCreateAttributeDefinition(Map<String, String> params) {
    final StringBuilder sb = new StringBuilder();
    productSpecification = Specification.where(null);
    sb.append("?");
    if (params.containsKey("min_price") && !params.get("min_price").trim().isEmpty()) {
      int val = Integer.parseInt(params.get("min_price"));
      productSpecification = productSpecification.and(ProdSpec.priceGreaterOrEqual(val));
      sb.append("&min_price=").append(val);
    }

    if (params.containsKey("max_price") && !params.get("max_price").trim().isEmpty()) {
      int val = Integer.parseInt(params.get("max_price"));
      productSpecification = productSpecification.and(ProdSpec.priceLessOrEqual(val));
      sb.append("&max_price=").append(val);
    }

    if (params.containsKey("title") && !params.get("title").trim().isEmpty()) {
      String val = params.get("title");
      productSpecification = productSpecification.and(ProdSpec.titleLike(val));
      sb.append("&title=").append(val);
    }
    return productSpecification;
  }
}
