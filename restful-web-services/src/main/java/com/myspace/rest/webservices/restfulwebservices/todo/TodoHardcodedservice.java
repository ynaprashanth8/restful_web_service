package com.myspace.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TodoHardcodedservice {
	
	private static List<Todo> todos = new ArrayList<>();
	static long idCounter =  0;
	static {
	todos.add(new Todo(++idCounter, "Prashnath", "Learn Microservices", new Date(), false));
	todos.add(new Todo(++idCounter, "Prashnath", "Learn Angular", new Date(), false));
	todos.add(new Todo(++idCounter, "Prashnath", "Learn AWS", new Date(), false));
	}
	
	public List<Todo> findAll(){
		return todos;
	}
	
	
	public Todo save(Todo todo) {
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			 todos.add(todo);
		}else {
			deleteTodoById(todo.getId());
		   todos.add(todo);
		}
		return todo;
	}
	
	public Todo deleteTodoById(long id) {
		Todo todo = findById(id);
		todos.remove(todo);
		return todo;
	}

	public Todo findById(long id) {
		for(Todo todo : todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
}
