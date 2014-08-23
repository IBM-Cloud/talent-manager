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

@Path("/resonance")
public class ResonanceResource {

	@GET
	public String getInformation() {
		try
		{
			String word = "hello";
			String username = "03d2c98c-2e39-4638-904e-70836236164c";	// YOUR USERNAME HERE
			String password = "HZjf3lcza3Di";							// YOUR PASSWORD HERE
			String baseUrl = "https://service-s.platform.watson.ibm.com/messageresonance/service/api/v1/ringscore";
			
			JSONObject contentItem = new JSONObject();
	    	contentItem.put("userid", UUID.randomUUID().toString());
	    	contentItem.put("id", UUID.randomUUID().toString());
	    	contentItem.put("dataset", "1");
	    	contentItem.put("text", word);
	    	
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
    		
			URI resonanceUrl = new URI(baseUrl).normalize();
    		
    		String resJson = executor.execute(Request.Post(resonanceUrl)
    			    .setHeader("Accept", "application/json")
    			    .bodyString(content.toString(), ContentType.APPLICATION_JSON)
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