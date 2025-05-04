package com.todo.task_manager.service;

import com.todo.task_manager.entity.TaskManager;
import com.todo.task_manager.entity.TaskStatus;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskManagerService {
    List<TaskManager> getAllTasks();
    TaskManager getTaskById(Long id);
    TaskManager createTask(TaskManager taskManager);
    TaskManager updateTask(Long id, TaskManager taskManager);
    void deleteTask(Long id);
    List<TaskManager> getTaskByStatus(TaskStatus status);
    List<TaskManager> getTaskByCategoryId(Long categoryId);
}
