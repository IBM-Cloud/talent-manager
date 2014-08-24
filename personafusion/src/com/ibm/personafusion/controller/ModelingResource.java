package com.ibm.personafusion.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.ibm.personafusion.service.WatsonUserModeller;

@Path("/modeling")
public class ModelingResource {
	WatsonUserModeller WUM = new WatsonUserModeller();
	
	@GET
	public String getInformation() {
		return "Error";
	}
}