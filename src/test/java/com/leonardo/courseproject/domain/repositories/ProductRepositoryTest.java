package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.Product;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Long totalProducts;

    @BeforeEach
    void Setup() throws Exception {
        totalProducts = 6L;
    }

    @Test
    void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);
        product = productRepository.save(product);

        assertNotNull(product.getId());
        assertEquals(totalProducts + 1, product.getId());
    }
}
