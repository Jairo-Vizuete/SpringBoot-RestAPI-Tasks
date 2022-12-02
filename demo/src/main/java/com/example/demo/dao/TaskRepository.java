package com.example.demo.dao;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByDone(Integer done);

    @Override
    Optional<Task> findById(Integer integer);
}
