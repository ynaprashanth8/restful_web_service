package com.myspace.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
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
		List<User> users = userDaoService.findAll();
		if(users.isEmpty()) throw new UserNotFoundException("user is null");
		return users;
	}
	// get users by Id 
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
		
		/*
		 * HATEOAS : (Hypermedia as the Engine of Application State) 
		 * Creating a link to retrieve all the users based on method name (for this we have dependency provied by springboot).
		 *  Here we are returning both data and the link to the other resource with reference all-users.
		 */
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo =linkTo(methodOn(this.getClass()).retrieveAllUser());
		
		resource.add(linkTo.withRel("all-user"));
		return resource;
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
