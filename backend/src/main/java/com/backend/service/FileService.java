package com.backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.entity.FileStorage;
import com.backend.repository.FileRepository;

@Service
public class FileService {

	private String SAVE_PATH = "/home/rolexx/Desktop/car rental/backend/uploads/";

	@Autowired
	private FileRepository fileRepository;

	public FileStorage uploadFile(MultipartFile file) throws IOException {
		String filePath = SAVE_PATH + file.getOriginalFilename();

		FileStorage fileStorage = FileStorage.builder().fileName(UUID.randomUUID().toString())
				.fileType(file.getContentType()).filePath(filePath).build();

		file.transferTo(new File(filePath));

		return fileRepository.save(fileStorage);

	}
	
	public Map<String, Object> getFile(String fileName) throws IOException{
		FileStorage imageFile = fileRepository.findByFileName(fileName).orElse(null);
		
		if(imageFile == null) {
			return null;
		}
		
		String filePath = imageFile.getFilePath();
		String type = imageFile.getFileType();

		byte[] image = Files.readAllBytes(new File(filePath).toPath());
		
		Map<String, Object> file = new HashMap<String, Object>();
		file.put("type", type);
		file.put("image", image);
		
		return file;		
	}

}
