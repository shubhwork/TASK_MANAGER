package com.todo.task_manager.service;

import com.todo.task_manager.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category updateCategoryPriority(Long id,int priority);
    Category updateCategoryName(Long id,String name);
}
