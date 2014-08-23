package com.ibm.personafusion.service;

import java.net.URI;
import java.util.UUID;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/** A wrapper for accessing the Watson User Model API.
 *  @author Sean Welleck **/
public class WatsonUserModeller 
{
	private static final String USERNAME = "0cebddee-31b3-49a4-bf18-20e792fb6dea";
	private static final String PASSWORD = "Mwn6NRgSReqI";
	private static final String BASE_URL = "https://service-s.platform.watson.ibm.com/systemu/service/";
	private static final String PROFILE_API = "/api/v2/profile";
	private static final String VISUAL_API = "/api/v2/visualize";
	
	private Executor executor;
	
	public WatsonUserModeller()
	{
		this.executor = Executor.newInstance().auth(USERNAME, PASSWORD);
		if (this.executor == null) 
		{ 
			System.err.println("Authentication failed in WatsonUserModeller.");
		}
	}
	
	
	public String getProfileJSON(String text)
	{
    	return makePOST(BASE_URL, PROFILE_API, buildContent(text).toString());
	}
	
	public String getVizHTML(String profileJSON)
	{
		String vizHTML = null;
		try
		{
			byte[] vizBytes = makePOSTByte(BASE_URL, VISUAL_API, profileJSON);
			vizHTML = new String(vizBytes, "utf-8");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return vizHTML;
	}
	
	private String makePOST(String base, String suffix, String content)
	{
		try
		{
			URI uri = new URI(base + suffix).normalize();
			String profileJSON = executor.execute(Request.Post(uri)
    			    .setHeader("Accept", "application/json")
    			    .bodyString(content, ContentType.APPLICATION_JSON)
    			    ).returnContent().asString();
			System.out.println(profileJSON);
    		return profileJSON;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "An error occured on POST to " + base + suffix;
	}
	
	private byte[] makePOSTByte(String base, String suffix, String content)
	{
		byte[] vizHTMLData = null;
		try
		{
			URI uri = new URI(base + suffix).normalize();
			vizHTMLData = executor.execute(Request.Post(uri)
    			    .setHeader("Accept", "text/html")
    			    .bodyString(content, ContentType.APPLICATION_JSON)
    			    ).returnContent().asBytes();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("An error occured on POST to " + base + suffix);
		}
		return vizHTMLData;
	}
	
	private JSONObject buildContent(String text)
	{
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
    	return content;
	}
	
}
