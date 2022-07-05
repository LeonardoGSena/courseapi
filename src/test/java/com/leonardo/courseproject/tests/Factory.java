package com.leonardo.courseproject.tests;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.models.Product;

public class Factory {

    public static Category createCategory() {
        return  new Category(2L, "TV");
    }

    public static Product createProduct() {
        Product product = new Product(1L, "Iphone", 5999.99);
        product.getCategories().add(createCategory());
        return product;
    }
}
