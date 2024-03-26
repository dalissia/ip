package com.ip.manager.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ip.manager.dto.request.AttackRequest;
import com.ip.manager.entity.AttackEntity;

@Mapper(componentModel = "spring")
public interface AttackMapper {
	
	AttackEntity dtoToEntity(AttackRequest attackRequest); 

	AttackRequest entityToDto(AttackEntity attackEntity); 

	List<AttackEntity> listDtoToListEntity(List<AttackRequest> listAttacksRequest); 

	List<AttackRequest> listEtityToListDto(List<AttackEntity> listAttacksEntity); 
}
