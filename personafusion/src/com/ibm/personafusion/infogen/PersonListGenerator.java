package com.ibm.personafusion.infogen;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.ibm.personafusion.model.*;

public class PersonListGenerator {

	final static double devProb = 0.75;
	final static double manProb = 0.25;
	
	/**
	 * 
	 * 
	 * @param numPeople
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static List<Person> generateDistinctPeople (int numPeople) {
		
		List<Person> result = new ArrayList<Person>();
		
		Random rand = new Random();
		
		Set<String> nameList = NameGenerator.generateDistinctFullNames(numPeople);
		for(String name : nameList) {
			// TODO: get a proper trait list for each person.
			List<Trait> traitList = new ArrayList<Trait>();
			
			// Assign a role
			double roleIndicator = rand.nextDouble();
			if(roleIndicator > devProb) {
				List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Dev");
				List<String> prevInfo = ResumeInfoGenerator.generatePrev("Dev");
				ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
				
				result.add(new Person(name, traitList, ri, Person.Role.DEV));
				
			}
			else {
				List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Manager");
				List<String> prevInfo = ResumeInfoGenerator.generatePrev("Manager");
				ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
				
				result.add(new Person(name, traitList, ri, Person.Role.Manager));
				
			}
			
		}
		
		return result;
	}
	
}
