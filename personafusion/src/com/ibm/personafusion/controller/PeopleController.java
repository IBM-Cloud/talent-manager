package com.ibm.personafusion.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.ibm.personafusion.Config;
import com.ibm.personafusion.Constants;
import com.ibm.personafusion.model.Person;

/** Handles the GET /api/people endpoint.
 *  Returns a JSON representation of all of the Person objects. 
 *  @author Sean Welleck **/
@Path("/people")
public class PeopleController 
{
	/** Return people who are current employees in the user's group. **/
	public static List<Person> people = 
			Config.cc.getAllPeopleInGroup(Constants.CURRENT_EMPLOYEES_GROUP);
	@GET
	public Response handle()
	{
		String json = JsonUtils.getListPersonJson(people);
		System.out.println(json);
		return Response.ok(json).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}
}