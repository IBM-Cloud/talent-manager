package com.ibm.personafusion.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.ibm.personafusion.service.WatsonUserModeller;

@Path("/modeling")
public class ModelingResource {
	WatsonUserModeller WUM = new WatsonUserModeller();
	
	@GET
	public String getInformation() {
		String text = "This is a test of the service.";
		String profile = WUM.getProfileJSON(text);
		String vizHTML = WUM.getVizHTML(profile);
		if (vizHTML != null)
		{
			return vizHTML;
		}
		return "Error";
	}
}