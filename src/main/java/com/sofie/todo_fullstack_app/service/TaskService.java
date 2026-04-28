package com.sofie.todo_fullstack_app.service;

import com.sofie.todo_fullstack_app.model.Task;
import com.sofie.todo_fullstack_app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Marks this class as a service component (business logic layer)
// Handles business logic for Task entities
public class TaskService {

    // Repository is used to communicate with the database
    private final TaskRepository taskRepository;

    // Constructor injection: Spring automatically injects TaskRepository here
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Fetch all tasks from the database
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // Save a new task to the database and return the saved entity (with generated ID)
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

}
