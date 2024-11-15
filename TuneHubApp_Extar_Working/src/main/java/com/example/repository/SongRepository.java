package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Song;

public interface SongRepository extends JpaRepository<Song, Integer>
{

	/* here we fetching the name of the song from database by using findByName() method 
	   and this method return Song type value and it contains all info of Song  */
	public Song findByName(String name);
	
	
}
