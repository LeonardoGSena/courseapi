package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.Product;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Long totalProducts;
    private Long existsId;
    private Long nonExistId;

    @BeforeEach
    void Setup() throws Exception {
        totalProducts = 6L;
        existsId = 6L;
        nonExistId = 7L;
    }

    @Test
    @DisplayName("Save product with autoincrement")
    void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);
        product = productRepository.save(product);

        assertNotNull(product.getId());
        assertEquals(totalProducts + 1, product.getId());
    }

    @Test
    @DisplayName("Delete product when id exists")
    void deleteShouldDeleteObjectWhenIdExists() {
        productRepository.deleteById(existsId);
        Optional<Product> product = productRepository.findById(existsId);

        assertFalse(product.isPresent(), "It should return false");
    }

    @Test
    @DisplayName("Delete should throw EmptyResultDataAccessException when id doesn't exist")
    void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
           productRepository.deleteById(nonExistId);
        });
    }
}
