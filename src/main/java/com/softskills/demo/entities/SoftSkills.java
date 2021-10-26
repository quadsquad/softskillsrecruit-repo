package com.softskills.demo.entities;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="softskills")
public class SoftSkills {
	
	@Id
	private String id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

	private String soft_name;
	private int soft_note;
	private String soft_niveau;
	
	private String userauth;
	
	
	
	public String getUserauth() {
		return userauth;
	}
	public void setUserauth(String userauth) {
		this.userauth = userauth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSoft_name() {
		return soft_name;
	}
	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}
	public int getSoft_note() {
		return soft_note;
	}
	public void setSoft_note(int soft_note) {
		this.soft_note = soft_note;
	}
	public String getSoft_niveau() {
		return soft_niveau;
	}
	public void setSoft_niveau(String soft_niveau) {
		this.soft_niveau = soft_niveau;
	}
	
	public SoftSkills() {
	}
	

}
