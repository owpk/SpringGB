package com.owpk.hw.utils;

import com.owpk.hw.entities.Product;
import com.owpk.hw.repositories.specifications.ProdSpec;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProdFilter {
    private Specification<Product> spec;
    private String attributes;

    public void buildSpecAndAttributes(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.isBlank()) {
            spec = spec.and(ProdSpec.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }

        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProdSpec.priceGreaterThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProdSpec.priceLessThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        attributes = filterDefinitionBuilder.toString();
    }
}
