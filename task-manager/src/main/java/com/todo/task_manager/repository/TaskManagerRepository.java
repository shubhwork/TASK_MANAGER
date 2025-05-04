package com.todo.task_manager.repository;

import com.todo.task_manager.entity.TaskManager;
import com.todo.task_manager.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskManagerRepository extends JpaRepository<TaskManager, Long> {
    List<TaskManager> findByStatus(TaskStatus status);
    List<TaskManager> findByCategoryId(Long categoryId);
    // This interface will automatically provide CRUD operations for TaskManager entity
    // No need to implement any methods here, Spring Data JPA will handle it
}
