package com.ibm.personafusion.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.personafusion.Config;
import com.ibm.personafusion.Constants;
import com.ibm.personafusion.model.AddPersonRequest;
import com.ibm.personafusion.model.Person;

/** Handles the POST /api/add endpoint.
 *  Returns status 204 if a person was successfully added. 
 *  @author Sean Welleck **/
@Path("/add")
public class AddController 
{
	/** Create a new Person. **/
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response handle(String json)
	{
		System.out.println(json);
		Person p = personFromRequest(json);
		System.out.println(p);
		Config.cc.putPerson(p);
		addToGlobalList(p);
		return Response.ok(p.toString()).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	 }
	
	protected static Person personFromRequest(String json)
	{
		AddPersonRequest apr = JsonUtils.getAPRFromJson(json);
		Person p = AddPersonRequest.toPerson(apr);
		return p;
	}
	
	protected static void addToGlobalList(Person p)
	{
		if (p == null || p.group == null) return;
		if (p.group.equals(Constants.CURRENT_EMPLOYEES_GROUP))
		{
			PeopleController.people.add(p);
		}
		else
		{
			SearchController.people.add(p);
		}
	}
	
}