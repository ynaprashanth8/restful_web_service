package com.myspace.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
@Entity
public class User {
    
	@Id
	@GeneratedValue
	private int Id;
	@Size(min=2, message="Name should have atlest 2 char")
	@ApiModelProperty(notes="Name should have atlest 2 char")
	private String name;
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthDate;
	
	protected User() {
	}

	public User(int id, String name, Date birthDate) {
		super();
		Id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return String.format("User [Id=%s, name=%s, birthDate=%s]", Id, name, birthDate);
	}

}
