package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.repositories.CategoryRepository;
import com.leonardo.courseproject.domain.services.exceptions.DataBaseException;
import com.leonardo.courseproject.domain.services.exceptions.ResourceNotFoundException;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
    private PageImpl<Category> pageRequest;
    private Category category;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 4L;
        category = Factory.createCategory();
        pageRequest = new PageImpl<>(List.of(category));

        when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn((Page<Category>) pageRequest);

        doNothing().when(repository).deleteById(existingId);
        doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
        doThrow(DataBaseException.class).when(repository).deleteById(dependentId);
    }

    @Test
    void findAllPagedShouldReturnPage() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Category> result = service.findAllPaged(pageRequest);
       assertNotNull(result);
    }

    @Test
    void deleteShouldDoNothingWhenIDExists() {
        assertDoesNotThrow(() -> {
            service.deleteCategory(existingId);
        });
        verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteCategory(nonExistingId);
        });
        verify(repository, times(1)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowDataBaseExceptionWhenDependentId() {
        assertThrows(DataBaseException.class, () -> {
            service.deleteCategory(dependentId);
        });
        verify(repository, times(1)).deleteById(dependentId);
    }
}
