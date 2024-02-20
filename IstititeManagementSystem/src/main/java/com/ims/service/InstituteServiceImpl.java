package com.ims.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ims.entity.Institute;
import com.ims.exception.InstituteManagementSystemException;
import com.ims.repository.InstituteRepository;

public class InstituteServiceImpl implements InstituteService{
	
	  private InstituteRepository instituteRepository;
	 
	 
	 @Autowired
	 public InstituteServiceImpl(InstituteRepository instituteRepository) {
		super();
		this.instituteRepository = instituteRepository;
	}
	 
	 @Override
	 public Institute registerInstitute(Institute institute) {
	        // Additional validation logic if needed
	        return instituteRepository.save(institute);
	    }

	@Override
	public Institute updateInstitute(int id, Institute updatedInstitute) {
        Institute institute = instituteRepository.findById(id)
            .orElseThrow(() -> new InstituteManagementSystemException("Institute not found with id: " + id));
        
        // Update fields
        institute.setName(updatedInstitute.getName());
        institute.setLocation(updatedInstitute.getLocation());
        institute.setContactInformation(updatedInstitute.getContactInformation());
        // Update other fields
        
        return instituteRepository.save(institute);
    }

	@Override
	public Institute getInstituteById(int id) {
        return instituteRepository.findById(id)
            .orElseThrow(() -> new InstituteManagementSystemException("Institute not found with id: " + id));
    }

}
