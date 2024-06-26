package com.megmo.megmoProject.controller;

import java.util.HashSet;
import java.util.Set;

import com.megmo.megmoProject.entities.Role;
import com.megmo.megmoProject.entities.User;
import com.megmo.megmoProject.entities.UserRole;
import com.megmo.megmoProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/")
	public  ResponseEntity<?> CreateUser(@RequestBody User user ) {
		
		System.out.println("in create user method controller");
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		
		role.setRoleId(11L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		
		userRole.setRole(role);
		userRole.setUser(user);
		
		roles.add(userRole);		
		
		
		return this.userService.createUser(user, roles);
		
	}
	
	@GetMapping("/{username}")
	private ResponseEntity<?> getUser(@PathVariable("username") String username) {
		
		
		return this.userService.getUserByName(username);
		
	}
	
	@DeleteMapping("/delete-user/{userId}")
	
	public void deleteUserById(@PathVariable("userId") Long UserId) {
		
		this.userService.deleteUserById(UserId);
		
	}
	
//	
	
	
	
	
	
	
}
