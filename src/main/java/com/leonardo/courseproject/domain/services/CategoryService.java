package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
