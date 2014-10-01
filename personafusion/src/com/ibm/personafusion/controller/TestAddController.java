package com.ibm.personafusion.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ibm.personafusion.Config;
import com.ibm.personafusion.model.Person;
import com.ibm.personafusion.model.Person.Role;

public class TestAddController 
{
	String json = 	"{   \"firstName\": \"JOHN\","+
					    "\"lastName\": \"SMITH\","+
					    "\"role\": \"DEV\","+
					    "\"resumeInfo\": {"+
					        "\"techSkills\": ["+
					            "\"skill1\","+
					            "\"skill2\""+
					        "],"+
					        "\"pastEmployers\": ["+
					           "\"Google\","+
					            "\"IBM\""+
					        "]"+
					    "},"+
					    "\"responses\": ["+
					       "\"answer to question 1\","+
					       "\"answer to question 2\","+
					        "\"answer to question n\""+
					    "],"+
					    "\"imageUrl\": \"http://images.com\","+
					    "\"group\": \"CANDIDATE\""+
					"}";
	
	@Test
	public void testFullAddWithoutDB() 
	{
		Person p = AddController.personFromRequest(json);
		assertTrue(p != null);
		assertTrue(p.name.equals("JOHN SMITH"));
		assertTrue(p.role.equals(Role.DEV));
		assertTrue(p.resumeInfo != null);
		assertTrue(p.qaResponses.get(0).equals("answer to question 1"));
		assertTrue(p.qaResponses.get(2).equals("answer to question n"));
		assertTrue(p.image_url.equals("http://images.com"));
		assertTrue(p.group.equals("CANDIDATE"));
		assertTrue(p.traits.size() > 50);
	}
	
	@Test
	public void testFullAddWithDB() 
	{
		Person p = AddController.personFromRequest(json);
		Config.cc.putPerson(p);
		Person p2 = Config.cc.getPerson("JOHN SMITH");
		assertTrue(p2 != null);
		assertTrue(p2.name.equals("JOHN SMITH"));
		assertTrue(p2.role.equals(Role.DEV));
		assertTrue(p2.resumeInfo != null);
		assertTrue(p2.qaResponses.get(0).equals("answer to question 1"));
		assertTrue(p2.qaResponses.get(2).equals("answer to question n"));
		assertTrue(p2.image_url.equals("http://images.com"));
		assertTrue(p2.group.equals("CANDIDATE"));
		assertTrue(p2.traits.size() > 50);
		Config.cc.deletePerson(p);
	}

}
