package com.ibm.personafusion.infogen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class NameGenerator {

	final static String BothFirstURL = "person_source/name/both_first.txt";
	final static int BothFirstLength = 1780;
	
	// In the case we may need to distinguish gender. Highly unlikely, but just in case.
	final static String FemFirstURL = "person_source/name/fem_first.txt";
	final static int FemFirstLength = 1055;
	
	// In the case we may need to distinguish gender. Highly unlikely, but just in case.
	final static String MalFirstURL = "person_source/name/mal_first.txt";
	final static int MalFirstLength = 791;
	
	final static String LastURL = "person_source/name/last.txt";
	final static int LastLength = 4760;
	
	final static String OutputURL = "person_source/name/output.txt";
	
	public static void main(String[] args) {
		
		generateDistinctFullNames(100);
		
	}
	
	/**
	 * This method generates a set of unique names (the amount depends on the parameter).
	 * It takes this set, outputs it in the output url that's been assigned in the class and returns the set.
	 *
	 * @param	int peopleAmount - the number of unique full names you want to be generated.
	 * @return      A set of unique names. First and last names separately may not be unique, but each entire/full name is.
	 */
	public static Set<String> generateDistinctFullNames(int peopleAmount) {
		Set<String> nameList = new HashSet<String>();
		while(nameList.size() < peopleAmount) {
			
			Random rand = new Random();
			int FirstInd = rand.nextInt(BothFirstLength - 1) + 1;
			int LastInd = rand.nextInt(LastLength - 1) + 1;
			
			File file = new File(BothFirstURL);
			
			String first = "";
			Scanner scanner;
			try {
				scanner = new Scanner(file);
			
				int counter = 0;
				while(scanner.hasNextLine()) {
					counter++;
					String line = scanner.nextLine();
					
					if(counter == FirstInd) {
						first = line;
						break;
					}
				}
				
				file = new File(LastURL);
				
				String last = "";
				scanner = new Scanner(file);
				counter = 0;
				while(scanner.hasNextLine()) {
					counter++;
					String line = scanner.nextLine();
					
					if(counter == LastInd) {
						last = line;
						break;
					}
				}
				
				String name = first + " " + last;
				name.trim();
				
				if(!nameList.contains(name)) nameList.add(first + " " + last);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(OutputURL, "UTF-8");

			for(String s : nameList) {
				writer.println(s);
			}
			
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return nameList;
	}

}
