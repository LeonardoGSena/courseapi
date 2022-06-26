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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository categoryRepository;

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

        when(categoryRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn((Page<Category>) pageRequest);
        when(categoryRepository.findById(existingId)).thenReturn(Optional.of(category));
        when(categoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        when(categoryRepository.getOne(existingId)).thenReturn(category);
        when(categoryRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

        when(categoryRepository.save(ArgumentMatchers.any())).thenReturn(category);

        doNothing().when(categoryRepository).deleteById(existingId);
        doThrow(ResourceNotFoundException.class).when(categoryRepository).deleteById(nonExistingId);
        doThrow(DataBaseException.class).when(categoryRepository).deleteById(dependentId);
    }

    @Test
    void findAllPagedShouldReturnPage() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Category> result = service.findAllPaged(pageRequest);
       assertNotNull(result);
    }

    @Test
    void findByIdShouldReturnCategoryWhenIdExists() {
        Category category = service.findById(existingId);
        assertNotNull(category);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    void ItShouldCreateANewCategory() {
        Category save = service.insertCategory(category);
        assertNotNull(save);
    }

    @Test
    void updateShouldReturnACategoryWhenIdExists() {
        Category category = service.updateCategory(existingId, this.category);
        assertNotNull(category);
    }

    @Test
    void updateShouldResourceNotFoundWhenIdDoesNotExist() {
        assertThrows(ResourceNotFoundException.class, () -> {
            Category category = service.updateCategory(nonExistingId, this.category);
        });
    }

    @Test
    void deleteShouldDoNothingWhenIDExists() {
        assertDoesNotThrow(() -> {
            service.deleteCategory(existingId);
        });
        verify(categoryRepository, times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteCategory(nonExistingId);
        });
        verify(categoryRepository, times(1)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowDataBaseExceptionWhenDependentId() {
        assertThrows(DataBaseException.class, () -> {
            service.deleteCategory(dependentId);
        });
        verify(categoryRepository, times(1)).deleteById(dependentId);
    }
}
