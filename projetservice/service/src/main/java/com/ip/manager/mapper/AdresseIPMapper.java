package com.ip.manager.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ip.manager.dto.request.AdresseIPRequest;
import com.ip.manager.dto.response.AdresseIPResponse;
import com.ip.manager.entity.AdresseIPEntity;

@Mapper(componentModel = "spring")
public interface AdresseIPMapper {
	
	AdresseIPEntity requestToEntity(AdresseIPRequest adresseIPRequest); 

	AdresseIPRequest entityToRequest(AdresseIPEntity adresseIPEntity); 

	AdresseIPEntity responseToEntity(AdresseIPResponse adresseIPResponse); 

	AdresseIPResponse entityToResponse(AdresseIPEntity adresseIPEntity); 
	
	List<AdresseIPResponse> listEntityToListResponse(List<AdresseIPEntity> listAdresseIPEntity); 
	
}
