package com.softskills.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.softskills.demo.entities.SoftSkills;


@Repository
public interface SoftSkillsRepository extends MongoRepository<SoftSkills, String>{

}
