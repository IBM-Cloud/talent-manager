package com.ibm.personafusion.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/people")
public class PeopleController 
{
	
	/** Returns people results as a JSON string. **/
	@GET
	public Response handleSearch()
	{
		String json = JsonUtils.getListPersonJson(SearchController.people);
		System.out.println(json);
		return Response.ok(json).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}
}