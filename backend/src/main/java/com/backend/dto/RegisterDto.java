package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

	private String name;
	private String email;
	private String password;
	private String imageName;
	private String profileName;
	
}
