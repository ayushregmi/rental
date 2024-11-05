package com.backend.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tokenId;
	private String token;
	private Date expiryDate;
	
	@OneToOne()
	private User user;
	
}
