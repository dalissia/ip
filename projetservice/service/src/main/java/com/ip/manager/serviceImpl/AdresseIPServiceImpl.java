package com.ip.manager.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ip.manager.dto.request.AdresseIPRequest;
import com.ip.manager.dto.response.AdresseIPResponse;
import com.ip.manager.entity.AdresseIPEntity;
import com.ip.manager.mapper.AdresseIPMapper;
import com.ip.manager.repository.AdresseIPRepository;
import com.ip.manager.service.AdresseIPService;
import com.ip.manager.utils.enums.StatusEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdresseIPServiceImpl implements AdresseIPService {

    @Autowired
    private AdresseIPRepository adresseIPRepository;
        
    @Autowired
    private AdresseIPMapper adresseIPMapper;

	@Override
	public List<AdresseIPResponse> findAll() {
		List<AdresseIPEntity> listAdresseIPEntity = this.adresseIPRepository.findAll(); 
		return this.adresseIPMapper.listEntityToListResponse(listAdresseIPEntity); 
	}

	@Override
	public Page<AdresseIPResponse> findAllPageable(Pageable pageable) {
		Page<AdresseIPEntity> pageAdresseIPEntity = this.adresseIPRepository.findAll(pageable); 
		 return pageAdresseIPEntity.map(adresseIPMapper::entityToResponse);
	}

	@Override
	public List<AdresseIPResponse> getAttacksByStatus(StatusEnum status) {
		List<AdresseIPEntity> listeAdresseIPEntity = this.adresseIPRepository.findByStatus(status);
		return this.adresseIPMapper.listEntityToListResponse(listeAdresseIPEntity);
	}

	@Override
	public void save(AdresseIPRequest adresseIPRequest) {
		Optional<AdresseIPEntity> adresseIPEntityOptional = this.adresseIPRepository.findById(adresseIPRequest.getValueIPV4());
		if (!adresseIPEntityOptional.isPresent()) {
			log.info("Insertion de l'adresse IP : {}.", adresseIPRequest.getValueIPV4());
			AdresseIPEntity adresseIPEntity = this.adresseIPMapper.requestToEntity(adresseIPRequest);
			adresseIPEntity.setNbAttacks(Integer.valueOf(1));
			adresseIPEntity.setStatus(StatusEnum.INVESTIGATION);
			this.adresseIPRepository.save(adresseIPEntity);		
		} else {
			log.warn("L'adresse IP {} est déja sauvgardée, aucune insertion", adresseIPRequest.getValueIPV4());
		}
	}

	@Override
	public void updateStatus(AdresseIPRequest adresseIPRequest) throws Exception {
		if (adresseIPRequest.getValueIPV4() != null) {
			Optional<AdresseIPEntity> adresseIPEntityOptional = this.adresseIPRepository.findById(adresseIPRequest.getValueIPV4()); 
			if (adresseIPEntityOptional.isPresent()) {
				AdresseIPEntity adresseIPEntity = adresseIPEntityOptional.get();
				adresseIPEntity.setStatus(adresseIPRequest.getStatus());
				this.adresseIPRepository.save(adresseIPEntity); 
			} else {
				log.warn("L'adresse IP {} est introuvanle en BD", adresseIPRequest.getValueIPV4());
			}	
		} else {
			log.error("Aucune adresse IP en entrée");
			throw new Exception("Invalid body."); 
		}
	}

	@Override
	public void deleteAll() {
		this.adresseIPRepository.deleteAll();
	}

	@Override
	public void deleteAdresseIPById(String id) {
		System.out.println("> " + id);
		this.adresseIPRepository.deleteById(id);
	}	

}
