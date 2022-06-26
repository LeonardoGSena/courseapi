package com.leonardo.courseproject.api.controllers;

import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.services.CategoryService;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private Category category;
    private PageImpl<Category> page;

    @BeforeEach
    void setUp() throws Exception {
        category = Factory.createCategory();
        new PageImpl<>(List.of(this.category));
        when(categoryService.findAllPaged(any())).thenReturn(page);
    }

    @Test
    void findAllPagedShouldReturnPage() throws Exception {
        mockMvc.perform(get("/categories")).andExpect(status().isOk());
    }
}
