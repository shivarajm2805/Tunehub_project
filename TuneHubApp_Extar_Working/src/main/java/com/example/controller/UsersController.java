package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Song;
import com.example.entities.Users;
import com.example.service.SongService;
import com.example.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	
	@Autowired
	UsersService userv;
	@Autowired
	SongService serv;
	
	
	
	public UsersController(UsersService userv) {
		super();
		this.userv = userv;
	}
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user)
	{
		boolean userstatus=userv.emailExists(user.getEmail());

		if(userstatus==false) 
		{
			userv.addUser(user);
			return "registersuccess";
			  
		}
		else {
		
			return "registerfail";
		}
		
//		return "home";
	}
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session )
	{
		//here we creating session for user(both admin and customer)
		session.setAttribute("email", email);// and here we setting the session attribute or else it leads to user null exception
		
//		boolean loginstatus=userv.validateUser(email, password);
		
		// invoking validateUser() in service
		if(userv.validateUser(email, password)==true) 
		{
			// checking whether the user id admin or customer
			if(userv.getRole(email).equals("admin")) {
				return "adminhome";
			}
			else {
				return "customerhome";
			}
		}
		else {
			return "loginfail";
		}
	}
	
	@GetMapping("map-exploreSong")
	public String exploreSong(HttpSession session, Model model) /* here we creating a session
	 														object using HttpSession hence this session store the users credentials
	  														(i.e email and password) at the time of login*/
	{
		String email=(String) session.getAttribute("email");//getAttribute() method returns object hence we performing down-casting and convert into string
		Users user=userv.getUser(email);// here we fetching the user from database with help of getUser() method 
		boolean userStatus=user.isPremium();// here we fetching the status of the user(i.e true or false)
		
		if(userStatus==true) // if status is true then the user is premium user else user need make payment
		{
			List<Song> songlist=serv.getSong();// here we fetching all the songs from database by using getSong() method
			model.addAttribute("songs", songlist);
			return "displaysongCustomer";
		}
		else {
		
		return "payment";
		}
	}
	


}
