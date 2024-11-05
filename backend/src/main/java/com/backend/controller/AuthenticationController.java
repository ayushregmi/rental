package com.backend.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.dto.LoginRequest;
import com.backend.dto.LoginResponse;
import com.backend.entity.ProfileImage;
import com.backend.entity.User;
import com.backend.service.JwtService;
import com.backend.service.ProfileImageService;
import com.backend.service.RefreshTokenService;
import com.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	private ProfileImageService imageService;

//	@PostMapping("/register")
//	public ResponseEntity<?> register(@RequestBody RegisterDto register){
//		
//		User u = userService.getUserByEmail(register.getEmail());
//		
//		if(u != null) {
//			return new ResponseEntity<String>("Email Already Exists", HttpStatus.CONFLICT);
//		}
//		
//		try {
//			userService.createUser(register);
//		}catch(FileNotFoundException e) {
//			return new ResponseEntity<String>("Error uploading profile", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		
//		return ResponseEntity.ok("User Created");
//	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestParam("name") String name, @RequestParam("image") MultipartFile file,
			@RequestParam("password") String password, @RequestParam("email") String email) throws IOException {
		
		User u = userService.getUserByEmail(email);
		
		if(u != null) {
			return new ResponseEntity<String>("Email Already Exists", HttpStatus.CONFLICT);
		}
		
		ProfileImage profileImage = imageService.upload(file);
		
		u = User.builder()
				.name(name)
				.email(email)
				.password(password)
				.profile(profileImage)
				.build();
		
		u = userService.createUser(u);
		
		return ResponseEntity.ok(u);
		
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

		User u = userService.getUserByEmail(request.getEmail());

		if (u == null) {
			return new ResponseEntity<String>("User does not exist", HttpStatus.NOT_FOUND);
		}

		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		Map<String, Object> additionalClaims = new HashMap<String, Object>();
		additionalClaims.put("Name", u.getName());
		additionalClaims.put("Role", u.getRole().name());

		String jwtToken = jwtService.generateToken(additionalClaims, u);
		String refreshToken = refreshTokenService.createToken(u).getToken();

		LoginResponse response = LoginResponse.builder().authToken(jwtToken).refreshToken(refreshToken).user(u).build();

		return ResponseEntity.ok(response);
	}

}
