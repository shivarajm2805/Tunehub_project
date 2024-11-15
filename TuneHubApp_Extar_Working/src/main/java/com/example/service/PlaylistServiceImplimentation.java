package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.PlayList;
import com.example.entities.Song;
import com.example.repository.PlaylistRepository;
@Service
public class PlaylistServiceImplimentation implements PlaylistService
{

	@Autowired
	PlaylistRepository repo;
//	

	@Override
	public void addPlaylist(PlayList playlist) {
		repo.save(playlist);
		
	}

	@Override
	public List<PlayList> fetchPlaylist() {
		 List<PlayList> playlist=repo.findAll();
		return playlist;
	}

	
	
	

	
}
