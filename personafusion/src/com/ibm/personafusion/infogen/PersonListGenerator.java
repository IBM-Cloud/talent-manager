package com.ibm.personafusion.infogen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.ibm.personafusion.Constants;
import com.ibm.personafusion.model.*;
import com.ibm.personafusion.service.WatsonUserModeller;

public class PersonListGenerator {

	
	public static void main(String[] args) {
		generateDistinctPeople(100, 20, "IBM");		
	}
	
	/**
	 * Will generate a new randomized list of N people with unique names and random backgrounds/skills.
	 * As of now, numPeople is currently maxxed at 100. Over 100 would give an error.
	 * 
	 * @param numPeople - Number of people to generate overall (Currently maxed at 100)
	 * @param numCurrEmploy - How many people are to be under the current company. Must be less than numPeople
	 * @param currEmploy - Name of the current company for the numCurrEmploy
	 * @return Empty List if numCurrEmploy > numPeople.
	 */
	public static List<Person> generateDistinctPeople (int numPeople, int numCurrEmploy, String currEmploy) {
		
		if(numCurrEmploy > numPeople) return new ArrayList<Person>();
		
		List<Person> result = new ArrayList<Person>();

		int pplCounter = 1;
		
		String imgURL = "";
		List<Trait> traitList;
		
		
		try {
			URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/imageLinks.txt");
			Scanner scan0 = new Scanner(url.openStream());
			
			// Set<String> nameList = NameGenerator.generateDistinctFullNames(numPeople);
			Set<String> nameList = getNameList();
			for(String name : nameList) {
				// Call Watson to get traits. Call keyword converter to get keywords.
				
				if(scan0.hasNextLine()) {
					imgURL = scan0.nextLine();
				}				

				System.out.println("PplCounter: " + pplCounter);
				
				WatsonUserModeller WUM = new WatsonUserModeller();
				List<String> qaResponses = QuestionResponse.getResponseList();
				String response = QuestionResponse.convertToFullString(qaResponses);
				traitList = WUM.getTraitsList(response);				
				
//				for(String res : responseList) {
//					System.out.println(res);
//				}
								
				// Assign a role
				
				// Devs working at curr company
				if(pplCounter <= (numCurrEmploy/2)) {
					
					System.out.println("Dev - Curr");
					
					List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Dev");
					List<String> prevInfo = new ArrayList<String>();
					prevInfo.add(currEmploy);
					ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
	
					Person newPerson = new Person(name, traitList, imgURL, ri, Person.Role.DEV, new ArrayList<String>());
					
					/*
					 * ***TODO Restructure to read in a list of strings that represent the answers to interview questions
					 */
					newPerson.qaResponses = qaResponses;
					
					newPerson.keyWords = newPerson.getKeyWords(10);
					newPerson.image_url = imgURL;
					newPerson.group = Constants.CURRENT_EMPLOYEES_GROUP;
					result.add(newPerson);
					
					pplCounter++;
					
				}
				// Managers working at curr company
				else if (pplCounter <= numCurrEmploy) {
					
					System.out.println("Man - Curr");
					
					List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Manager");
					List<String> prevInfo = new ArrayList<String>();
					prevInfo.add(currEmploy);
					ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
										
					Person newPerson = new Person(name, traitList, imgURL, ri, Person.Role.Manager, new ArrayList<String>());
					
					newPerson.qaResponses = qaResponses;
					
					newPerson.keyWords = newPerson.getKeyWords(10);
					newPerson.image_url = imgURL;
					newPerson.group = Constants.CURRENT_EMPLOYEES_GROUP;
					result.add(newPerson);
					
					pplCounter++;
				}
				// Dev not at curr company
				else if (pplCounter <= (numCurrEmploy + ((numPeople - numCurrEmploy)/2))) {
					
					System.out.println("Dev - Not Curr");
					
					List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Dev");
					List<String> prevInfo = ResumeInfoGenerator.generatePrev("Dev");
					ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
					
					Person newPerson = new Person(name, traitList, imgURL, ri, Person.Role.DEV, new ArrayList<String>());
					
					newPerson.qaResponses = qaResponses;
					
					newPerson.keyWords = newPerson.getKeyWords(10);
					newPerson.image_url = imgURL;
					newPerson.group = "HAHAHA: Help Alan Heal A Healthy Athlete.";
					result.add(newPerson);
					
					pplCounter++;
				}
				// Manager not at curr company
				else {
					
					System.out.println("Man - Not Curr");
					
					List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Manager");
					List<String> prevInfo = ResumeInfoGenerator.generatePrev("Manager");
					ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
					
					Person newPerson = new Person(name, traitList, imgURL, ri, Person.Role.Manager, new ArrayList<String>());
					/*
					 * ***TODO Restructure to read in a list of strings that represent the answers to interview questions
					 */
					
					newPerson.qaResponses = qaResponses;

					newPerson.keyWords = newPerson.getKeyWords(10);
					newPerson.image_url = imgURL;
					newPerson.group = "HAHAHA: Help Alan Heal A Healthy Athlete.";
					result.add(newPerson);
					
					pplCounter++;
				}
				
			}
			scan0.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return result;
	}
	
	public static Set<String> getNameList() {
		Set<String> result = new HashSet<String>();
		
		try {
			URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/output.txt");
			Scanner scan = new Scanner(url.openStream());
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				result.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
