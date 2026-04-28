package com.sofie.todo_fullstack_app.repository;

import com.sofie.todo_fullstack_app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
