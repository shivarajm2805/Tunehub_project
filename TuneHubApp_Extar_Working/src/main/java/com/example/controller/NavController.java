package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class NavController {
	
	@GetMapping("map-register")
	public String mapRegister() {
		return "register";
	}
	@GetMapping("map-login")
	public String mapLogin() {
		return "login";
	}
	@GetMapping("map-song")
	public String mapSong() {
		return "addsong";
	}
	
	@GetMapping("map-sample")
	public String samplePayment() {
		return "samplepayment";
	}
	

}
