package com.megmo.megmoProject.services;

import com.megmo.megmoProject.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface TaskService {

    public ResponseEntity<?> addNewTask(Task task);

    public ResponseEntity<?> editTask(Task task);

    public ResponseEntity<?> deleteTask(Long taskId);

    public ResponseEntity<?> getAllTask(Long UserId,int pagesize);


}
