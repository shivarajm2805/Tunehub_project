package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.PlayList;
import com.example.entities.Song;
import com.example.service.PlaylistService;
import com.example.service.SongService;

@Controller
public class PlaylistController 
{
	@Autowired
	PlaylistService playserv;
	@Autowired
	SongService sserv;
	
	@GetMapping("map-createplaylist")
	public String createPlaylist(Model model) 
	{
		List<Song> songslist=sserv.getSong();//fetching the song from database using getSong() method
		model.addAttribute("songslist", songslist);//putting the all fetched songs into model interface
//		return "viewsong";
		return "createplaylistAdmin";// redirecting the control to createplaylist.html for creating the playlist
	}
	@PostMapping("addplaylist")
	public String addPlaylist(@ModelAttribute PlayList playlist) 
	{
		//adding playlist
		playserv.addPlaylist(playlist);
		
		//update song table
		
		List<Song> songlist=playlist.getSong();// here we getting songs list from playlist object using getSong()(it returns songs list) present in the PlayList Entity class
		for(Song song:songlist) // here we traversing through each song
		{
			//here initially we getting the playlist from song entity or song database but it is null hence we adding the newly created playlist to song entity or database
			song.getPlaylist().add(playlist);
			// here we saving or creating the newly added  into song database
			sserv.updateSong(song);
		}
		
		return "playlistsuccess";
	}
	@GetMapping("map-viewplaylistAdmin")
	public String viewPlaylist(Model model) {
		//fetching the playlist from database using fetchPlaylist() method present in PlaylistSeviceImplamentation class
		List<PlayList> playlist=playserv.fetchPlaylist();
		// putting the fetched playlist into Model Interface for accessing to html
//		System.out.println(playlist);
		model.addAttribute("playlist", playlist);
		return "viewplaylistAdmin";
	}
}
