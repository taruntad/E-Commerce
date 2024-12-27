package com.ecommerce.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecommerce.models.User;
import com.ecommerce.repositories.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService{
	
	private UserRepository userRepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		   return new org.springframework.security.core.userdetails.User(
		            user.getUserName(),
		            user.getPassword(),
		            user.getRoles()
		                .stream()
		                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
		                .collect(Collectors.toList())
	        );
	 
	}

}
