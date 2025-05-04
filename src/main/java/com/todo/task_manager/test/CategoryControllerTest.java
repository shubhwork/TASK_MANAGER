package com.todo.task_manager.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import com.todo.task_manager.controller.CategoryController;
import com.todo.task_manager.entity.Category;
import com.todo.task_manager.service.CategoryService;

public class CategoryControllerTest {
    @Mock
   private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    public CategoryControllerTest(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
void testCreateCategory() {
    // Arrange
    Category mockCategory = new Category(1L, "Work", 1); // Use 1L for Long
    when(categoryService.createCategory(any(Category.class))).thenReturn(mockCategory);

    // Act
    Category createdCategory = categoryController.createCategory(mockCategory);

    // Assert
    assertEquals(1L, createdCategory.getId()); // Use 1L for Long
    assertEquals("Work", createdCategory.getName());
    assertEquals(1, createdCategory.getPriority());

    verify(categoryService, times(1)).createCategory(mockCategory);
}
    @Test
    void testUpdateCategoryPriority(){
        Long categoryId=1L;
        int newPriority=2;
        Category mockCategory=new Category();
        mockCategory.setId(categoryId);
        mockCategory.setPriority(newPriority);
        mockCategory.setName("Test Category");
        // Mock the behavior of the categoryService
        when(categoryService.updateCategoryPriority(categoryId, newPriority)).thenReturn(mockCategory);

        Category updatedCategory=categoryController.updateCategoryPriority(categoryId, newPriority);

        assertEquals(categoryId, updatedCategory.getId());
        assertEquals("Test Category", updatedCategory.getName());
        assertEquals(newPriority, updatedCategory.getPriority());

        verify(categoryService, times(1)).updateCategoryPriority(categoryId, newPriority);
    }

}
