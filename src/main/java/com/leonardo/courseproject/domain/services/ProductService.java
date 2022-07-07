package com.leonardo.courseproject.domain.services;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.models.Product;
import com.leonardo.courseproject.domain.repositories.CategoryRepository;
import com.leonardo.courseproject.domain.repositories.ProductRepository;
import com.leonardo.courseproject.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<Product> findAllPaged(Long categoryId, String name, Pageable pageable) {
        List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getOne(categoryId));
        Page<Product> page = productRepository.find(categories, name, pageable);
        productRepository.findProductsWithCategories(page.getContent());
        return page.map(x -> new Product(x, x.getCategories()));
    }

    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Transactional
    public Product insertNewProduct(Product product) {
       return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        try {
            Product newProduct = productRepository.getOne(id);
            updateData(newProduct, product);
            return productRepository.save(newProduct);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void updateData(Product newProduct, Product product) {
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
    }
}
