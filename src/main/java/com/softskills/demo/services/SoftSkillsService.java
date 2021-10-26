package com.softskills.demo.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.core.Authentication;

import com.softskills.demo.entities.SoftSkills;



public interface SoftSkillsService {

	public void createSoftSkills(SoftSkills soft) throws ConstraintViolationException;

}
