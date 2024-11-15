package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Users;

public interface UsersRepository extends JpaRepository<Users,Integer>
{

	/* here we fetching the email from database by using findByEmail() method 
   and this method return Users type value and it contains all info of users like email,role,name,
   password,gender,id and address  */
	public Users findByEmail(String email);
	
}
