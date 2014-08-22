package com.ibm.cloudoe.samples;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

@Path("/modeling")
public class ModelingResource {

	@GET
	public String getInformation() {
		try
		{
			String text = "\"Alan is really awesome, and is the master of security\" - Ron Martin";
			String username = "0cebddee-31b3-49a4-bf18-20e792fb6dea";
			String password = "Mwn6NRgSReqI";
			String baseUrl = "https://service-s.platform.watson.ibm.com/systemu/service/";
			
			
			JSONObject contentItem = new JSONObject();
	    	contentItem.put("userid", UUID.randomUUID().toString());
	    	contentItem.put("id", UUID.randomUUID().toString());
	    	contentItem.put("sourceid", "freetext");
	    	contentItem.put("contenttype", "text/plain");
	    	contentItem.put("language", "en");
	    	contentItem.put("content", text);
	    	
	    	JSONObject content = new JSONObject();
	    	JSONArray contentItems = new JSONArray();
	    	content.put("contentItems", contentItems);
	    	contentItems.add(contentItem);
			
			// 'VCAP_APPLICATION' is in JSON format, it contains useful information about a deployed application
			String envApp = System.getenv("VCAP_APPLICATION");
	
			// 'VCAP_SERVICES' contains all the credentials of services bound to this application.
			String envServices = System.getenv("VCAP_SERVICES");
			JSONObject sysEnv = JSONObject.parse(envServices);
    		
			Executor executor = Executor.newInstance()
    				.auth(username, password);
    		// Compose urls like this to avoid issues with trailing slashes on
    		// the baseUrl and leading slashes on the api/v2 path creating
    		// double slashes in the url.  normalize takes care of that.
    		
			URI profileUrl = new URI(baseUrl + "/api/v2/profile").normalize();
    		URI visualizeUrl = new URI(baseUrl + "/api/v2/visualize").normalize();
    		
    		String profileJson = executor.execute(Request.Post(profileUrl)
    			    .setHeader("Accept", "application/json")
    			    .bodyString(content.toString(), ContentType.APPLICATION_JSON)
    			    ).returnContent().asString();
			
			byte[] vizHtmlData = executor.execute(Request.Post(visualizeUrl)
				.setHeader("Accept", "text/html")
			    .bodyString(profileJson, ContentType.APPLICATION_JSON)
			    ).returnContent().asBytes();
			String vizHtml = new String(vizHtmlData, "utf-8");
			
    		return vizHtml;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Error");
		return "Error";
	}
}