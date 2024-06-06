package com.megmo.megmoProject.services;

import java.util.Set;

import com.megmo.megmoProject.entities.User;
import com.megmo.megmoProject.entities.UserRole;
import org.springframework.http.ResponseEntity;



public interface UserService {
	
	public  ResponseEntity<?> createUser(User user, Set<UserRole> userRoles );
	
	public ResponseEntity<?> getUserByName(String username);
	
	public void deleteUserById(Long UserId);
		
	
		
	
		
	

}
