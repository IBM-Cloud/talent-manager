package com.ibm.personafusion;

import java.util.Collections;
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
		this.setQueryPerson(p);
		this.setDistanceWeights(.5, .25 , .25);
		Collections.sort(this.people);
		return this.people;
	}
	
	void setQueryPerson(Person p)
	{
		people.get(0).setQueryPerson(p);
	}
	
	void setDistanceWeights(double weightTraits, double weightResume, double weightRole)
	{
		people.get(0).setDistanceWeights(weightTraits, weightResume, weightRole);
	}
}
