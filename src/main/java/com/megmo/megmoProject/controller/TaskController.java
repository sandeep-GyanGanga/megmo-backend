package com.megmo.megmoProject.controller;

import com.megmo.megmoProject.entities.Task;
import com.megmo.megmoProject.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
 private TaskService taskService;


    @PostMapping("/add-new-task")
    public ResponseEntity<?> saveNewTask(@RequestBody Task task){

        return taskService.addNewTask(task);
    }

    @PutMapping("/edit-task")
    public ResponseEntity<?> editTask(@RequestBody Task task){

        return taskService.editTask(task);
    }

    @GetMapping("/get-all-tasks/{userId}/{pagesize}")
    public ResponseEntity<?> getAllTask(@PathVariable Long userId,@PathVariable int pagesize){

        return taskService.getAllTask(userId,pagesize);
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }


}
