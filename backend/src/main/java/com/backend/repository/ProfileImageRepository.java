package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.ProfileImage;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer>{
	public Optional<ProfileImage> getByFileName(String name);
}
