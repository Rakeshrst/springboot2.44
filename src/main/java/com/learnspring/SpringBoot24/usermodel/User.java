package com.learnspring.SpringBoot24.usermodel;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details abnout the user.")
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2,message="Name should have 2 characters")
	@ApiModelProperty(notes="Name should have minimym two character")
	private String name;
	
	@Past(message = "Birthdate should be in the past")
	@ApiModelProperty(notes="BirthDate should be in the past")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	public User() {
		super();
	}

	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
