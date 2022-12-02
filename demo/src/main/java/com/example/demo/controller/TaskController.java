package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.payload.request.TaskRequest;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "{done}")
    public ResponseEntity getTasksList(@PathVariable("done") Integer done){
        log.info("The list is: {}", done);
        List<Task> tasks = this.taskService.getTasksList(done);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping
    public ResponseEntity getAllTasks(){
        List<Task> tasks = this.taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskRequest request){
        log.info("It's gonna create a new task");
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());

        Task task = new Task();
        //task.setId(0);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDone(0);
        task.setCreatedAt(timestamp2);
        this.taskService.createTask(task);
        return ResponseEntity.ok().build();
    }
}
