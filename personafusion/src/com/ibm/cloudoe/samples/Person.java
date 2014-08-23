package com.ibm.cloudoe.samples;

import java.util.List;

public class Person implements Comparable<Person>
{
	String name;
	List<Trait> traits;
	enum Role {DEV, Manager};
	Role role;
	
	Person(String name, List<Trait> traits, Role role)
	{
		this.name = name;
		this.traits = traits;
		this.role = role;
	}
	
	public String toString()
	{
		String pString = "";
		pString = traits.toString();
		return pString;
	}
	
	public int compareTo(Person other) 
	{
		
		return 0;
	}
}
