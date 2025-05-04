package com.todo.task_manager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="TASK_MANAGER")
@Data
@NoArgsConstructor
@Getter
@Setter
public class TaskManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public TaskManager(Long id, String title, String description, TaskStatus status, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
    }

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
