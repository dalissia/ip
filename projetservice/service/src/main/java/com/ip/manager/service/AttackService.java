package com.ip.manager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ip.manager.dto.request.AttackRequest;
import com.ip.manager.entity.AttackEntity;
import com.ip.manager.utils.enums.SeverityEnum;

public interface AttackService {
	
	String save(AttackRequest attackRequest);

	List<AttackRequest> findAll();

	List<AttackRequest> getAttacksBySeverity(SeverityEnum severity);

	List<AttackRequest> getAttacksByDateRange(LocalDateTime startDate, LocalDateTime endDate);

	Page<AttackRequest> findAllPageable(Pageable pageable);

	void deleteAll();
	
}
