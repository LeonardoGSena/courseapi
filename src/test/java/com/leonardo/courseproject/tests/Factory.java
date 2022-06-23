package com.leonardo.courseproject.tests;

import com.leonardo.courseproject.domain.models.Category;

public class Factory {

    public static Category createCategory() {
        Category category = new Category(1L, "Electronics");
        return category;
    }
}
