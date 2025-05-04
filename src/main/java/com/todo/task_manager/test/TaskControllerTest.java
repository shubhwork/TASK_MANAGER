package com.todo.task_manager.test;

import com.todo.task_manager.controller.TaskController;
import com.todo.task_manager.entity.Category;
import com.todo.task_manager.entity.TaskManager;
import com.todo.task_manager.entity.TaskStatus;
import com.todo.task_manager.service.TaskManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskManagerService taskService;

    @InjectMocks
    private TaskController taskController;

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

        when(taskService.getAllTasks()).thenReturn(mockTasks);

        // Act
        List<TaskManager> tasks = taskController.getAllTasks();

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetTaskById() {
        // Arrange
        Long taskId = 1L;
        TaskManager mockTask = new TaskManager(taskId, "Task 1", "Description 1", TaskStatus.PENDING, new Category(1L, "Work", 1));

        when(taskService.getTaskById(taskId)).thenReturn(mockTask);

        // Act
        TaskManager task = taskController.getTaskById(taskId);

        // Assert
        assertEquals(taskId, task.getId());
        assertEquals("Task 1", task.getTitle());
        assertEquals("Description 1", task.getDescription());

        verify(taskService, times(1)).getTaskById(taskId);
    }
}
