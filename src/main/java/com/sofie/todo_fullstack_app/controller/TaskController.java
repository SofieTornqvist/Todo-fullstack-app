package com.sofie.todo_fullstack_app.controller;


import com.sofie.todo_fullstack_app.model.Task;
import com.sofie.todo_fullstack_app.service.TaskService;
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

    // GET /tasks - returns all tasks
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    // POST /tasks - creates a new task
    @PostMapping
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }
}

