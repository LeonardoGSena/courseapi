package com.leonardo.courseproject.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.courseproject.domain.models.Category;
import com.leonardo.courseproject.domain.services.CategoryService;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    private Category category;
    private PageImpl<Category> page;

    @BeforeEach
    void setUp() throws Exception {
        category = Factory.createCategory();
        new PageImpl<>(List.of(this.category));
        when(categoryService.findAllPaged(any())).thenReturn(page);
        when(categoryService.insertCategory(any())).thenReturn(category);
    }

    @Test
    void findAllPagedShouldReturnPage() throws Exception {
        ResultActions result = mockMvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    void findByIdShouldReturnCategory() throws Exception {
        ResultActions result = mockMvc.perform(get("/categories/1")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    void insertCategoryShouldCreateNewCategoryWhenIdExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(category);
        ResultActions result = mockMvc.perform(post("/categories")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

}
