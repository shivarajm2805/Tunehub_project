package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Users;
import com.example.repository.UsersRepository;
@Service
public class UsersServiceImplementation implements UsersService
{
//   @Autowired
	UsersRepository repo;/* declaring the UsersRepository type of variable for establishing  the connection 
	 					with UsersRepository*/
	
	public UsersServiceImplementation(UsersRepository repo) {
	super();
	this.repo = repo;
}

	@Override
	public String addUser(Users user)/*This is a overriden method for inserting the users values
	 								 into database and it return String type of value */ 
	{
		repo.save(user);/*Here we calling the save() method present in the UsersRepository
		 				for inserting the users values into database*/
		return "user is created and saved";
	}

	@Override
	/*This is a overriden method for checking the presence of email in database and it return boolean type of value*/
	public boolean emailExists(String email) 
	{
		if(repo.findByEmail(email)==null)/*Here we calling the findByEmail(email) method which
		 							     present in the UsersRepository and it return users */
		{
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	/*This is a overriden method for validating the users by using email and password and it return boolean type of value*/
	public boolean validateUser(String email, String password)
	{
		Users user=repo.findByEmail(email);/*Here we calling the findByEmail(email) method which
	    
	    	 									in the UsersRepository and it return all the Users details*/
		if(user==null)/*this method is for checking user object is null or not if user is null(null bcz that email not present in the database)
						it return false and in the UsersController it redirect the control to loginfail page*/
		{
			return false;
		}
		
		String db_password=user.getPassword();// here we taking the password from users by getPassword() method for comaparision
		if(db_password.equals(password)) // if password match it return true else false
		{
			return true;
		}
		else {
		
		return false;
		}
	}

	@Override
	/*This is a overriden method for getting the users role by using email*/
	public String getRole(String email) {
		/*here we call findByEmail() method and it returns users. 
		 * By using users we extracting the role by using getRole() method*/
		return (repo.findByEmail(email).getRole());
	}

	/*This is override method for fetching the users from database by using email for checking he is a premium user or not*/
	@Override
	public Users getUser(String email) {
		
		return repo.findByEmail(email);//*here we call findByEmail() method and it returns users. 
	}

	// This is a overriden method for updating the user
	@Override
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		repo.save(user);// by using save() method we updating the user as premium user
		
	}
	
	

}
