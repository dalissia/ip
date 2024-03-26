package com.ip.manager.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ip.manager.dto.request.AttackRequest;
import com.ip.manager.utils.enums.StatusEnum;

import lombok.Data;

//Lombok annotation to generate constructors, getters and setters. 
@Data
public class AdresseIPResponse {
	
    private String valueIPV4;
    private StatusEnum status; 
    private Integer nbAttacks; 
    @JsonIgnore
    List<AttackRequest> listeAttacks; 

}
