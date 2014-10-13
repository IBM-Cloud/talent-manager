package com.ibm.personafusion.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

/** A wrapper for using the Twitter API. 
 *  @author Sean Welleck **/
public class TwitterClient 
{
	Twitter twitter;
	public TwitterClient()
	{
		TwitterFactory tf = new TwitterFactory();
		twitter = tf.getInstance();
	}
	
	public List<String> getUserTweetsText(String username, int n) throws TwitterException
	{
		List<String> tweets = new ArrayList<String>();
		if (n <= 0) { return tweets; }
		int iters = (int) Math.ceil(n / 200.0);
		for(int i = 0; i<iters; i++)
		{
			Paging p = new Paging(1, 200);
			ResponseList<Status> statuses = this.twitter.getUserTimeline(username, p);
			for (Status s: statuses)
			{
				tweets.add(s.getText());
			}
		}

		return tweets;
	}
	
	public List<String> getPhotoURLs(String seedname, int n) throws TwitterException
	{
		List<String> urls = new ArrayList<String>();
		Set<Long> history = new HashSet<Long>();
		if (n <= 0) { return urls; }
		User current = this.twitter.showUser(seedname);
		while(urls.size() < n)
		{
			long[] ids = this.twitter.getFriendsIDs(current.getId()).getIDs();
			for (long id: ids)
			{
				if (!history.contains(id))
				{
					current = this.twitter.showUser(id);
					urls.add(current.getOriginalProfileImageURL());
					if (urls.size() == n) break;
				}
			}
		}
		return urls;
	}
	
	public void downloadTweetsFromUsers(String inFilename, String outFilename, int n) throws IOException, TwitterException
	{
		BufferedReader in = new BufferedReader(new FileReader(new File(inFilename)));
		String line = null;
		List<String> usernames = new ArrayList<String>();
		while((line = in.readLine()) != null)
		{
			usernames.add(line);
			if (usernames.size() == n)
			{
				break;
			}
		}
		in.close();
		PrintWriter out = new PrintWriter(outFilename);
		int tweetsPerUser = n / usernames.size();
		
		for(int i = 0; i<usernames.size(); i++)
		{
			List<String> tweets = this.getUserTweetsText(usernames.get(i), tweetsPerUser);
			System.out.println(usernames.get(i) + " : " + tweets.size());
			for (String t: tweets)
			{
				out.println(t);
			}
		}
		out.close();
	}
	
	private void downloadData() throws IOException, TwitterException
	{
		this.downloadTweetsFromUsers("dev_usernames.txt", "dev_tweets.txt", 10000);
		this.downloadTweetsFromUsers("mgr_usernames.txt", "mgr_tweets.txt", 10000);
	}
	
	public static void main(String[] args) throws TwitterException, IOException
	{
		TwitterClient tc = new TwitterClient();
	}
}
