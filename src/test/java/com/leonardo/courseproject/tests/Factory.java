package com.leonardo.courseproject.tests;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.models.Product;

public class Factory {

    public static Category createCategory() {
        Category category = new Category(1L, "Electronics");
        return category;
    }

    public static Product createProduct() {
        Product product = new Product(1L, "Iphone", 5999.99);
        product.getCategories().add(createCategory());
        return product;
    }
}
