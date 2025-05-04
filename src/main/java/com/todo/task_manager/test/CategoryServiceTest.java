package com.todo.task_manager.test;

import com.todo.task_manager.entity.Category;
import com.todo.task_manager.repository.CategoryRepository;
import com.todo.task_manager.service.CategoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testUpdateCategoryPriority() {
        // Arrange
        Long categoryId = 1L;
        int newPriority = 5;

        Category mockCategory = new Category(1L, "Work", 1);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
        when(categoryRepository.save(mockCategory)).thenReturn(mockCategory);

        // Act
        Category updatedCategory = categoryService.updateCategoryPriority(categoryId, newPriority);

        // Assert
        assertEquals(categoryId, updatedCategory.getId());
        assertEquals("Work", updatedCategory.getName());
        assertEquals(newPriority, updatedCategory.getPriority());

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).save(mockCategory);
    }

    @Test
    void testUpdateCategoryPriority_CategoryNotFound() {
        // Arrange
        Long categoryId = 99L;
        int newPriority = 5;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryService.updateCategoryPriority(categoryId, newPriority);
        });

        assertEquals("Catgeory not found with id: 99", exception.getMessage());
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void testUpdateCategoryName_UnimplementedMethod() {
        // Arrange
        long categoryId = 1;
        String newName = "Personal";

        // Act & Assert
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            categoryService.updateCategoryName(categoryId, newName);
        });

        assertEquals("Unimplemented method 'updateCategoryName'", exception.getMessage());
    }
}
