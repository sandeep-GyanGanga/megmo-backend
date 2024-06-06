package com.megmo.megmoProject.services.impl;

import com.megmo.megmoProject.entities.ResponseDomain;
import com.megmo.megmoProject.entities.Task;
import com.megmo.megmoProject.entities.User;
import com.megmo.megmoProject.repo.Taskrepo;
import com.megmo.megmoProject.services.TaskService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private Taskrepo taskrepo;
    @Override
    public ResponseEntity<?> addNewTask(Task task) {

        try {

            Task task1 = taskrepo.findByTaskTitle(task.getTaskTitle());

            if (task1==null){

                taskrepo.save(task);

                ResponseDomain.postResponse("Task has been added successfully");
            }
            else {

                return ResponseDomain.badRequest("Task title Already exits!");
            }

        }
        catch (Exception e){
            e.printStackTrace();
            ResponseDomain.badRequest("Something went wrong!");
        }



        return null;
    }

    @Override
    public ResponseEntity<?> editTask(Task task) {
        try {

                taskrepo.save(task);
                return ResponseDomain.successResponse();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteTask(Long taskId) {
        try {
            Task task= taskrepo.findById(taskId).get();
           taskrepo.deleteById(taskId);
            return ResponseDomain.successResponse("Task Deleted Successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllTask(Long userId,int pagesize) {

        User user = new User();
        user.setId(userId);

        Pageable pageable =null;
        JSONObject responseObj = new JSONObject();

        pageable = PageRequest.of(pagesize,5);

        Page<Task> taskPage =  taskrepo.findByUser(user,pageable);

        List<Task> taskList = taskPage.getContent();

        responseObj.put("data",taskList);
        responseObj.put("total_pages",taskPage.getTotalPages());

        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }
}
