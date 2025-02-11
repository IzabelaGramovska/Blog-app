package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.CategoryResponse;
import org.springframework.stereotype.Service;


public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategory(Long categoryId);
}
