package com.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.entity.Institute;
import com.ims.service.InstituteService;

@RestController
@RequestMapping("/institutes")
public class InstituteController {

    private InstituteService instituteService;
    
    
    @Autowired
    public InstituteController(InstituteService instituteService) {
		super();
		this.instituteService = instituteService;
	}

	@PostMapping
    public ResponseEntity<Institute> registerInstitute(@RequestBody Institute institute) {
        Institute registeredInstitute = instituteService.registerInstitute(institute);
        return new ResponseEntity<>(registeredInstitute, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Institute> updateInstitute(@PathVariable int id, @RequestBody Institute updatedInstitute) {
        Institute institute = instituteService.updateInstitute(id, updatedInstitute);
        return new ResponseEntity<>(institute, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Institute> getInstituteById(@PathVariable int id) {
        Institute institute = instituteService.getInstituteById(id);
        return new ResponseEntity<>(institute, HttpStatus.OK);
    }
}

