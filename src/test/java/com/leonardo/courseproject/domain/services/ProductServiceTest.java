package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.models.Product;
import com.leonardo.courseproject.domain.repositories.ProductRepository;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private PageImpl<Product> pageable;
    private Product product;

    @BeforeEach
    void Setup() throws Exception {
        product = Factory.createProduct();
        pageable = new PageImpl<>(List.of(product));

        when(productRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(pageable);
    }

    @Test
    @DisplayName("Find all should return a page")
    void findAllPagedShouldReturnPage() {
        PageRequest pageRequest = PageRequest.of(0, 12);
        Page<Product> result = productService.finaAllPaged(pageRequest);
        assertNotNull(result);
    }
}
