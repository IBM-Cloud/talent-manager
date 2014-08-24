package com.ibm.personafusion.infogen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class ResumeInfoGenerator {

	final static String DevRole = "Dev";
	final static String ManagerRole = "Manager";
	
	final static int minSkills = 3;
	final static int maxSkills = 7;
	
	final static int minPrev = 1;
	final static int maxPrev = 4;
	
	// Dev skills populated for sake of common skills vs. rare skills
//	final static String DevSkillURL = "person_source/resumeinfo/dev_skill.txt";
	final static int DevSkillLength = 160;
	
//	final static String DevPrevURL = "person_source/resumeinfo/dev_prev.txt";
	final static int DevPrevLength = 32;
	
//	final static String ManSkillURL = "person_source/resumeinfo/manager_skill.txt";
	final static int ManSkillLength = 74;
	
//	final static String ManPrevURL = "person_source/resumeinfo/manager_prev.txt";
	final static int ManPrevLength = 43;
	
	public static void main(String[] args) {
		
		List<String> devSkillList = generateTechSkill("Dev");
		System.out.println("Skill List: Dev");
		for(String i : devSkillList) {
			System.out.println(i);
		}
		
		System.out.println();
		
		List<String> devPrevList = generatePrev("Dev");
		System.out.println("Prev List: Dev");
		for(String i : devPrevList) {
			System.out.println(i);
		}
		
		System.out.println();
		
		List<String> manSkillList = generateTechSkill("Manager");
		System.out.println("Skill List: Manager");
		for(String i : manSkillList) {
			System.out.println(i);
		}

		System.out.println();
		
		List<String> manPrevList = generatePrev("Manager");
		System.out.println("Prev List: Manager");
		for(String i : manPrevList) {
			System.out.println(i);
		}
	}
	
	/**
	 * Takes in a String representing the role and returns a random amount of skills associated with the role.
	 * The number ranges from the min and max assigned in the class.
	 * If the role doesn't exist, returns null.
	 * 
	 * @param role that the person is in. (For the purpose of POC, use only either Dev or Manager).
	 * @return A list of Skill strings according to said role
	 * @throws FileNotFoundException 
	 */
	public static List<String> generateTechSkill (String role) {
		Set<String> skillList = new HashSet<String>();
		
		Random rand = new Random();
		int skillNum = rand.nextInt(maxSkills - minSkills) + minSkills;
		
		if(DevRole.equals(role)) {
			while(skillList.size() < skillNum) {
				int skillInd = rand.nextInt(DevSkillLength - 1) + 1;
				
				String skill = "";
				Scanner scanner;
				try {
					URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/dev_skill.txt");	
					scanner = new Scanner(url.openStream());
				
					int counter = 0;
					while(scanner.hasNextLine()) {
						counter++;
						String line = scanner.nextLine();
						
						if(counter == skillInd) {
							skill = line;
							break;
						}
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if(!skillList.contains(skill)) skillList.add(skill);
			}
			
			List<String> result = new ArrayList<String>();
			result.addAll(skillList);
			
			return result;
		}
		else if(ManagerRole.equals(role)) {
			while(skillList.size() < skillNum) {
				int skillInd = rand.nextInt(ManSkillLength - 1) + 1;
								
				String skill = "";
				Scanner scanner;
				try {
					URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/manager_skill.txt");
					scanner = new Scanner(url.openStream());
					
					int counter = 0;
					while(scanner.hasNextLine()) {
						counter++;
						String line = scanner.nextLine();
						
						if(counter == skillInd) {
							skill = line;
							break;
						}
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if(!skillList.contains(skill)) skillList.add(skill);
			}
			
			List<String> result = new ArrayList<String>();
			result.addAll(skillList);
			
			return result;
		}
		else return null;
	}

	/**
	 * Takes in a String representing the role and returns a random amount of previous employers associated with the role.
	 * The number ranges from the min and max assigned in the class.
	 * If the role doesn't exist, returns null.
	 * 
	 * @param role that the person is in. (For the purpose of POC, use only either Dev or Manager).
	 * @return A list of prev Employers strings according to said role
	 * @throws FileNotFoundException 
	 */
	public static List<String> generatePrev (String role) {
		Set<String> prevList = new HashSet<String>();
		
		Random rand = new Random();
		int prevNum = rand.nextInt(maxPrev - minPrev) + minPrev;
		
		if(DevRole.equals(role)) {
			while(prevList.size() < prevNum) {
				int prevInd = rand.nextInt(DevPrevLength - 1) + 1;
								
				String prev = "";
				Scanner scanner;
				try {
					URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/dev_prev.txt");
					scanner = new Scanner(url.openStream());
					
					int counter = 0;
					while(scanner.hasNextLine()) {
						counter++;
						String line = scanner.nextLine();
						
						if(counter == prevInd) {
							prev = line;
							break;
						}
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if(!prevList.contains(prev)) prevList.add(prev);
			}
			
			List<String> result = new ArrayList<String>();
			result.addAll(prevList);
			
			return result;
		}
		else if(ManagerRole.equals(role)) {
			while(prevList.size() < prevNum) {
				int prevInd = rand.nextInt(ManPrevLength - 1) + 1;

				String prev = "";
				Scanner scanner;
				try {
					URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/manager_prev.txt");
					scanner = new Scanner(url.openStream());
					
					int counter = 0;
					while(scanner.hasNextLine()) {
						counter++;
						String line = scanner.nextLine();
						
						if(counter == prevInd) {
							prev = line;
							break;
						}
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				

				if(!prevList.contains(prev)) prevList.add(prev);
			}
			
			List<String> result = new ArrayList<String>();
			result.addAll(prevList);
			
			return result;
		}
		else return null;
	}
	
}
