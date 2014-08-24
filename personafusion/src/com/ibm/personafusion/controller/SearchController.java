package com.ibm.personafusion.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.ibm.personafusion.Engine;
import com.ibm.personafusion.infogen.PersonListGenerator;
import com.ibm.personafusion.model.Person;

/** The controller for the Search functionality.
 *  Handles GET requests to /search.
 *  @author Sean Welleck 
 **/
@Path("/search")
public class SearchController 
{
	public static List<Person> people = PersonListGenerator.generateDistinctPeople(10);
	
	/** Returns search results as a JSON string. **/
	@GET
	public String handleSearch(@Context UriInfo ui)
	{
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String fname = queryParams.get("fname").get(0);
		String lname = queryParams.get("lname").get(0);
		System.out.println("Num people: " + people.size());
		System.out.println("fname=" + fname + " lname=" + lname);
		
		Engine engine = new Engine(people);
		System.out.println("Engine created.");
		
		String fullName = fname + " " + lname;
		List<Person> results = engine.query(fullName);
		System.out.println("Num results: " + results.size());
		
		String json = JsonUtils.getListPersonJson(results);
		System.out.println(json);
		return json;
	}

}
