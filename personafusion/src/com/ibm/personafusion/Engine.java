package com.ibm.personafusion;

import java.util.List;

import com.ibm.personafusion.model.Person;

public class Engine 
{
	List<Person> people;
	
	Engine(List<Person> people)
	{
		this.people = people;
	}
	
	List<Person> query(Person p)
	{
		//sort person list based off query person p
		return this.people;
	}
}
