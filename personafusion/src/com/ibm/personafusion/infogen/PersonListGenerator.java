package com.ibm.personafusion.infogen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.ibm.personafusion.model.*;
import com.ibm.personafusion.service.WatsonUserModeller;

public class PersonListGenerator {

	final static double devProb = 0.5;
	final static double manProb = 0.5;
	
	final static String devTweetsURL = "dev_tweets.txt";
	final static String manTweetsURL = "mgr_tweets.txt";
	final static int tweetsPerPerson = 200;

	public static void main(String[] args) {
		generateDistinctPeople(1);
	}
	
	/**
	 * 
	 * 
	 * @param numPeople
	 * @return
	 */
	public static List<Person> generateDistinctPeople (int numPeople) {
		
		List<Person> result = new ArrayList<Person>();
		
		Random rand = new Random();
		
		Set<String> nameList = NameGenerator.generateDistinctFullNames(numPeople);
		for(String name : nameList) {
			// Get 200 different tweets per person. Call Watson to get traits. Call keyword converter to get keywords.
			int devCounter = 1;
			int manCounter = 1;
			
			List<Trait> traitList;
			
			
			// Assign a role
			double roleIndicator = rand.nextDouble();
			if(roleIndicator > devProb) {
				List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Dev");
				List<String> prevInfo = ResumeInfoGenerator.generatePrev("Dev");
				ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);

				List<String> tweets = new ArrayList<String>();
				String onetweetline = "";
				
				File file = new File(devTweetsURL);
				
				Scanner scanner;
				try {
					scanner = new Scanner(file);
				
					if(scanner.hasNextLine()) {
						String line = scanner.nextLine();
						tweets.add(line);
						onetweetline += line;
					}
					
					while((devCounter % tweetsPerPerson != 0) && scanner.hasNextLine()) {
						String line = scanner.nextLine();
						devCounter++;
						tweets.add(line);
						onetweetline += line;
					}
					
					devCounter++;
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				WatsonUserModeller WUM = new WatsonUserModeller();
				traitList = WUM.getTraitsList(onetweetline);
								
				Person newPerson = new Person(name, traitList, ri, Person.Role.DEV);
				newPerson.tweets = tweets;
				result.add(newPerson);
				
			}
			else {
				List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Manager");
				List<String> prevInfo = ResumeInfoGenerator.generatePrev("Manager");
				ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
				
				List<String> tweets = new ArrayList<String>();
				String onetweetline = "";
				
				File file = new File(manTweetsURL);
				
				Scanner scanner;
				try {
					scanner = new Scanner(file);
				
					if(scanner.hasNextLine()) {
						String line = scanner.nextLine();
						tweets.add(line);
						onetweetline += line;
					}
					
					while((manCounter % tweetsPerPerson != 0) && scanner.hasNextLine()) {
						String line = scanner.nextLine();
						manCounter++;
						tweets.add(line);
						onetweetline += line;
					}
					
					manCounter++;
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				WatsonUserModeller WUM = new WatsonUserModeller();
				traitList = WUM.getTraitsList(onetweetline);
				
				Person newPerson = new Person(name, traitList, ri, Person.Role.Manager);
				newPerson.tweets = tweets;
				result.add(newPerson);
				
			}
			
		}
		
		return result;
	}
	
}
