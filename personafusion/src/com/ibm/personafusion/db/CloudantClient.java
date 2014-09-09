package com.ibm.personafusion.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.ibm.personafusion.Config;
import com.ibm.personafusion.Constants;
import com.ibm.personafusion.controller.JsonUtils;
import com.ibm.personafusion.model.Person;

/** A class for communicating with the Cloudant datastore. 
 *  See main() for example usage.
 *  
 *  @author Sean Welleck**/
public class CloudantClient 
{
	private HttpClient httpClient;
	private CouchDbConnector dbc;
	
	private int port;
	private String name;
	private String host;
	private String username;
	private String password;
	
	
	
	public CloudantClient()
	{
		this.httpClient = null;
		this.port = Config.CLOUDANT_PORT;
		this.host = Config.CLOUDANT_HOST;
		this.username = Config.CLOUDANT_USERNAME;
		this.password = Config.CLOUDANT_PASSWORD;
		this.name = Config.CLOUDANT_NAME;
		this.dbc = this.createDBConnector();
	}
	
	/** Put a Person into Cloudant using person.name as the unique id.
	 *  Stored as :
	 *  { 
	 *  	id: person.name, 
	 *  	type: Person.class, 
	 *  	group: person.group, 
	 *  	json: toJSON(person) 
	 *  }
	 */
	public void putPerson(Person p)
	{
		HashMap<String, Object> data = new HashMap<String, Object>();
		String name = p.name.toUpperCase();
		data.put(Constants.ID_KEY, name);
		data.put(Constants.TYPE_KEY, Person.class.getName());
		data.put(Constants.GROUP_KEY, p.group);
		System.out.println(data.get(Constants.TYPE_KEY));
		data.put(Constants.JSON_KEY, JsonUtils.getJson(p));
		this.putItem(data);
	}
	
	/** Get a Person from Cloudant using name as the unique id. **/
	public Person getPerson(String name)
	{
		name = name.toUpperCase();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> obj = this.dbc.get(HashMap.class, name);
		Person p = JsonUtils.getPersonFromJson((String)obj.get(Constants.JSON_KEY));
		return p;
	}
	
	/** Get all Person objects from Cloudant. **/
	public List<Person> getAllPeople()
	{
		List<Person> people = new ArrayList<Person>();
		List<String> docIds = dbc.getAllDocIds();
		for(String docId : docIds)
		{
			@SuppressWarnings("unchecked")
			HashMap<String, Object> obj = this.dbc.get(HashMap.class, docId);
			if (obj.get(Constants.TYPE_KEY) != null && 
				obj.get(Constants.TYPE_KEY).equals(Person.class.getName()))
			{
				String json = (String)obj.get(Constants.JSON_KEY);
				Person p = JsonUtils.getPersonFromJson(json);
				people.add(p);
			}
		}
		System.out.println(
			String.format("Retrieved %d Person entries.", people.size()));
		return people;
	}
	
	/** Get all Person objects in the specified group from Cloudant. **/
	public List<Person> getAllPeopleInGroup(String groupName)
	{
		List<Person> people = new ArrayList<Person>();
		List<String> docIds = dbc.getAllDocIds();
		for(String docId : docIds)
		{
			@SuppressWarnings("unchecked")
			HashMap<String, Object> obj = this.dbc.get(HashMap.class, docId);
			if (obj.get(Constants.TYPE_KEY) != null && 
				obj.get(Constants.TYPE_KEY).equals(Person.class.getName()) &&
				obj.get(Constants.GROUP_KEY) != null &&
				obj.get(Constants.GROUP_KEY).equals(groupName))
			{
				String json = (String)obj.get(Constants.JSON_KEY);
				Person p = JsonUtils.getPersonFromJson(json);
				people.add(p);
			}
		}
		System.out.println(String.format(
					"Retrieved %d Person entries for group %s.", 
					people.size(), groupName));
		return people;
	}
	
	/** Get all Person objects in the specified group from Cloudant. **/
	public List<Person> getAllPeopleNotInGroup(String groupName)
	{
		List<Person> people = new ArrayList<Person>();
		List<String> docIds = dbc.getAllDocIds();
		for(String docId : docIds)
		{
			@SuppressWarnings("unchecked")
			HashMap<String, Object> obj = this.dbc.get(HashMap.class, docId);
			if (obj.get(Constants.TYPE_KEY) != null && 
				obj.get(Constants.TYPE_KEY).equals(Person.class.getName()) &&
				obj.get(Constants.GROUP_KEY) != null &&
				!obj.get(Constants.GROUP_KEY).equals(groupName))
			{
				String json = (String)obj.get(Constants.JSON_KEY);
				Person p = JsonUtils.getPersonFromJson(json);
				people.add(p);
			}
		}
		System.out.println(String.format(
					"Retrieved %d Person entries that are not in group %s.", 
					people.size(), groupName));
		return people;
	}
	
	/** Delete all documents from the Cloudant datastore. Use with caution. **/
	public void deleteAll()
	{
		List<String> docIds = this.dbc.getAllDocIds();
		int startSize = docIds.size();
		for(String docId : docIds)
		{
			@SuppressWarnings("unchecked")
			HashMap<String, Object> obj = this.dbc.get(HashMap.class, docId);
			this.dbc.delete(obj);
		}
		docIds = this.dbc.getAllDocIds();
		int endSize = docIds.size();
		System.out.println(
				String.format(
					"Deleted all entries. Starting size: %d. Current size: %d.",
					startSize, endSize));
	}
	
	/** Put a generic item modeled as Key-Value pairs into Cloudant. **/
	private void putItem(HashMap<String, Object> data)
	{
		if (data == null) 
		{ 
			System.err.println("data cannot be null in putItem()"); 
			return;
		}
		String id = (String)data.get(Constants.ID_KEY);
		if (id == null)   
		{ 
			System.err.println("data must have an _id field."); 
			return;
		}
		if (this.dbc.contains(id)) 
		{ 
			System.err.println("Didn't putItem. _id=" + id + " already exists."); 
			return;
		}
		this.dbc.create(data);
		System.out.println("Put _id=" + id + " into the datastore."); 
	}
	
	private CouchDbConnector createDBConnector() 
	{
		CouchDbInstance dbInstance = null;
		
		System.out.println("Creating CouchDB instance...");
		this.httpClient = new StdHttpClient.Builder()
		.host(this.host)
		.port(this.port)
		.username(this.username)
		.password(this.password)
		.enableSSL(true)
		.relaxedSSLSettings(true)
		.build();

		dbInstance = new StdCouchDbInstance(this.httpClient);
		CouchDbConnector dbc = new StdCouchDbConnector(this.name, dbInstance);
		dbc.createDatabaseIfNotExists();
		return dbc;
	}
	
	private void closeDBConnector()
	{
		if (httpClient != null)
		{
			httpClient.shutdown();
		}
	}
	
	/** Example usage. **/
	public static void main(String[] args) throws Exception
	{
		CloudantClient cc = new CloudantClient();
		
		cc.deleteAll();
		
		Person p = new Person("Alan Xia", null, null);
		p.group = "group1";
		cc.putPerson(p);
		Person p2 = new Person("Prachi Snehal", null, null);
		p2.group = "group2";
		cc.putPerson(p2);
		
		Person alan = cc.getPerson("Alan Xia");
		System.out.println(alan.name);
		
		List<Person> people = cc.getAllPeople();
		System.out.println("There are " + people.size() + " people.");
		
		List<Person> g1ppl = cc.getAllPeopleInGroup("group1");
		System.out.println("There are " + g1ppl.size() + " people in group1.");
		
		List<Person> notg1ppl = cc.getAllPeopleNotInGroup("group1");
		System.out.println(notg1ppl.get(0).name + " is not in group1.");
		
		cc.closeDBConnector();
	}
}
