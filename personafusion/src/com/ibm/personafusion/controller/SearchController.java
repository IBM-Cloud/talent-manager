package com.ibm.personafusion.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

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
	public String handleSearch(@QueryParam("fname") String fname, @QueryParam("lname") String lname)
	{
		System.out.println("Num people: " + people.size());
		// instantiate new Engine
		Engine engine = new Engine(people);
		List<Person> results = engine.query(fname + " " + lname);
		String str = "";
		for (Person p: results)
		{
			str += p.name;
		}
		return str;
	}

}
