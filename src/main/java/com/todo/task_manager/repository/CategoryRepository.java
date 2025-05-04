package com.todo.task_manager.repository;

import com.todo.task_manager.entity.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findById(int id);

}
