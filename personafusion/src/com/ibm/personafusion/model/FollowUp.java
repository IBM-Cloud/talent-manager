package com.ibm.personafusion.model;

import com.ibm.personafusion.model.Person.Role;

public class FollowUp {
	
	private String name;
	private String image_url;
	private Role role;
	
	public FollowUp(String name, String image_url, Role role) {
		super();
		this.name = name;
		this.image_url = image_url;
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
