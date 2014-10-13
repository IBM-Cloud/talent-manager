package com.ibm.personafusion.controller;

import java.util.List;

import com.google.gson.Gson;
import com.ibm.personafusion.model.AddPersonRequest;
import com.ibm.personafusion.model.Person;

public class JsonUtils 
{
	public static String getJson(Person p)
	{
		Gson gson = new Gson();
		String json = gson.toJson(p);
		return json;
	}
	
	public static String getFollowup(Person p)
	{
		Gson gson = new Gson();
		String json = gson.toJson(p.getFollowUp());
		return json;
	}
	
	public static String getListPersonJson(List<Person> people)
	{
		Gson gson = new Gson();
		String json = gson.toJson(people);
		return json;
	}
	
	public static Person getPersonFromJson(String json)
	{
		Gson gson = new Gson();
		Person p = gson.fromJson(json, Person.class);
		return p;
	}
	
	public static AddPersonRequest getAPRFromJson(String json)
	{
		Gson gson = new Gson();
		AddPersonRequest p = gson.fromJson(json, AddPersonRequest.class);
		return p;
	}
	
}
