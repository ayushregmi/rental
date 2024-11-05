package com.backend.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.service.FileService;
import com.backend.service.ProfileImageService;

@RestController
@RequestMapping("/api")
public class FileController {

	@Autowired
	private ProfileImageService profileImageService;
	
	@Autowired
	private FileService fileService;
	
	
	@PostMapping("/profile/save")
	public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile file) throws IOException{
		
		return ResponseEntity.ok(profileImageService.upload(file));
	}
	
	
	@GetMapping("/get/{file_name}")
	public ResponseEntity<?> getImage(@PathVariable("file_name") String fileName) throws IOException{
		
		Map<String, Object> file= fileService.getFile(fileName);
		
		if(file == null) {
			return new ResponseEntity<String>("Image not found", HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf((String) file.get("type"))).body(file.get("image"));
		
	}
	
}
