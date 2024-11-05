package com.backend.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.RefreshToken;
import com.backend.entity.User;
import com.backend.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	private int EXPIRATION = 1000 * 60 * 60 * 24;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	public RefreshToken createToken(User u) {
		
		RefreshToken token = u.getRefreshToken();
		
		if(token != null) {
			refreshTokenRepository.delete(token);
		}
		
		token = RefreshToken.builder().token(UUID.randomUUID().toString()).user(u)
				.expiryDate(new Date(System.currentTimeMillis() + EXPIRATION)).build();

		return refreshTokenRepository.save(token);
	}

	public void delete(String token) {
		RefreshToken refreshToken = refreshTokenRepository.getByToken(token).orElse(null);

		if (refreshToken != null) {
			refreshTokenRepository.delete(refreshToken);
		}

	}
}
