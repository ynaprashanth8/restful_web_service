package com.myspace.rest.webservices.restfulwebservices.todo;



import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoJPAResource {
	
	@Autowired
	private TodoHardcodedservice todoservice;
	
	@Autowired
	private TodoJPARepository todoJPARepo;
	
	@GetMapping("/jpa/helloTodo")
	public String HelloTodo() { 
		return "todo" ;
	}	
	
	@GetMapping("/jpa/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username){
		return todoJPARepo.findByUsername(username);
		//return todoservice.findAll();
    }
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo getUserById(@PathVariable String username, @PathVariable long id){
		return todoJPARepo.findById(id).get();
    }
	
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		 todo.setUsername(username);
	     Todo UpdateTodo = todoJPARepo.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo){
		todo.setUsername(username);
	     Todo createTodo = todoJPARepo.save(todo);
	 	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(createTodo.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		todoJPARepo.deleteById(id);
		return ResponseEntity.notFound().build();
	}
}


