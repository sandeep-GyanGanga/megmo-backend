package com.megmo.megmoProject.repo;

import com.megmo.megmoProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User, Long> {

public User	findByUsername(String username);
}
