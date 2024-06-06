package com.megmo.megmoProject.services.impl;

import java.util.Set;

import com.megmo.megmoProject.entities.User;
import com.megmo.megmoProject.entities.UserRole;
import com.megmo.megmoProject.repo.RoleRepository;
import com.megmo.megmoProject.repo.UserRepository;
import com.megmo.megmoProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.exam.examServer.services.ResponseDomain;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public  ResponseEntity<?> createUser(User user, Set<UserRole> userRoles) {
			
		
	User local =	this.userRepository.findByUsername(user.getUsername());
	
	if (local!=null) {
		
		System.out.println("user already exist in database");
		return ResponseDomain.badRequest("User alredy exist!!");
		
	}
	else {
		
//	TO DO: 	saving  roles in db
		for(UserRole ur:userRoles)
		{
			
			this.roleRepository.save(ur.getRole());
		}
//		tO do : adding userRoles in user and saving user in DB.
		user.getUserRoles().addAll(userRoles);
		local = this.userRepository.save(user);
		
	}
		
		return new ResponseEntity<>(local,HttpStatus.OK);
	}

	
	
	@Override
	public  ResponseEntity<?> getUserByName(String username) {
		
		User localUser = this.userRepository.findByUsername(username);
		
		
		return new ResponseEntity<>(localUser,HttpStatus.OK);
	}



	@Override
	public void deleteUserById(Long UserId) {
		// TODO Auto-generated method stub
		
		this.userRepository.deleteById(UserId);
		
	}



	
}
