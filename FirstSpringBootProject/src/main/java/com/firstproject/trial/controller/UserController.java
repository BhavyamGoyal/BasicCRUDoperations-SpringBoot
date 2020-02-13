package com.firstproject.trial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.trial.exception.ResourceNotFoundException;
import com.firstproject.trial.model.User;
import com.firstproject.trial.repository.UserRepository;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String get() {
		System.out.println("####################0");
		return "sfsds";
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		System.out.println("####################1");
		return this.userRepo.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/users")
	public User createEmployee(@RequestBody User user) {
		System.out.println(user.toString());
		return userRepo.save(user);
	}
	
	@PostMapping("/users/{id}")
	public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long id,@RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		user.setEmailID(userDetails.getEmailID());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		final User updatedUser = userRepo.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		userRepo.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
