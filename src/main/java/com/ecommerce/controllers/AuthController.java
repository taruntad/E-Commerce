package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.UserRegistrationDTO;
import com.ecommerce.models.User;
import com.ecommerce.services.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/csrf")
	public CsrfToken getCsrfToken(HttpServletRequest http) {
		return (CsrfToken) http.getAttribute("_csrf");
	}
	
	@GetMapping("/demo")
	public String demo(HttpServletRequest http) {
		System.out.println("This is hello method");
		System.out.println("This is a test");
		return "This is a success message " + http.getSession().getId();
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO userDTO) {
		User user =userService.registerNewUser(userDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
}
