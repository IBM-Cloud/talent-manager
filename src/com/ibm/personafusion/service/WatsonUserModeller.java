package com.ibm.personafusion.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.personafusion.Config;
import com.ibm.personafusion.model.Person;
import com.ibm.personafusion.model.Trait;

/** A wrapper for accessing the Watson User Model API.
 *  Usage: 
 *  	WatsonUserModeller WUM = new WatsonUserModeller();
 *  	List<Trait> traits = WUM.getTraitsList("all of the twitter text");
 *    
 *  	String vizHTML     = WUM.getPersonVizHTML(personObject);
 *  @author Sean Welleck **/
public class WatsonUserModeller 
{
	private String username;
	private String password;
	private String base_url;
	private String profile_api;
	private String visual_api;
	
	private static JSONArray watson;
    private static JSONObject watsonInstance;
    private static JSONObject watsonCredentials;
	
	private Executor executor;
	
	public WatsonUserModeller()
	{
		try {
            String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
            JSONObject vcap;
            vcap = (JSONObject) JSONObject.parse(VCAP_SERVICES);
            
          watson = (JSONArray) vcap.get("personality_insights");
          watsonInstance = (JSONObject) watson.get(0);
          watsonCredentials = (JSONObject) watsonInstance.get("credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
		this.username = (String) watsonCredentials.get("username");
		this.password = (String) watsonCredentials.get("password");
		this.base_url = (String) watsonCredentials.get("url");
		this.profile_api = Config.WATSON_PROF_API;
		this.visual_api = Config.WATSON_VIZ_API;
		this.executor = Executor.newInstance().auth(username, password);
		if (this.executor == null) 
		{ 
			System.err.println("Authentication failed in WatsonUserModeller.");
		}
	}
	
	/** Get the list of Traits for this text using the data 
	 *  returned by Watson. **/
	public List<Trait> getTraitsList(String text)
	{
		List<Trait> traits = new ArrayList<Trait>();
		String json = getProfileJSON(text);
		List<Map<String,String>> lm = formatTraits(json);
		for (Map<String, String> m : lm)
		{
			String id = m.get("id");
			String val = m.get("value");
			if (id != null)
			{
				double pct = 0.0;
				/** NOTE: val == null means that this id is the name of a 
				 *  trait 'category'; e.g. values, personality. 
				 *  We do not add categories to the returned list of traits.
				 */
				if (val != null)
				{
					val = val.substring(0, val.length()-1);
					pct = Double.parseDouble(val);
					traits.add(new Trait(id, pct));
				}
			}
		}
		return traits;
	}
	
	/** Produce a visualization of a Person's traits based
	 *  on the text of their tweets. Uses the Watson API. **/
	public String getPersonVizHTML(Person p)
	{
		if (p == null) { return null; }
		String profileJSON = this.getProfileJSON(combine(p.qaResponses));
		String vizHTML = this.getVizHTML(profileJSON);
		return vizHTML;
	}
	
	/** Get a personality profile from Watson based on the input text.
	 *  Returns a string of JSON. **/
	private String getProfileJSON(String text)
	{
		return makePOST(base_url, profile_api, buildContent(text).toString());
	}
	
	/** Get a visualization from Watson based on the input personality profile,
	 *  e.g. from the output of getProfileJSON(). Returns a string of HTML. **/
	private String getVizHTML(String profileJSON)
	{
		String vizHTML = null;
		try
		{
			byte[] vizBytes = makePOSTByte(base_url, visual_api, profileJSON);
			vizHTML = new String(vizBytes, "utf-8");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return vizHTML;
	}
	
	private static List<Map<String,String>> formatTraits(String profileJson){
		List<Map<String,String>> arr = new ArrayList<Map<String,String>>();
		try
		{
			JSONObject tree = JSONObject.parse(profileJson);
			formatTree((JSONObject)tree.get("tree"), 0, arr);
		} catch(Exception e) { e.printStackTrace(); }
		return arr;
	}
	
	private static void formatTree(JSONObject node, int level, List<Map<String,String>> arr) {	
		if (node == null) return;
		JSONArray children = (JSONArray)node.get("children");
		if (level > 0 && (children == null || level != 2)) {
			Map<String,String> obj = new HashMap<String,String>();
			obj.put("id", (String)node.get("id"));
			if (children != null) obj.put("title", "true");
			if (node.containsKey("percentage")) {
				double p = (Double)node.get("percentage");
				p = Math.floor(p * 100.0);
				obj.put("value", Double.toString(p) + "%");
			}
			arr.add(obj);
		}
		if (children != null && !"sbh".equals(node.get("id"))) {
			for (int i = 0; i < children.size(); i++) {
				formatTree((JSONObject)children.get(i), level + 1, arr);
			}
		}
		
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
	
	private static String combine(List<String> lst)
	{
		String out = "";
		for (String s: lst)
		{
			out += s;
		}
		return out;
	}
	
}