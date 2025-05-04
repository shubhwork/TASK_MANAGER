package com.todo.task_manager.service;

import com.todo.task_manager.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category updateCategoryPriority(int id,int priority);
    Category updateCategoryName(int id,String name);
}
