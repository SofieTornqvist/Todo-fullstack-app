package com.sofie.todo_fullstack_app.controller;


import com.sofie.todo_fullstack_app.model.Task;
import com.sofie.todo_fullstack_app.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller (returns JSON responses)
@RequestMapping("/tasks") // Base URL for all endpoints in this controller
public class TaskController {

    private final TaskService taskService;

    // Constructor injection: Spring injects TaskService automatically
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ===== CRUD =====

    // GET /tasks - returns all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // GET /tasks/{id} - returns a specifik task
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    // POST /tasks - creates a new task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.status(201).body(taskService.addTask(task));
    }

    // PUT /tasks/{id} - updates a task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }


    // DELETE /tasks/{id} - deletes a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}

