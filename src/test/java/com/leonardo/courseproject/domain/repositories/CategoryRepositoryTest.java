package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        long existId = 1L;
        categoryRepository.deleteById(existId);
        Optional<Category> result = categoryRepository.findById(existId);

        Assertions.assertFalse(result.isPresent(), "It should return false");
    }

}
