package com.ibm.personafusion.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/** The controller for the Search functionality.
 *  Handles POST requests to /search.
 *  @author Sean Welleck 
 **/
@Path("/search")
public class SearchController 
{
	/** Returns search results as a JSON string. **/
	@POST
	public String handleSearch()
	{
		String resultJSON = "";
		return resultJSON;
	}

}
