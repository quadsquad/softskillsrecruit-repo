package com.softskills.demo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.softskills.demo.entities.SoftSkills;
import com.softskills.demo.repositories.SoftSkillsRepository;
import com.softskills.demo.services.SoftSkillsService;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/softskills")
public class SoftSkillsController {

	@Autowired
	private SoftSkillsRepository softrepo;
	
	@Autowired
	private SoftSkillsService softservice;
	
	@Autowired
	private RestTemplate rest;
	
	 @Autowired
	    @LoadBalanced
	    private RestTemplate loadBalanced;
	
	 
	@GetMapping("/findallusers")
	public List<Object> getConnected(){
		Object[] objects =rest.getForObject("http://authrecruit/findallusers", Object[].class);
		return Arrays.asList(objects);
	}
	
	public static String getBearerTokenHeader() {
	    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
	  }
	
	@GetMapping(value = "/getuserconnect")
	public ResponseEntity<?> getConnectedUser() {
		
		//Object response = rest.getForObject("http://authrecruit/getuserconnected", Object.class);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getBearerTokenHeader()); 
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = rest.exchange("http://authrecruit/content", HttpMethod.GET, entity, String.class);


		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}

	@GetMapping("/findall")
	public ResponseEntity<?> getAllSoftSkills(){
		List<SoftSkills> softallskills =	softrepo.findAll();
		if(!softallskills.isEmpty()){
			return new ResponseEntity<>(softallskills, HttpStatus.OK);
			
			}
		else 
		{
				return new ResponseEntity<String>("No soft skills Available",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createSoft(@RequestBody SoftSkills soft){
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", getBearerTokenHeader()); 
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	        ResponseEntity<String> response = rest.exchange("http://authrecruit/content", HttpMethod.GET, entity, String.class);

			soft.setUserauth(response.getBody());
			softservice.createSoftSkills(soft);
			return new ResponseEntity<SoftSkills>(soft, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

		}
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody SoftSkills soft){
		Optional<SoftSkills> softskillsOptional = softrepo.findById(id);
		if (softskillsOptional.isPresent())
		{
			SoftSkills softToSave = softskillsOptional.get();
		
			softToSave.setSoft_name(soft.getSoft_name() != null? soft.getSoft_name(): softToSave.getSoft_name());
			softToSave.setSoft_note(soft.getSoft_note() != 0 ? soft.getSoft_note(): softToSave.getSoft_note());
			softToSave.setSoft_niveau(soft.getSoft_niveau() != null? soft.getSoft_niveau(): softToSave.getSoft_niveau());
			softrepo.save(softToSave);
			return new ResponseEntity<SoftSkills>(softskillsOptional.get(), HttpStatus.OK);

		}else{
			return new ResponseEntity<String>("Soft Skills not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSoftSkills(@PathVariable("id") String id){
		try{
			softrepo.deleteById(id);
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
}
