package com.todo.task_manager.service;

import com.todo.task_manager.entity.Category;
import com.todo.task_manager.entity.TaskManager;
import com.todo.task_manager.entity.TaskStatus;
import com.todo.task_manager.repository.TaskManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import com.todo.task_manager.repository.CategoryRepository;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskManagerService{
    private final TaskManagerRepository taskManagerRepository;
    private final CategoryRepository categoryRepository;
    public TaskServiceImpl(TaskManagerRepository taskManagerRepository, CategoryRepository categoryRepository){
        this.taskManagerRepository = taskManagerRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public List<TaskManager> getAllTasks() {
        return taskManagerRepository.findAll();
    }

    @Override
    public TaskManager getTaskById(Long id) {
        return taskManagerRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public TaskManager createTask(TaskManager taskManager) {
        return taskManagerRepository.save(taskManager);
    }

    @Override
    public TaskManager updateTask(Long id, TaskManager taskManager) {
        TaskManager task=taskManagerRepository.findById(id).orElseThrow(() -> new RuntimeException(id+"Task not found"));
        task.setTitle(taskManager.getTitle());
        task.setDescription(taskManager.getDescription());
        task.setCategory(taskManager.getCategory());
        task.setStatus(taskManager.getStatus());
        if(taskManager.getCategory()!=null){
            Category category=categoryRepository.findById(taskManager.getCategory().getId()).orElseThrow(() -> new RuntimeException("Category not found"));
            category.setName(taskManager.getCategory().getName());
            task.setCategory(category);
        }
        return taskManagerRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskManagerRepository.deleteById(id);
    }

    @Override
    public List<TaskManager> getTaskByStatus(TaskStatus status) {
        return taskManagerRepository.findByStatus(status);
    }

    @Override
    public List<TaskManager> getTaskByCategoryId(Long categoryId) {
        return taskManagerRepository.findByCategoryId(categoryId);
    }
}
