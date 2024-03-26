package com.ip.manager.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ip.manager.dto.request.AttackRequest;
import com.ip.manager.entity.AdresseIPEntity;
import com.ip.manager.entity.AttackEntity;
import com.ip.manager.mapper.AdresseIPMapper;
import com.ip.manager.mapper.AttackMapper;
import com.ip.manager.repository.AdresseIPRepository;
import com.ip.manager.repository.AttackRepository;
import com.ip.manager.service.AttackService;
import com.ip.manager.utils.enums.SeverityEnum;
import com.ip.manager.utils.enums.StatusEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttackServiceImpl implements AttackService {

    @Autowired
    private AdresseIPRepository adresseIPRepository;
    
    @Autowired
    private AttackRepository attackRepository;
    
    @Autowired
    private AdresseIPMapper adresseIPMapper;
    
    @Autowired
    private AttackMapper attackMapper;
    
	@Override
	public List<AttackRequest> findAll() {
		List<AttackEntity> listeE = this.attackRepository.findAll();
		return this.attackMapper.listEtityToListDto(listeE);
	}
	
	@Override
    public List<AttackRequest> getAttacksBySeverity(SeverityEnum severity) {
        List<AttackEntity> listeE = attackRepository.findBySeverity(severity);
		return this.attackMapper.listEtityToListDto(listeE);

    }
    
	@Override
	public List<AttackRequest> getAttacksByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		List<AttackEntity> listeE = attackRepository.findByDateBetween(startDate, endDate);
		return this.attackMapper.listEtityToListDto(listeE);
	}
	
	@Override
	public String save(AttackRequest attackRequest) {  
        AttackEntity attackEntity = this.attackMapper.dtoToEntity(attackRequest);
        String ipV4 = attackRequest.getAdresseIP().getValueIPV4(); 
        Optional<AdresseIPEntity> adresseIPEntityInDBOptional = this.adresseIPRepository.findById(ipV4);
        if (adresseIPEntityInDBOptional.isPresent()) {
        	log.warn("L'adresse IP {} est déja répertoriée dans le système.", ipV4);
        	AdresseIPEntity adresseIPEntityInDB = adresseIPEntityInDBOptional.get();
        	Integer nbAttacks = adresseIPEntityInDB.getNbAttacks(); 
        	if (nbAttacks != null) {
        	    int incrementedValue = nbAttacks.intValue() + 1; 
        	    nbAttacks = Integer.valueOf(incrementedValue);
        	} else {
        		nbAttacks = 1; 
        	}
        	adresseIPEntityInDB.setNbAttacks(nbAttacks);
        	adresseIPEntityInDB.setStatus(this.determinateStatus(adresseIPEntityInDB.getNbAttacks()));
            this.adresseIPRepository.save(adresseIPEntityInDB);
            attackEntity.setAdresseIP(adresseIPEntityInDB);
            this.attackRepository.save(attackEntity);   
		} else {
        	log.warn("L'adresse IP {} est identifiée pour la première fois, lancement de la sauvgarde dans le système.", ipV4);
	        AdresseIPEntity adresseIPEntityToSave = this.adresseIPMapper.requestToEntity(attackRequest.getAdresseIP());
	        adresseIPEntityToSave.setNbAttacks(1);
	        adresseIPEntityToSave.setStatus(this.determinateStatus(1));
	        this.adresseIPRepository.save(adresseIPEntityToSave);
	        attackEntity.setAdresseIP(adresseIPEntityToSave); 
	        this.attackRepository.save(attackEntity);  
		}    
        return "";        
	}

	private StatusEnum determinateStatus(Integer nbAttacks) {
		if (Integer.valueOf(1).equals(nbAttacks)) {
			return StatusEnum.INVESTIGATION; 
		} else if (Integer.valueOf(1) < nbAttacks && nbAttacks < Integer.valueOf(10)) {
			return StatusEnum.WARNING; 
		} 
		return StatusEnum.BLOCKED;
	}

	@Override
	public Page<AttackRequest> findAllPageable(Pageable pageable) {
		Page<AttackEntity> pageAttackEntities = attackRepository.findAll(pageable);
		 return pageAttackEntities.map(this.attackMapper::entityToDto);
	}

	@Override
	public void deleteAll() {
		this.attackRepository.deleteAll();	
	}

}
