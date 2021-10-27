package com.softskills.demo.services;

import org.hibernate.exception.ConstraintViolationException;

import com.softskills.demo.entities.SoftSkills;



public interface SoftSkillsService {

	public void createSoftSkills(SoftSkills soft) throws ConstraintViolationException;

}
