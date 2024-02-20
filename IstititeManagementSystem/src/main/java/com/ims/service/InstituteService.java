package com.ims.service;

import com.ims.entity.Institute;

public interface InstituteService {
	
	public Institute registerInstitute(Institute institute);
	
	public Institute updateInstitute(int id, Institute updatedInstitute);
	
	public Institute getInstituteById(int id);
	

}
