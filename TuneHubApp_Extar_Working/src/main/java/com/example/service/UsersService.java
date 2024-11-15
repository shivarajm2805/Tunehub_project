package com.example.service;

import com.example.entities.Users;

public interface UsersService {

	//This method is used for inserting or storing the users details into database.
	public String addUser(Users user);
	
	//This method is used for checking the email is present in database or not.
	public boolean emailExists(String email);
	
	//This method method is used for validation of user by using email and password
	public boolean validateUser(String email,String password);
	
	//This method is used for getting the role of the user using email.
	public String getRole(String email);

	//This method is used for getting the user using email.
	public Users getUser(String email);

	public void updateUser(Users user);
}
