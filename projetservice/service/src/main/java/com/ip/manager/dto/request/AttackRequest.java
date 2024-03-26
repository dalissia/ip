package com.ip.manager.dto.request;

import java.time.LocalDateTime;

import com.ip.manager.utils.enums.SeverityEnum;

import lombok.Data;

// Lombok annotation to generate constructors, getters and setters. 
@Data
public class AttackRequest {
	
    private String label;
    private LocalDateTime date;
    private SeverityEnum severity;
    private AdresseIPRequest adresseIP; 
	
}
