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

    // ===== CRUD =====

    // Fetch all tasks from the database
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // Save a new task to the database and return the saved entity (with generated ID)
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Update an existing task by its ID
    public Task updateTask(Long id, Task updatedTask) {
        // Try to find the existing task in the database
        Task existingTask = taskRepository.findById(id).orElse(null);

        // Check if the task exists
        if (existingTask != null) {
            // Update fields with new values from the incoming object
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());

            // Save the updated task back to the database
            return taskRepository.save(existingTask);
        }
        // If task was not found, return null (could be handled better later)
        return null;
    }

    // Delete task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
