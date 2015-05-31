package com.ibm.personafusion.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.personafusion.Config;
import com.ibm.personafusion.Constants;
import com.ibm.personafusion.model.Person;

/**
 * Handles the POST /api/followup endpoint. Returns status 204 if a person was
 * successfully added.
 * 
 * @author Jeff Sloyer
 **/
@Path("/followup")
public class FollowUpController {
	/** Create a new Person. **/
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response handle(String payload) {
		Person p = personFromRequest(payload);
		System.out.println(p);

		String followup = JsonUtils.getFollowup(p);

		try {
			makeMobileDataRequest(followup);
		} catch (Exception e) {
			System.out.println(e);
			return Response.status(500).build();
		}

		// Config.cc.putPerson(p);
		// addToGlobalList(p);
		return Response.ok(p.toString()).build();
	}

	protected static Person personFromRequest(String json) {
		Person p = JsonUtils.getPersonFromJson(json);
		return p;
	}

	protected static void addToGlobalList(Person p) {
		if (p == null || p.group == null)
			return;
		if (p.group.equals(Constants.CURRENT_EMPLOYEES_GROUP)) {
			PeopleController.people.add(p);
		} else {
			SearchController.people.add(p);
		}
	}

	private static void makeMobileDataRequest(String rawData) throws Exception {
		String type = "application/json";
		URL u = new URL("https://mobile.ng.bluemix.net/data/rest/v1/apps/"
				+ Config.MOBILE_DATA_APP_ID + "/injections?classname=Item");
		
		System.out.println(rawData);
		byte[] bytes = rawData.getBytes("UTF-8");
		HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", type);
		conn.setRequestProperty("Content-Length", String.valueOf(bytes.length));
		conn.setRequestProperty("Ibm-Application-Secret", Config.MOBILE_DATA_APP_SECRET);
		OutputStream os = conn.getOutputStream();
		os.write(bytes);
		os.flush();
		os.close();
		
		int responseCode = conn.getResponseCode();
		System.out.println("Response code " + responseCode);
		System.out.println("\nSending 'POST' request");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

}