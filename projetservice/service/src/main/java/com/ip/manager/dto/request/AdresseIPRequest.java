package com.ip.manager.dto.request;

import com.ip.manager.utils.enums.StatusEnum;

import lombok.Data;

//Lombok annotation to generate constructors, getters and setters. 
@Data
public class AdresseIPRequest {
	
    private String valueIPV4;
    private StatusEnum status; 

}
