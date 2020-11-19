package com.example.dto;

import com.example.entities.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
@Getter
@Setter
public class ProductDto implements com.example.ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String category;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.category = p.getCategory().getTitle();
    }
}