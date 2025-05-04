package com.todo.task_manager.service;

import com.todo.task_manager.entity.Category;
import com.todo.task_manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategoryPriority(Long id, int priority) {
    Category category=categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Catgeory not found with id: "+id));
        category.setPriority(priority);
        return categoryRepository.save(category);
}

    @Override
    public Category updateCategoryName(Long id, String name) {
        // TODO Auto-generated method stub
        Category category=categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Catgeory not found with id: "+id));
        category.setName(name);
        return categoryRepository.save(category);
    }
}
