package com.ibm.personafusion.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ibm.personafusion.Config;
import com.ibm.personafusion.Constants;
import com.ibm.personafusion.Engine;
import com.ibm.personafusion.model.Person;

/** Handles the GET /api/search endpoint.
 *  The controller for the Search functionality.
 *  Requires 'fname' and 'lname' query parameters to search.
 *  @author Sean Welleck 
 **/
@Path("/search")
public class SearchController 
{
	/** Search for people who aren't current employees in the user's group. **/
	public static List<Person> people = Config.cc.getAllPeopleNotInGroup(Constants.CURRENT_EMPLOYEES_GROUP);
	
	/** Returns search results as a JSON string. **/
	@GET
	public Response handleSearch(@Context UriInfo ui)
	{
		System.out.println("Num people: " + people.size());
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String fname = getParam("fname", queryParams);
		String lname = getParam("lname", queryParams);
		
		if (fname == null || lname == null)
		{
			return error();
		}
		
		String fullName = fname + " " + lname;
		System.out.println("fname=" + fname + " lname=" + lname);
		
		Engine engine = new Engine(people);
		System.out.println("Engine created.");
		
		List<Person> results = engine.query(fullName);
		System.out.println("Num results: " + results.size());
		
		String json = JsonUtils.getListPersonJson(results);
		System.out.println(json);
		return Response.ok(json).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}
	
	/** Returns null if a parameter does not exist. **/
	public static String getParam(String key, MultivaluedMap<String, String> qp)
	{
		List<String> vals = qp.get(key);
		if (vals == null || vals.size() == 0) return null;
		return vals.get(0);
	}
	
	/** Retrieve a person by name from the global list. **/
	public static Person getPersonFromList(String fullname)
	{
		for (Person p: SearchController.people)
		{
			if (p != null && p.name != null && p.name.equals(fullname))
			{
				return p;
			}
		}
		return null;
	}
	
	private Response error()
	{
		return Response.serverError()
				.header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}

}
