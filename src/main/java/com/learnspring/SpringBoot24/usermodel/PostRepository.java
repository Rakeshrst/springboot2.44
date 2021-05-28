package com.learnspring.SpringBoot24.usermodel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
}
