package com.springsecurityjpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	private String homePage()
	{
		return "<h1>Welcome, This is Home page !!</h1>";
	}

	@GetMapping("/admin")
	private String adminePage()
	{
		return "<h1>Welcome, This is Admin page !!</h1>";
	}
	
	@GetMapping("/user")
	private String userPage()
	{
		return "<h1>Welcome, This is User page !!</h1>";
	}
}
