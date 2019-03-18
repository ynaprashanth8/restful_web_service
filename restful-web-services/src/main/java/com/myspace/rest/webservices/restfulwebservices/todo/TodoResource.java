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
public class TodoResource {
	
	@Autowired
	private TodoHardcodedservice todoservice;
	
	@GetMapping("/helloTodo")
	public String HelloTodo() { 
		return "todo" ;
	}	
	
	@GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username){
		return todoservice.findAll();
    }
	
	@GetMapping("/users/{username}/todos/{id}")
    public Todo getUserById(@PathVariable String username, @PathVariable long id){
		return todoservice.findById(id);
    }
	
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
	     Todo UpdateTodo = todoservice.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo){
	     Todo createTodo = todoservice.save(todo);
	 	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(createTodo.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		Todo todo = todoservice.deleteTodoById(id);
		if(todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}


