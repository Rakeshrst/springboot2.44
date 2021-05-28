package com.learnspring.SpringBoot24.usermodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	
	private static List<User> users =new ArrayList<>();
	
	
	private static int userCount=3;
	
	
	static {
		users.add(new User(1,"Rakesh", new Date()));
		users.add(new User(2,"Adam", new Date()));
		users.add(new User(3,"Eves", new Date()));
	}

	public List<User> findAll(){
		return users;
	}
	
	
	public User save(User user) {
		if(user.getId()==null)
		user.setId(++userCount);
		
		
		users.add(user);
		return user;
	}
	
	
	public User findOne(int id) {
		
		Optional<User> userlist=users.stream().
				filter(p -> p.getId()==id).findFirst();
		
		if(userlist.isPresent())
		return userlist.get();
		else
		return null;
	}
	
	public User deleteUser(int id) {
		Iterator<User>iterator=users.iterator();
		while(iterator.hasNext()) {
			User user=iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	
	
	
}
