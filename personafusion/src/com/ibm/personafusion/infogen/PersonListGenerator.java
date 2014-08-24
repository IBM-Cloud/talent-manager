package com.ibm.personafusion.infogen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.ibm.personafusion.model.*;
import com.ibm.personafusion.service.WatsonUserModeller;

public class PersonListGenerator {

//	final static double devProb = 0.5;
//	final static double manProb = 0.5;
	final static int devNum = 50;
	
//	final static String devTweetsURL = "dev_tweets.txt";
//	final static String manTweetsURL = "mgr_tweets.txt";
	final static int tweetsPerPerson = 200;

//	final static String uniqueNameListURL = "person_source/name/output.txt";
	
	public static void main(String[] args) {
	//	long startTime = System.nanoTime();
		generateDistinctPeople(100);
	//	long endTime = System.nanoTime();

	//	long duration = (endTime - startTime)/1000000;
	//	System.out.println(duration + " milliseconds");
		
	}
	
	/**
	 * EDIT: numPeople parameter will no longer affect the amount of people to be generated.
	 * As of now, 100 people will always be generated.
	 * 
	 * @param numPeople
	 * @return
	 */
	public static List<Person> generateDistinctPeople (int numPeople) {
		
		List<Person> result = new ArrayList<Person>();
		
		Random rand = new Random();
				
		List<String> initialDevTweets = new ArrayList<String>();
		
		BufferedReader in;
		try {
			URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/dev_tweets.txt");
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while((line = in.readLine()) != null) {
				line = line.trim();
				if(line.length() < 10) continue;
				initialDevTweets.add(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		System.out.println(initialDevTweets.size());
		
		List<String> initialManTweets = new ArrayList<String>();
		try {
			URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/mgr_tweets.txt");
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while((line = in.readLine()) != null) {
				line = line.trim();
				if(line.length() < 10) continue;
				initialManTweets.add(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(initialManTweets.size());
		
		try {
			URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/imageLinks.txt");
			Scanner scan0 = new Scanner(url.openStream());
			String imgURL = "";
			
			int pplCounter = 1;
			//Set<String> nameList = NameGenerator.generateDistinctFullNames(numPeople);
			Set<String> nameList = getNameList();
			for(String name : nameList) {
				// Get 200 different tweets per person. Call Watson to get traits. Call keyword converter to get keywords.
				
				if(scan0.hasNextLine()) {
					imgURL = scan0.nextLine();
				}
				
				List<Trait> traitList;				
				
				System.out.println("PplCounter: " + pplCounter);
				
				if(pplCounter == 80) return result;
				
				// Assign a role
				if(pplCounter < devNum) {
					List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Dev");
					List<String> prevInfo = ResumeInfoGenerator.generatePrev("Dev");
					ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
	
					List<String> tweets = new ArrayList<String>();
					String onetweetline = "";
					
					while(tweets.size() != tweetsPerPerson) {
						String line = initialDevTweets.remove(0);
						tweets.add(line);
						onetweetline += line;
					}
					
					WatsonUserModeller WUM = new WatsonUserModeller();
					traitList = WUM.getTraitsList(onetweetline);
					
//					System.out.println(name);
//					System.out.println(onetweetline);
//					System.out.println(imgURL);
//					for(Trait i : traitList) {
//						System.out.print(i.traitName + ": " + i.percent + ", ");
//					}
//					System.out.println();
					
					Person newPerson = new Person(name, traitList, ri, Person.Role.DEV);
					newPerson.tweets = tweets;
					newPerson.image_url = imgURL;
					System.out.println(newPerson.image_url);
					result.add(newPerson);
					
					pplCounter++;
					
				}
				else {
					List<String> techSkills = ResumeInfoGenerator.generateTechSkill("Manager");
					List<String> prevInfo = ResumeInfoGenerator.generatePrev("Manager");
					ResumeInfo ri = new ResumeInfo(techSkills, prevInfo);
					
					System.out.println("Got past ResumeInfo.");
					
					List<String> tweets = new ArrayList<String>();
					String onetweetline = "";
					
					while(tweets.size() != tweetsPerPerson) {
						String line = initialManTweets.remove(0);
						tweets.add(line);
						onetweetline += line;
					}
					
					System.out.println("Got past tweeting.");
					
					System.out.println(onetweetline);
					
					WatsonUserModeller WUM = new WatsonUserModeller();
					traitList = WUM.getTraitsList(onetweetline);
					
					System.out.println("Got past traiting.");
					
					Person newPerson = new Person(name, traitList, ri, Person.Role.Manager);
					newPerson.tweets = tweets;
					newPerson.image_url = imgURL;
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
