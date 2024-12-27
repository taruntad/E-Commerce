package com.ecommerce.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.UserRegistrationDTO;
import com.ecommerce.models.Role;
import com.ecommerce.models.User;
import com.ecommerce.repositories.RoleRepository;
import com.ecommerce.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public User registerNewUser(UserRegistrationDTO userDTO) {
		Optional<User> userOptional = userRepository.findByUserName(userDTO.getUserName());
		if(userOptional.isPresent()) {
			throw new RuntimeException("User Already Exists.");
		}
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		
		Set<Role> roles = new HashSet<>();
		Role roleOptional = roleRepository.findByRoleName("USER")
				.orElseThrow(() -> 	 new RuntimeException("Role does not exit."));
		
		//Optional<Role> userRole = roleRepository.findByRoleName("USER");
		/*
		 * if(userRole.isEmpty()) { throw new RuntimeException("Role does not exit."); }
		 */
		
		//Role role = userRole.get();
		
		
		roles.add(roleOptional);
		user.setRoles(roles);
		
		return userRepository.save(user);
	}
	
}
