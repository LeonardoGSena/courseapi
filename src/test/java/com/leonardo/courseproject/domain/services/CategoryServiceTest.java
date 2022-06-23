package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    private Long existingId;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
    }

    @Test
    public void deleteShouldDoNothingWhenIDExists() {
        assertDoesNotThrow(() -> {
            service.deleteCategory(existingId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }
}
