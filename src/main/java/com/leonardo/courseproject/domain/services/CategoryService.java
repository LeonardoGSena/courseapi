package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.repositories.CategoryRepository;
import com.leonardo.courseproject.domain.services.exceptions.DataBaseException;
import com.leonardo.courseproject.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<Category> findAllPaged(PageRequest pageRequest) {
        Page<Category> categories = categoryRepository.findAll(pageRequest);
        return categories;
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException("Category not founded"));
    }

    @Transactional
    public Category insertCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Long id, Category category) {
        try {
            Category newCategory = categoryRepository.getOne(id);
            updateData(newCategory, category);
            return categoryRepository.save(newCategory);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not founded " + id);
        }
    }

    private void updateData(Category newCategory, Category category) {
        newCategory.setName(category.getName());
    }

    public void deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not Found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }

    }
}
