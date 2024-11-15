package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Song;
import com.example.service.SongService;

@Controller
public class SongController {
	@Autowired
	SongService serv;
	
	@PostMapping("add-songs")// @ModelAttribute this annotation binds the parameters sending from the frontend or view
	public String addSong(@ModelAttribute Song song) //This method is for adding the user(Both admin and customer) into database
	{
		boolean namestatus=serv.nameExist(song.getName());//here we checking the existence of users in database using nameExist(song.getName()) method
		if(namestatus==false) {
		serv.addSong(song);// here we calling the addSong() method with help of SongService type variable serv present in the SongService 
		return "songsuccess";
		}
		else {
			return "songfail";
		}
		
	}
	@GetMapping("map-view")
	public String readSong(Model model) //This method for displaying songs for admin
	{
		
		//fetching the songs data through service and repository
		List<Song> songsList=serv.getSong();// here we calling the getSong() method with help of SongService type variable serv  
		
		//Adding the fetched data in the model
		model.addAttribute("songs", songsList);//here songsList is access by using songs
		
		//sending control to viewsong.html
		return "displaysongAdmin";
	}
	@GetMapping("map-customerSong")
	public String viewCustomerSong(Model model) //This method is for displaying songs for customer
	{
		boolean primeStatus=true;
		if(primeStatus==true) {
			List<Song> songlist=serv.getSong();
			model.addAttribute("songs", songlist);
//			
			return "displaysongCustomer";
		}
		else {
			return "makepayment";
		}
	}

}
