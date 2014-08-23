package com.ibm.personafusion.model;

import java.util.List;

public class Person implements Comparable<Person>
{
	String name;
	List<Trait> traits;
	enum Role {DEV, Manager};
	Role role;
	
	Person queryPerson;
	double distToQueryPerson;
	
	Person(String name, List<Trait> traits, Role role)
	{
		this.name = name;
		this.traits = traits;
		this.role = role;
	}
	
	void setQueryPerson(Person p)
	{
		this.queryPerson = p;
	}
	
	public String toString()
	{
		String pString = "";
		pString = traits.toString();
		return pString;
	}
	
	double getDistanceToQueryPerson()
	{
		double distance = 0;
		for(int i=0; i<this.queryPerson.traits.size(); i++)
		{
			String queryTraitName = this.queryPerson.traits.get(i).traitName;
			double queryTraitPercent = this.queryPerson.traits.get(i).percent;
			distance += this.getTraitDistance(queryTraitName, queryTraitPercent);
		}
		return distance;
	}
	
	double getTraitDistance(String queryTrait, double queryScore)
	{
		double distance = 1;//worst case if the trait does not exist return max value 1
		for(int i=0; i<this.traits.size(); i++)
		{
			if(queryTrait.equals(this.traits.get(i).traitName))
			{
				double tempDist = queryScore - this.traits.get(i).percent;
				distance = Math.pow(queryScore, 2);
				break;//found trait distance break loop
			}
		}
		return distance;
	}
	
	public int compareTo(Person other) 
	{
		//sort bases off min distance from query perosn
		//weight different distances between resume skills, traits, and role
		return 0;
	}
}
