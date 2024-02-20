package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.entity.Institute;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Integer> {
	

}
