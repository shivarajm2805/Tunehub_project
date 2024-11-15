package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Song;
import com.example.repository.SongRepository;
@Service
public class SongServiceImplementation implements SongService
{
	@Autowired
	SongRepository repo;
	
	
	@Override
	/*This is a overriden method for inserting the Song values
	 into database and it return String type of value */
	public String addSong(Song song) {
		repo.save(song);/*Here we calling the save() method present in the SongRepository
						for inserting the song values into database*/
		return "Song is added successfully";
	}
	
	
	@Override
	/*This is a overriden method for checking the presence of name in database and 
	 it return boolean type of value*/
	public boolean nameExist(String name) {
		
		if(repo.findByName(name)==null) /*Here we calling the findByName(name) method which
	     								present in the SongRepository and it return Song */
		{
			return false;
		}
		else 
		{
			return true;
		}
	}


	@Override
	/*This is a overriden method for getting the all songs details from database 
	 and return List of songs*/
	public List<Song> getSong() {
		List<Song> ls=repo.findAll();/*Here we calling the findAll() method which is present in 
		 							SongRepository for fetching all the	data from database*/
		return ls;
	}


	@Override
	public void updateSong(Song song) {
		// TODO Auto-generated method stub
		repo.save(song);
		
	}

}
