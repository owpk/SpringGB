package com.owpk.hw.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.owpk.hw.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonAutoDetect
public class ProductDto {
    private Long id;
    private Integer price;
    private String title;
    private String category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.title = product.getTitle();
        this.category = product.getCategory().getTitle();
    }
}
