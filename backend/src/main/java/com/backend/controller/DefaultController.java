package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/default")
public class DefaultController {

	@GetMapping("/hello")
	public ResponseEntity<?> hello(){
		return ResponseEntity.ok("hello");
	}
	
	@GetMapping("/auth/hello")
	public ResponseEntity<?> authHello(){
		return ResponseEntity.ok("auth hello");
	}
	
	@PostMapping("/file")
	public ResponseEntity<?> file(@RequestParam("name") String name, @RequestParam("hello") String nice){
		return ResponseEntity.ok(name + nice);
	}
	
}
