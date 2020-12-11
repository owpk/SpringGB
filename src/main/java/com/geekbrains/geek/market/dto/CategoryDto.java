package com.geekbrains.geek.market.dto;


import com.geekbrains.geek.market.entities.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category c) {
        this.id = c.getId();
        this.title = c.getTitle();
    }
}
