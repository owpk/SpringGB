package com.owpk.hw.utils;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.specification.ProdSpec;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProdFilter {
  private Specification<Product> spec;
  private String attributes;

  private Specification<Product> creatSpecAndAttributes(Map<String, String> params) {
    spec = Specification.where(null);
    StringBuilder filterDefinitionBuilder = new StringBuilder();

    String filterTitle = params.get("title");
    if (filterTitle != null && !filterTitle.isBlank()) {
      spec = spec.and(ProdSpec.getProdByTitlePart(filterTitle));
      filterDefinitionBuilder.append("&title=").append(filterTitle);
    }

    if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
      Integer minPrice = Integer.parseInt(params.get("min_price"));
      spec = spec.and(ProdSpec.getProdByMinPrice(minPrice));
      filterDefinitionBuilder.append("&min_price=").append(minPrice);
    }

    if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
      Integer maxPrice = Integer.parseInt(params.get("max_price"));
      spec = spec.and(ProdSpec.getProdByMaxPrice(maxPrice));
      filterDefinitionBuilder.append("&max_price=").append(maxPrice);
    }
    attributes = filterDefinitionBuilder.toString();
    return spec;
  }
}
