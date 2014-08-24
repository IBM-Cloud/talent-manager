package com.ibm.personafusion.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/** Handles the GET /api/people endpoint.
 *  Returns a JSON representation of all of the Person objects. 
 *  @author Sean Welleck **/
@Path("/people")
public class PeopleController 
{
	
	@GET
	public Response handle()
	{
		String json = JsonUtils.getListPersonJson(SearchController.people);
		System.out.println(json);
		return Response.ok(json).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}
}