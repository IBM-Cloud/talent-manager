package com.ibm.personafusion.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/** The controller for the homepage.
 *  Handles GET requests to /home.
 *  @author Sean Welleck 
 **/
@Path("/home")
public class HomeController 
{
	
	@GET
	public String handleHome()
	{
		String html = "<h1>Hello</h1>";
		return html;
	}

}
