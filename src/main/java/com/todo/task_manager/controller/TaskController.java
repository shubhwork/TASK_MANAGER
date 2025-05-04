package com.todo.task_manager.controller;

import com.todo.task_manager.entity.TaskManager;
import com.todo.task_manager.entity.TaskStatus;
import com.todo.task_manager.service.TaskManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class TaskController {

    private  TaskManagerService taskService;
    public TaskController(TaskManagerService taskService){
        this.taskService=taskService;
    }

    @GetMapping
    public List<TaskManager> getAllTasks(){
       return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public TaskManager getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @PostMapping
    public TaskManager createTask(@RequestBody TaskManager taskManager ){
        try {
            return taskService.createTask(taskManager);
        }
        catch(Exception e){
            throw new RuntimeException("Error creating task: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public TaskManager updateTask(@PathVariable Long id, @RequestBody TaskManager taskManager){
        try {
            return taskService.updateTask(id, taskManager);
        }
        catch(Exception e){
            throw new RuntimeException("Error updating task: "+e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        try {
            taskService.deleteTask(id);
        }catch(Exception e){
            throw new RuntimeException("Task not found");
        }
    }
    @GetMapping("/status/{status}")
    public List<TaskManager> getTaskByStatus(@PathVariable("status") String status){
        try {
            TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
            return taskService.getTaskByStatus(taskStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }
        }
    @GetMapping("/category/{categoryId}")
    public List<TaskManager> getTaskByCategoryId(@PathVariable Long categoryId){
        try {
            return taskService.getTaskByCategoryId(categoryId);
        }
        catch(Exception e) {
            throw new RuntimeException("Category not found");
        }
    }
}
