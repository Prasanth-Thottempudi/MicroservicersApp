package com.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class BaseEntity {
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;

}
