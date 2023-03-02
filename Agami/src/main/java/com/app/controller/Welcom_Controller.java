package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Welcom_Controller {
	
	@GetMapping(value = "/")
	public String welcomeP() {
		return "wleocme Aman";
	}	
	
	@GetMapping(value = "/welcome")
	public String welcome() {
		return "wleocm";
	}
}
