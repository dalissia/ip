package com.ip.manager.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ip.manager.dto.request.AttackRequest;
import com.ip.manager.dto.response.AdresseIPResponse;
import com.ip.manager.entity.AttackEntity;
import com.ip.manager.service.AttackService;
import com.ip.manager.utils.enums.SeverityEnum;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AttackController  {

    @Autowired
    private AttackService attackService;

    @GetMapping(value = "/attacks/all")
    public ResponseEntity<List<AttackRequest>> findAll() {
        List<AttackRequest> liste =  this.attackService.findAll();
        return new ResponseEntity<>(liste, HttpStatus.OK);
    }
    
    @GetMapping(value = "/attacks")
    public ResponseEntity<Page<AttackRequest>> findAllPageable (            
    		@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {    	
        Pageable pageable = PageRequest.of(page, size); 
        Page<AttackRequest> liste =  this.attackService.findAllPageable(pageable);
        return new ResponseEntity<>(liste, HttpStatus.OK);
    }
    
    @GetMapping("/attacks/severity/all")
    public ResponseEntity<List<AttackRequest>> getAttacksBySeverity(
            @RequestParam("severity") SeverityEnum severity) {
        List<AttackRequest> attacks = attackService.getAttacksBySeverity(severity);
        return new ResponseEntity<>(attacks, HttpStatus.OK);
    }
    
    @GetMapping("/attacks/date")
    public ResponseEntity<List<AttackRequest>> getAttacksByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<AttackRequest> attacks = attackService.getAttacksByDateRange(startDate, endDate);
        return new ResponseEntity<>(attacks, HttpStatus.OK);
    }
    
    @PostMapping(value = "/attacks")
    public ResponseEntity<String> save(@RequestBody AttackRequest attackRequest) {
        this.attackService.save(attackRequest);
        return new ResponseEntity<>("Attack enregistrée avec succès", HttpStatus.CREATED);
    }
	
    @DeleteMapping(value = "/attacks")
    public ResponseEntity<List<AdresseIPResponse>> deleteAll() {
        this.attackService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
