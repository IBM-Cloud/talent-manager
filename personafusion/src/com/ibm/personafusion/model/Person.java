package com.ibm.personafusion.model;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Person implements Comparable<Person>
{
	public String name;
	public List<Trait> traits;
	public enum Role {DEV, Manager};
	public Role role;
	public ResumeInfo resumeInfo;
	public List<String> tweets;
	public String image_url;
	public List<String> keyWords;
	
	//one queryPerson for everyone
	static Person queryPerson;
	//different distances for everyone
	public double distToQueryPerson;
	//one set of weights for everyone
	static double weightTraits, weightResume, weightRole;
	
	public Person(String name, List<Trait> traits, ResumeInfo resumeInfo, Role role)
	{
		this.name = name;
		this.traits = traits;
		this.resumeInfo = resumeInfo;
		this.role = role;
		//Set default weights
		this.weightTraits = 1;
		this.weightResume = 1;
		this.weightRole = 1;
		this.image_url = "";
		this.tweets = new ArrayList<String>();
		this.keyWords = new ArrayList<String>();
	}
	
	public Person(String name, List<Trait> traits, ResumeInfo resumeInfo)
	{
		this.name = name;
		this.traits = traits;
		this.resumeInfo = resumeInfo;
		this.role = role;
		//Set default weights
		this.weightTraits = 1;
		this.weightResume = 1;
		this.weightRole = 1;
		this.image_url = "";
		this.role = Role.DEV;
		this.tweets = new ArrayList<String>();
		this.keyWords = new ArrayList<String>();
	}
	
	public void setQueryPerson(Person p)
	{
		this.queryPerson = p;
	}
	
	public String toString()
	{
		String pString = "";
		pString = traits.toString();
		//pString = this.name + " , " + this.distToQueryPerson;
		return pString;
	}
	
	public void setDistanceWeights(double weightTraits, double weightResume, double weightRole)
	{
		this.weightTraits = weightTraits;
		this.weightResume = weightResume;
		this.weightRole = weightRole;
	}
	
	/*
	 * Remove stop words and return N most frequent words
	 */
	public List<String> getKeyWords(int nMostFrequent)
	{
		List<String> stopWords = new ArrayList<String>();
		try
		{
			URL url = new URL("https://dl.dropboxusercontent.com/u/27101002/personafusion/stopWords.txt");
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNextLine())
			{
				String word = sc.nextLine();
				word = word.trim();
				stopWords.add(word);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		List<String> keyWords = new ArrayList<String>();
		Map<String, Integer> keyMapCount = new TreeMap<String, Integer>();
		for(String tweet : this.tweets)
		{
			String[] tweetParts = tweet.split(" ");
			for(String tweetWord : tweetParts)
			{
				if(keyMapCount.containsKey(tweetWord))
				{
					int value = keyMapCount.get(tweetWord);
					keyMapCount.put(tweetWord, value+1);
				}
				else
				{
					keyMapCount.put(tweetWord, 1);
				}
			}
		}
		
		List<TweetTerm> termList = new ArrayList<TweetTerm>();
		for(String  tweetWord : keyMapCount.keySet())
		{
			tweetWord = tweetWord.toLowerCase();
			if(!stopWords.contains(tweetWord))
				termList.add(new TweetTerm(tweetWord, keyMapCount.get(tweetWord)));
		}
		Collections.sort(termList);
		for(int i=0; i<nMostFrequent; i++)
		{
			keyWords.add(termList.get(i).word);
		}
		return keyWords;
	}
	
	class TweetTerm implements Comparable<TweetTerm>
	{
		String word;
		int wordCount;
		
		TweetTerm(String word, int wordCount)
		{
			this.word = word;
			this.wordCount = wordCount;
		}
		
		public int compareTo(TweetTerm other) 
		{
			return other.wordCount - this.wordCount;
		}
		
	}
	
	double getDistanceToQueryPerson()
	{
		
		double distance = 0, distanceTraits = 0, distanceResume = 0, distanceRole = 0;
		
		for(int i=0; i<this.queryPerson.traits.size(); i++)
		{
			String queryTraitName = this.queryPerson.traits.get(i).traitName;
			double queryTraitPercent = this.queryPerson.traits.get(i).percent;
			distanceTraits += this.getTraitDistance(queryTraitName, queryTraitPercent);
		}
		
		double distanceTechSkills = 0, distancePastEmployers = 0;
		for(int i=0; i<this.queryPerson.resumeInfo.techSkills.size(); i++)
		{
			//if all query tech skills exist in this person distance is 0
			//distance grows as skills dont match
			String techSkill = this.queryPerson.resumeInfo.techSkills.get(i);
			if(!this.resumeInfo.techSkills.contains(techSkill))
				distanceTechSkills++;
			
		}	
		for(int i=0; i<this.queryPerson.resumeInfo.pastEmployers.size(); i++)
		{
			String pastEmployer = this.queryPerson.resumeInfo.pastEmployers.get(i);
			if(!this.resumeInfo.techSkills.contains(pastEmployer))
				distancePastEmployers++;
		}
		distanceResume = (.75 * distancePastEmployers) + (.25 * distanceTechSkills);
		
		if(!this.queryPerson.role.equals(this.role))
			distanceRole = 1;
		
		distance = (weightTraits * distanceTraits) + (weightResume * distanceResume) + (weightRole * distanceRole);
		this.distToQueryPerson = distance;
		return distance;
	}
	
	double getTraitDistance(String queryTrait, double queryScore)
	{
		double distance = 1;//worst case if the trait does not exist return max value 1
		for(int i=0; i<this.traits.size(); i++)
		{
			if(queryTrait.equals(this.traits.get(i).traitName))
			{
				double tempDist = queryScore - this.traits.get(i).percent;
				distance = Math.pow(tempDist, 2);
				break;//found trait distance break loop
			}
		}
		return distance;
	}
	
	public int compareTo(Person other) 
	{
		//sort bases off min distance from query perosn
		//weight different distances between resume skills, traits, and role
		double thisDistance = this.getDistanceToQueryPerson();
		double otherDistance = other.getDistanceToQueryPerson();
		if(thisDistance < otherDistance)
			return -1;
		if(thisDistance > otherDistance)
			return 1;
		else
			return 0;
	}
}
