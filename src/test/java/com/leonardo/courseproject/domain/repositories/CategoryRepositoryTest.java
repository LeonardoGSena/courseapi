package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Long existId;
    private Long nonExistId;
    private Long totalCategories;

    @BeforeEach
    void Setup() throws Exception {
        existId = 1L;
        nonExistId = 2L;
        totalCategories = 1L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Category category = Factory.createCategory();
        category.setId(null);

        category = categoryRepository.save(category);

        assertNotNull(category.getId());
        assertEquals(totalCategories + 1, category.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        categoryRepository.deleteById(existId);
        Optional<Category> result = categoryRepository.findById(existId);

        assertFalse(result.isPresent(), "It should return false");
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
           categoryRepository.deleteById(nonExistId);
        });
    }
}
