package com.ibm.personafusion.model;

import java.util.List;

/*
 * Resume Info is going to not be associated with Watson API
 */

public class ResumeInfo 
{
	List<String> techSkills;
	List<String> pastEmployers;
	
	ResumeInfo(List<String> techSkills, List<String> pastEmployers)
	{
		this.techSkills = techSkills;
		this.pastEmployers = pastEmployers;
	}
	
	void addTechSkill(String skill)
	{
		this.techSkills.add(skill);
	}
	
	void addPastEmployer(String pastEmployer)
	{
		this.pastEmployers.add(pastEmployer);
	}
}
