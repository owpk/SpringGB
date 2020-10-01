package com.owpk.hw.utils;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.specifications.ProdSpec;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProdFilter {

  private Specification<Product> spec;
  private Map<String, String> params;
  private String filteredAttributes;

  public ProdFilter(Map<String, String> params) {
    this.params = params;
  }

  public void buildSpec() {
    final StringBuilder sb = new StringBuilder();
    spec = Specification.where(null);
    if(params.containsKey("max_price") && !params.get("max_price").trim().isEmpty()) {
      Integer maxPrice = Integer.valueOf(params.get("max_price"));
      spec = spec.and(ProdSpec.priceLessOrEqual(maxPrice));
      sb.append("&max_price=").append(maxPrice);
    }
    if (params.containsKey("min_price") && !params.get("min_price").trim().isEmpty()) {
      Integer minPrice = Integer.valueOf(params.get("min_price"));
      spec = spec.and(ProdSpec.priceGreaterOrEqual(minPrice));
      sb.append("&min_price=").append(minPrice);
    }
    if (params.containsKey("title") && !params.get("title").trim().isEmpty()) {
      String titlePart = params.get("title");
      spec = spec.and(ProdSpec.titleStartWith(titlePart));
      sb.append("&title=").append(titlePart);
    }
    filteredAttributes = sb.toString();
  }
}
