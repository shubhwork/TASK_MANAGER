package com.todo.task_manager.controller;

import com.todo.task_manager.entity.Category;
import com.todo.task_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        try {
            return categoryService.getAllCategories();
        }
    catch(Exception e){
            throw new RuntimeException("Error fetching categories: "+e.getMessage());
    }
    }
    @PostMapping
    public Category createCategory(@RequestBody Category category){
        try{
        return categoryService.createCategory(category);
    }
        catch(RuntimeException e){
            throw new RuntimeException("Error creating category: "+e.getMessage());
        }
    }
    @PutMapping("/{id}/priority")
    public Category updateCategoryPriority(@PathVariable int id, @RequestParam int priority){
        try{
        return categoryService.updateCategoryPriority(id,priority);
    }
        catch(RuntimeException e){
            throw new RuntimeException("Error updating category priority: "+e.getMessage());
        }

    }
}
