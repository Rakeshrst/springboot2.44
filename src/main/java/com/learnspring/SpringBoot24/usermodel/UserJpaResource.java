package com.learnspring.SpringBoot24.usermodel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaResource {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepo;
	//GET /users
	//retieveAllUsers
	
	@GetMapping(path="/jpa/users")
	public List<User> retriveAllUsers(){
		
		return userRepository.findAll();
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
	
	
	
	@PostMapping("/jpa/users")
	private ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser=userRepository.save(user);
		URI location =ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
	
	
	@DeleteMapping(path="/jpa/users/{id}")
	private void deleteUser(@PathVariable int id) {
	userRepository.deleteById(id);
	}
	
	

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUserbyId(@PathVariable int id) {
		User user ;
		
		if(userRepository.findById(id).isPresent())
		user = userRepository.findById(id).get();
		else
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
	
	@GetMapping(path="/jpa/posts")
	public List<Post> retriveAllPosts(){
		
		return postRepo.findAll();
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> create(@PathVariable int id,@RequestBody Post post){
		Optional<User> userOptional=userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		User user=userOptional.get();
		post.setUser(user);
		
		postRepo.save(post);
		
		URI location =ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}

}
