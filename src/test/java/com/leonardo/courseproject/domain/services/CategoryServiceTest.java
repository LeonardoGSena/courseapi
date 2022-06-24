package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.repositories.CategoryRepository;
import com.leonardo.courseproject.domain.services.exceptions.DataBaseException;
import com.leonardo.courseproject.domain.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 4L;

        doNothing().when(repository).deleteById(existingId);
        doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
        doThrow(DataBaseException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void deleteShouldDoNothingWhenIDExists() {
        assertDoesNotThrow(() -> {
            service.deleteCategory(existingId);
        });
        verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteCategory(nonExistingId);
        });
        verify(repository, times(1)).deleteById(nonExistingId);
    }

    @Test
    public void deleteShouldThrowDataBaseExceptionWhenDependentId() {
        assertThrows(DataBaseException.class, () -> {
            service.deleteCategory(dependentId);
        });
        verify(repository, times(1)).deleteById(dependentId);
    }
}
