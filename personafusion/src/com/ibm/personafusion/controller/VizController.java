package com.ibm.personafusion.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ibm.personafusion.model.Person;
import com.ibm.personafusion.service.WatsonUserModeller;

/** Handles the /api/viz API endpoint.
 *  Visualizes a person's personality traits.
 *  Requires 'fname' and 'lname' query parameters to search.
 *  Returns an HTML string of the visualization.
 *  @author Sean Welleck **/
@Path("/viz")
public class VizController 
{
	
	/** Returns people results as a JSON string. **/
	@GET
	public Response handleViz(@Context UriInfo ui)
	{
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String fname = SearchController.getParam("fname", queryParams);
		String lname = SearchController.getParam("lname", queryParams);
		
		if (fname == null || lname == null)
		{
			return Response.serverError().build();
		}
		
		String fullName = fname + " " + lname;
		System.out.println("fname=" + fname + " lname=" + lname);
		
		Person p = SearchController.getPersonFromList(fullName.toUpperCase());
		if (p == null)
		{
			return Response.serverError().build();
		}
		
		WatsonUserModeller WUM = new WatsonUserModeller();
		String vizHTML = WUM.getPersonVizHTML(p);
		
		if (vizHTML == null)
		{
			return Response.serverError().build();
		}
		
		return Response.ok(vizHTML).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}
	
}