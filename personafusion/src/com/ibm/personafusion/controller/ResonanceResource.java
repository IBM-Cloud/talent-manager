package com.ibm.personafusion.controller;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

@Path("/resonance")
public class ResonanceResource {

	@GET
	public String getInformation() {
		try
		{
			String word = "hello";
			int numWords = 1;
			String username = null;	// YOUR USERNAME HERE [for the Bluemix Service]
			String password = null;	// YOUR PASSWORD HERE [for the Bluemix Service]
			String baseUrl = "https://service-s.platform.watson.ibm.com/messageresonance/service/api/v1/ringscore";
			baseUrl += "?dataset=" + numWords;
			baseUrl += "&text=" + word;
    		
			Executor executor = Executor.newInstance()
    				.auth(username, password);
			URI resonanceUrl = new URI(baseUrl).normalize();
    		
    		String resJson = executor.execute(Request.Get(resonanceUrl)
    			    .setHeader("Accept", "application/json")
    			    ).returnContent().asString();
			
    		return resJson;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Error");
		return "Error";
	}
}