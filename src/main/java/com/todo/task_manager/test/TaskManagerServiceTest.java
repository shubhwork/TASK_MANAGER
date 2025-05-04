package com.todo.task_manager.test;

import com.todo.task_manager.entity.Category;
import com.todo.task_manager.entity.TaskManager;
import com.todo.task_manager.entity.TaskStatus;
import com.todo.task_manager.repository.TaskManagerRepository;
import com.todo.task_manager.service.TaskManagerServiceImpl;
import com.todo.task_manager.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskManagerServiceImplTest {

    @Mock
    private TaskManagerRepository taskManagerRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TaskManagerServiceImpl taskManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetAllTasks() {
        // Arrange
        TaskManager task1 = new TaskManager(1L, "Task 1", "Description 1", TaskStatus.PENDING, new Category(1L, "Work", 1));
        TaskManager task2 = new TaskManager(2L, "Task 2", "Description 2", TaskStatus.COMPLETED, new Category(2L, "Personal", 2));
        List<TaskManager> mockTasks = Arrays.asList(task1, task2);

        when(taskManagerRepository.findAll()).thenReturn(mockTasks);

        // Act
        List<TaskManager> tasks = taskManagerService.getAllTasks();

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());

        verify(taskManagerRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskById() {
        // Arrange
        Long taskId = 1L;
        TaskManager mockTask = new TaskManager(taskId, "Task 1", "Description 1", TaskStatus.PENDING, new Category(1L, "Work", 1));

        when(taskManagerRepository.findById(taskId)).thenReturn(Optional.of(mockTask));

        // Act
        TaskManager task = taskManagerService.getTaskById(taskId);

        // Assert
        assertEquals(taskId, task.getId());
        assertEquals("Task 1", task.getTitle());
        assertEquals("Description 1", task.getDescription());
        assertEquals(TaskStatus.PENDING, task.getStatus());

        verify(taskManagerRepository, times(1)).findById(taskId);
    }

    @Test
    void testCreateTask() {
        // Arrange
        TaskManager newTask = new TaskManager(null, "New Task", "New Description", TaskStatus.PENDING, new Category(1L, "Work", 1));
        TaskManager savedTask = new TaskManager(1L, "New Task", "New Description", TaskStatus.PENDING, new Category(1L, "Work", 1));

        when(taskManagerRepository.save(newTask)).thenReturn(savedTask);

        // Act
        TaskManager task = taskManagerService.createTask(newTask);

        // Assert
        assertEquals(1L, task.getId());
        assertEquals("New Task", task.getTitle());
        assertEquals("New Description", task.getDescription());

        verify(taskManagerRepository, times(1)).save(newTask);
    }

    @Test
    void testUpdateTask() {
        // Arrange
        Long taskId = 1L;
        Category category = new Category(1L, "Work", 1);
        TaskManager existingTask = new TaskManager(taskId, "Old Title", "Old Description", TaskStatus.PENDING, category);
        TaskManager updatedTask = new TaskManager(taskId, "New Title", "New Description", TaskStatus.COMPLETED, category);

        when(taskManagerRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(taskManagerRepository.save(existingTask)).thenReturn(updatedTask);

        // Act
        TaskManager result = taskManagerService.updateTask(taskId, updatedTask);

        // Assert
        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
        assertEquals(TaskStatus.COMPLETED, result.getStatus());
        assertEquals(category, result.getCategory());

        verify(taskManagerRepository, times(1)).findById(taskId);
        verify(categoryRepository, times(1)).findById(category.getId());
        verify(taskManagerRepository, times(1)).save(existingTask);
    }

    @Test
    void testDeleteTask() {
        // Arrange
        Long taskId = 1L;

        doNothing().when(taskManagerRepository).deleteById(taskId);

        // Act
        taskManagerService.deleteTask(taskId);

        // Assert
        verify(taskManagerRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testGetTaskByStatus() {
        // Arrange
        TaskStatus status = TaskStatus.PENDING;
        TaskManager task1 = new TaskManager(1L, "Task 1", "Description 1", status, null);
        TaskManager task2 = new TaskManager(2L, "Task 2", "Description 2", status, null);
        List<TaskManager> mockTasks = Arrays.asList(task1, task2);

        when(taskManagerRepository.findByStatus(status)).thenReturn(mockTasks);

        // Act
        List<TaskManager> tasks = taskManagerService.getTaskByStatus(status);

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());

        verify(taskManagerRepository, times(1)).findByStatus(status);
    }

    @Test
    void testGetTaskByCategoryId() {
        // Arrange
        Long categoryId = 1L;
        TaskManager task1 = new TaskManager(1L, "Task 1", "Description 1", TaskStatus.PENDING, new Category(categoryId, "Work", 1));
        TaskManager task2 = new TaskManager(2L, "Task 2", "Description 2", TaskStatus.COMPLETED, new Category(categoryId, "Work", 1));
        List<TaskManager> mockTasks = Arrays.asList(task1, task2);

        when(taskManagerRepository.findByCategoryId(categoryId)).thenReturn(mockTasks);

        // Act
        List<TaskManager> tasks = taskManagerService.getTaskByCategoryId(categoryId);

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());

        verify(taskManagerRepository, times(1)).findByCategoryId(categoryId);
    }
}
