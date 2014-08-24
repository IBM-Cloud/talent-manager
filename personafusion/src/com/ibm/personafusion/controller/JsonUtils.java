package com.ibm.personafusion.controller;

import java.util.List;

import com.ibm.personafusion.model.Person;
import com.google.gson.Gson;

public class JsonUtils 
{
	public static String getJson(Person p)
	{
		Gson gson = new Gson();
		String json = gson.toJson(p);
		return json;
	}
	
	public static String getListPersonJson(List<Person> people)
	{
		Gson gson = new Gson();
		String json = gson.toJson(people);
		return json;
	}
	
}
