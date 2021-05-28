package com.learnspring.SpringBoot24.usermodel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	
	//GET /users
	//retieveAllUsers
	
	@GetMapping(path="/users")
	public List<User> retriveAllUsers(){
		
		return service.findAll();
	}
	
	/**
	@GetMapping(path="/users/{id}")
	private User retrieveUser(@PathVariable int id) {
	User foundUser= service.findOne(id);
	
	if(foundUser==null)
		throw new UserNotFoundException("id - "+id);
	
	
	return foundUser;
	}
	**/
	
	
	
	@PostMapping("/users")
	private ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser=service.save(user);
		URI location =ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
	
	
	@DeleteMapping(path="/users/{id}")
	private void deleteUser(@PathVariable int id) {
	User foundUser= service.deleteUser(id);
	
	if(foundUser==null)
		throw new UserNotFoundException("id - "+id);
	

	}
	
	

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserbyId(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}
	

}
