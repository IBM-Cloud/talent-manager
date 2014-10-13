package com.ibm.personafusion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.personafusion.infogen.QuestionResponse;
import com.ibm.personafusion.model.Person.Role;
import com.ibm.personafusion.service.WatsonUserModeller;

/** Models the JSON received from an POST /api/add request. **/
public class AddPersonRequest
{
	public String firstName;
	public String lastName;
	public String role;
	public Map<String, List<String>> resumeInfo;
	public List<String> responses;
	public String imageUrl;
	public String group;

	public static Person toPerson(AddPersonRequest apr)
	{
		String fullName = apr.firstName + " " + apr.lastName;
		Role r = apr.role.equals("DEV") ? Role.DEV : Role.Manager;
		Person p = new Person(fullName, getTraits(apr), apr.imageUrl, constructResumeInfo(apr), r, new ArrayList<String>());
		p.qaResponses = apr.responses;
		p.group = apr.group;
		p.keyWords = p.getKeyWords(10);
		return p;
	}
	
	private static List<Trait> getTraits(AddPersonRequest apr)
	{
		// get traits
		WatsonUserModeller WUM = new WatsonUserModeller();
		String response = QuestionResponse.convertToFullString(apr.responses);
		List<Trait> traits = WUM.getTraitsList(response);
		return traits;
	}
	
	private static ResumeInfo constructResumeInfo(AddPersonRequest apr)
	{
		// construct ResumeInfo
		ResumeInfo ri = new ResumeInfo();
		ri.pastEmployers = apr.resumeInfo.get("pastEmployers");
		ri.techSkills = apr.resumeInfo.get("techSkills");
		return ri;
	}

}
