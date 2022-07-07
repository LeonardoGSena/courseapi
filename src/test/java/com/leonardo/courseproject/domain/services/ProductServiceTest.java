package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.models.Product;
import com.leonardo.courseproject.domain.repositories.ProductRepository;
import com.leonardo.courseproject.domain.services.exceptions.ResourceNotFoundException;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private PageImpl<Product> page;
    private Product product;
    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void Setup() throws Exception {
        product = Factory.createProduct();
        page = new PageImpl<>(List.of(product));
        existingId = 1L;
        nonExistingId = 100L;

//        when(productRepository.findAll((Pageable) any())).thenReturn(page);
        when(productRepository.find(any(), any(), any())).thenReturn(page);
        when(productRepository.findById(existingId)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.getOne(existingId)).thenReturn(product);
        when(productRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

    }

    @Test
    @DisplayName("Find all should return a page")
    void findAllPagedShouldReturnPage() {
        PageRequest pageable = PageRequest.of(0, 12);
        Page<Product> result = productService.findAllPaged(0L, "", pageable);
        assertNotNull(result);
    }

    @Test
    @DisplayName("It should return a product when id exists")
    void findByIdShouldReturnProductWhenIdExists() {
        Product product = productService.findProductById(existingId);
        assertNotNull(this.product);
    }

    @Test
    @DisplayName("It should Throw Resource not found exception when id does not exist")
    void findByIdShouldThroeResourceNotFoundExceptionWhenIdDoesNotExist() {
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findProductById(nonExistingId);
        });
    }

    @Test
    @DisplayName("It should create a new product")
    void ItShouldCreateANewProduct() {
        Product product = productService.insertNewProduct(this.product);
        assertNotNull(product);
    }

    @Test
    @DisplayName("It should update a product")
    void updateSouldReturnProductWhenIdExist() {
        Product updateProduct = productService.updateProduct(existingId, product);
        assertNotNull(updateProduct);
    }

    @Test
    @DisplayName("It should throw Resource not found exception when id does not exist")
    void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.updateProduct(nonExistingId, product);
        });
    }
}
