package com.backend.service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.dto.RegisterDto;
import com.backend.entity.ProfileImage;
import com.backend.entity.Role;
import com.backend.entity.User;
import com.backend.repository.ProfileImageRepository;
import com.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProfileImageRepository profileImageRepository;
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}
	
	public void createUser(RegisterDto register) throws FileNotFoundException {
		
		ProfileImage profileImage = profileImageRepository.getByFileName(register.getProfileName()).orElseThrow(()->new FileNotFoundException("Profile image not found"));
		
		User user = User.builder()
				.name(register.getName())
				.email(register.getEmail())
				.password(passwordEncoder.encode(register.getPassword()))
				.role(Role.USER)
				.profile(profileImage)
				.build();
		
		userRepository.save(user);
	}
	
	public User createUser(User u) {
		
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		u.setRole(Role.USER);

		return userRepository.save(u);
	}
	
}
