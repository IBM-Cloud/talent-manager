package com.ibm.personafusion;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import com.ibm.personafusion.controller.JsonUtils;
import com.ibm.personafusion.infogen.PersonListGenerator;
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
		/*
		List<Person> people = new ArrayList<Person>();
		String alphName = "abcdefghij";
		for(int i=0; i<10; i++)
		{
			String name = alphName.charAt(i) + "";
			List<Trait> traits = new ArrayList<Trait>();
			people.add(new Person(name, genTraits(traits), new ResumeInfo(), Person.Role.DEV));
		}
		*/
		//System.out.println(people.toString());
		
		
		//Engine Example
		
		//FIX CLONING SO YOU DONT KEEP DELETING THE QUERY AT POSITION 0
		// List<Person> genPeople = PersonListGenerator.generateDistinctPeople(10);
		// JsonUtils jsonUtils = new JsonUtils();
		// Engine engineGen = new Engine(genPeople);
		// //List<Person> peopleResultsGen = engineGen.query(genPeople.get(0));
		// List<Person> peopleResultsGen = engineGen.query("EMORY WREN");
		// String pJsonQuery = jsonUtils.getJson(genPeople.get(0));
		// System.out.println(pJsonQuery);
		// System.out.println(peopleResultsGen.get(0).name);
		// System.out.println(peopleResultsGen.get(0).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(1).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(2).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(3).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(4).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(5).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(6).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(7).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(8).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(9).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(10).distToQueryPerson);
		// System.out.println(peopleResultsGen.get(11).distToQueryPerson);
		// String pJson = jsonUtils.getJson(peopleResultsGen.get(1));
		// System.out.println(pJson);
		
		/*
		Engine engine = new Engine(people);
		List<Person> peopleResults = engine.query(people.get(0));
		System.out.println(peopleResults.get(2).distToQueryPerson);
		System.out.println(peopleResults.toString());
		
		Engine engine2 = new Engine(people);
		List<Person> peopleResultsName = engine2.query("c");
		System.out.println(peopleResultsName.toString());
		*/
	}
	
	List<Trait> genTraits(List<Trait> traits)
	{
		
		String alph = "abcd";
		for(int i=0; i<20; i++)
		{
			String traitName = "";
			for(int j=0; j<4; j++)
				traitName += alph.charAt((int)(Math.random()*4));
			traits.add(new Trait(traitName, Math.random()*100));
		}
		return traits;
	}
}
