package com.myspace.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
    // get users list 
	@GetMapping("/users")
	public List<User> retrieveAllUser(){
		return userDaoService.findAll();
	}
	// get users by Id 
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
		return user;
	}
	
	// delete user by Id
	@DeleteMapping("/users/{id}")
	public @ResponseBody void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
	}
	
	//This will return a proper response status with loaction 
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(saveUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
