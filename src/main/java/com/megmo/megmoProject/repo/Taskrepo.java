package com.megmo.megmoProject.repo;

import com.megmo.megmoProject.entities.Task;
import com.megmo.megmoProject.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Taskrepo extends JpaRepository<Task, Long> {

   public Task findByTaskTitle(String title);

   public Page<Task> findByUser(User user, Pageable pageable);

   public void deleteByTaskTitle(String title);
}
