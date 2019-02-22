package com.myspace.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//This indicates that the class interact with the database 
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
