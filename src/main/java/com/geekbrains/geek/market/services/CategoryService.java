package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.dto.CategoryDto;
import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.CategoryRepository;
import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<CategoryDto> findAllDto() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
