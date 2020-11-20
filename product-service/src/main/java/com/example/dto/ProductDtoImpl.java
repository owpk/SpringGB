package com.example.dto;

import com.example.entities.Product;
import com.example.ProductDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
@Getter
@Setter
public class ProductDtoImpl implements ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String category;

    public ProductDtoImpl(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.category = p.getCategory().getTitle();
    }
}