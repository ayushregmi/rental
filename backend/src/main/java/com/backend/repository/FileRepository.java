package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.FileStorage;

public interface FileRepository extends JpaRepository<FileStorage, Integer>{
	
	public Optional<FileStorage> findByFileName(String name);

	
}
