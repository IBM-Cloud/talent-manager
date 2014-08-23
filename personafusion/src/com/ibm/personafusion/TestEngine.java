package com.ibm.personafusion;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import com.ibm.personafusion.model.Person;
import com.ibm.personafusion.model.ResumeInfo;
import com.ibm.personafusion.model.Trait;

public class TestEngine 
{
	public static void main(String[] args)
	{
		new TestEngine().doit();
	}
	
	void doit()
	{		
		List<Person> people = new ArrayList<Person>();
		String alphName = "abcdefghij";
		for(int i=0; i<10; i++)
		{
			String name = alphName.charAt(i) + "";
			List<Trait> traits = new ArrayList<Trait>();
			people.add(new Person(name, genTraits(traits), new ResumeInfo(), Person.Role.DEV));
		}
		System.out.println(people.toString());
		
		
		//Engine Example
		Engine engine = new Engine(people);
		List<Person> peopleResults = engine.query(people.get(0));
		System.out.println(peopleResults.toString());
		
		List<Person> peopleResultsName = engine.query("c");
		System.out.println(peopleResults.toString());
	}
	
	List<Trait> genTraits(List<Trait> traits)
	{
		
		String alph = "abcd";
		for(int i=0; i<20; i++)
		{
			String traitName = "";
			for(int j=0; j<4; j++)
				traitName += alph.charAt((int)(Math.random()*4));
			traits.add(new Trait(traitName, Math.random()));
		}
		return traits;
	}
}
