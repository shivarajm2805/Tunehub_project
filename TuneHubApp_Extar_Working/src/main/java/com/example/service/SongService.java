package com.example.service;

import java.util.List;

import com.example.entities.Song;

public interface SongService 
{
	//This method is used for inserting or storing the Song details into database.
	public String addSong(Song song);
	//This method is used for checking the name is present in database or not.
	public boolean nameExist(String name);
	
	/*This method is for fetching the all the data from database and it returns
	 List of songs*/
	public List<Song> getSong();
	public void updateSong(Song song);
}
