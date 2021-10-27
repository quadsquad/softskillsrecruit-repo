package com.softskills.demo.services;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.softskills.demo.entities.SoftSkills;
import com.softskills.demo.repositories.SoftSkillsRepository;


@Service
public class SoftSkillsServiceImpl implements SoftSkillsService{

	@Autowired
	private SoftSkillsRepository softrepo;
	


	public void createSoftSkills(SoftSkills soft) throws ConstraintViolationException {
		Optional<SoftSkills> softOptional = softrepo.findById(soft.getSoft_name());
		//String connected =  SecurityContextHolder.getContext().getAuthentication().getName();
		if (softOptional.isPresent()){
			System.err.println("IS PRESENT");			
		}else{
		  //  soft.setUserauth((String) getConnected());
			softrepo.save(soft);
		}
		
	}

}
