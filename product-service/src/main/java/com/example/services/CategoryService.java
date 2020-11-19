package com.example.services;

import com.example.entities.Category;
import com.example.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private CategoryRepo categoryRepo;

    public Optional<Category> findByName(String name) {
        return categoryRepo.findByCategoryName(name);
    }
}
