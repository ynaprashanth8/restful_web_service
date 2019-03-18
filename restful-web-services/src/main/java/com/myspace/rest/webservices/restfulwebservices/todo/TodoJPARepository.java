package com.myspace.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJPARepository extends JpaRepository<Todo, Long>{
	
	List<Todo> findByUsername(String username);

}
