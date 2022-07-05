package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> find(List<Category> categories, String name, Pageable pageable);

    List<Product> findProductsWithCategories(List<Product> products);
}
