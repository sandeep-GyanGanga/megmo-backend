package com.megmo.megmoProject.services.impl;

import com.megmo.megmoProject.entities.User;
import com.megmo.megmoProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




/*
 * jab bhi spring security user ko login karayega to , spring security call karta hai UserDetails service class ko ab kyunki 
 *  
 *  UserDetails ek interface isliye hame userDetails ki impleamentation karni hogi , kyunki uss interface ke pass ek method hota hai 	
 *  
 *  loadUserByUsername() , isi method ka use karke user ko load karta hai  databse se.  agar user database mein hai to spring user ko access 
 *  
 *   dega , otherwise exception throw kar dega userNoFoundException.  iss tarah automatically spring user ki details db se nikalkar verification kar lega.
 *   
 *   
 *   
 * 
 * */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	
//		finding user in db by username 
		User user = this.userRepository.findByUsername(username);
		
		if (user==null) {
			System.out.println("user not found ");
			
			throw new UsernameNotFoundException("No User found");
		}

// agar user mil jayega to user ka object return kar denge.
		return user;
	}
	
	
	
	

}
