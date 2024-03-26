package com.ip.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ip.manager.dto.request.AdresseIPRequest;
import com.ip.manager.dto.response.AdresseIPResponse;
import com.ip.manager.service.AdresseIPService;
import com.ip.manager.utils.enums.StatusEnum;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AdresseIPController  {

    @Autowired
    private AdresseIPService adresseIPService;

    @GetMapping(value = "/adresses-ip/all")
    public ResponseEntity<List<AdresseIPResponse>> findAll() {
        List<AdresseIPResponse> liste =  this.adresseIPService.findAll();
        return new ResponseEntity<>(liste, HttpStatus.OK);
    }
    
    @GetMapping(value = "/adresses-ip")
    public ResponseEntity<Page<AdresseIPResponse>> findAllPageable (            
    		@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {    	
        Pageable pageable = PageRequest.of(page, size); 
        Page<AdresseIPResponse> liste =  this.adresseIPService.findAllPageable(pageable);
        return new ResponseEntity<>(liste, HttpStatus.OK);
    }
    
    @GetMapping("/adresses-ip/status")
    public ResponseEntity<List<AdresseIPResponse>> getAdresseIPByStatus(
            @RequestParam("status") StatusEnum status) {
        List<AdresseIPResponse> attacks = adresseIPService.getAttacksByStatus(status);
        return new ResponseEntity<>(attacks, HttpStatus.OK);
    }
       
    @PostMapping(value = "/adresses-ip")
    public ResponseEntity<String> save(@RequestBody AdresseIPRequest adresseIPRequest) {
        this.adresseIPService.save(adresseIPRequest);
        return new ResponseEntity<>("Adresse IP enregistrée avec succès", HttpStatus.CREATED);
    }
	
    @PutMapping("/adresses-ip")
    public ResponseEntity<Void> updateStatus(@RequestBody AdresseIPRequest request) {
            try {
				this.adresseIPService.updateStatus(request);
	            return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
    }
    
    @DeleteMapping(value = "/adresses-ip")
    public ResponseEntity<List<AdresseIPResponse>> deleteAll() {
        this.adresseIPService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/adresses-ip/{id}")
    public ResponseEntity<Void> deleteAdresseIPById(@PathVariable String id) {
		System.out.println(">>> " + id);
        this.adresseIPService.deleteAdresseIPById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
