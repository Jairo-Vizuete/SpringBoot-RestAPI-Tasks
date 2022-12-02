package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksList(Integer done){
        return this.taskRepository.findByDone(done);
    }

    public List<Task> getAllTasks(){
        return this.taskRepository.findAll();
    }

    @Transactional
    public void createTask(Task task){
        Optional<Task> taskObj = this.taskRepository.findById(task.getId());
        if (taskObj.isPresent()){
            log.info("This meaning that the task with id: {} already exists",task.getId());
        }
        System.out.println("Here we are gonna create the task");
        this.taskRepository.save(task);
    }
}
