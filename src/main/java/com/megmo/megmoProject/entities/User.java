package com.megmo.megmoProject.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


@javax.persistence.Entity
@Data
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String phone;

	private String email;
	
	private boolean enabled = true;
	
	private String profile;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	@JsonIgnore
	private Set<Task> userTask = new HashSet<>();
	
	public User(Long id, String username, String password, String firstname, String lastname, String phone,
			boolean enabled, String profile, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.enabled = enabled;
		this.profile = profile;
		this.email = email;
	}

	public String getProfile() {
		return profile;
	}
	

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

//	user ke pass kon kon si authority hai ye check karne ke liye spring security yhi funtion call karega.
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

			Set<Authority> set = new HashSet<>();
			
			this.userRoles.forEach(userRole ->{
				
				set.add(new Authority(userRole.getRole().getRoleName()));
			});

		
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public Set<Task> getUserTask() {
		return userTask;
	}

	public void setUserTask(Set<Task> userTask) {
		this.userTask = userTask;
	}

	public User(Long id, String username, String password, String firstname, String lastname, String phone, String email, boolean enabled, String profile, Set<UserRole> userRoles, Set<Task> userTask) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
		this.profile = profile;
		this.userRoles = userRoles;
		this.userTask = userTask;
	}
}
