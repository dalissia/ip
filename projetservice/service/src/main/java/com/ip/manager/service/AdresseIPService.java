package com.ip.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ip.manager.dto.request.AdresseIPRequest;
import com.ip.manager.dto.response.AdresseIPResponse;
import com.ip.manager.utils.enums.StatusEnum;

public interface AdresseIPService {

	List<AdresseIPResponse> findAll();

	Page<AdresseIPResponse> findAllPageable(Pageable pageable);

	List<AdresseIPResponse> getAttacksByStatus(StatusEnum status);

	void save(AdresseIPRequest adresseIPRequest);

	void updateStatus(AdresseIPRequest request) throws Exception;

	void deleteAll();

	void deleteAdresseIPById(String id);

}
