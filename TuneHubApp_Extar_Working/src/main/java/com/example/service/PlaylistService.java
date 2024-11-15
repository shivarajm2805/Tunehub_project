package com.example.service;

import java.util.List;

import com.example.entities.PlayList;
import com.example.entities.Song;

public interface PlaylistService 
{
	public void addPlaylist(PlayList playlist);

	public List<PlayList> fetchPlaylist();
	
	
	
}
