package com.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.entity.ProfileImage;
import com.backend.repository.FileRepository;
import com.backend.repository.ProfileImageRepository;

@Service
public class ProfileImageService {

	private String SAVE_PATH = "/home/rolexx/Desktop/car rental/backend/uploads/profile/";

	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private ProfileImageRepository profileImageRepository;
	
	
	public ProfileImage upload(MultipartFile file) throws IOException {
		String filePath = SAVE_PATH + file.getOriginalFilename();

		ProfileImage image = new ProfileImage();
		image.setFileName(UUID.randomUUID().toString());
		image.setFileType(file.getContentType());
		image.setFilePath(filePath);
		
		file.transferTo(new File(filePath));
		
		return fileRepository.save(image);
	}
	
	public Optional<ProfileImage> getImageByName(String name) {
		return profileImageRepository.getByFileName(name);
	}
	
}
