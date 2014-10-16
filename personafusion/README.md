# Persona Fusion aka Talent Hotspot

# About
## Meet Ivy
* She's a talent manager at a growing tech startup. 
* She's having trouble finding the right candidate based on:
..* technical skills
..* personal compatibility

> I wish I could clone my developer, Emory Wren -- having two guys like Emory working here would be amazing. 

But that's not possible. So what's the next best thing? 


** Talent Hotspot
A web application that allows you to search for candidates from a pool of applicants based on how closely they resemble one of your current employees. 
Talent Hotspot uses Watson's User Modeling API service to analyze a potential candidate's personality based on their answers to a questionnaie (completed upon application to Ivy's company.)

The application can issue queries such as, 
> "Find me a Developer like Craig Smith". 
Then search through all possible candidate and return a ranked list of candidates sorted by highest-to-lowest percentage of personality resemblance. 
From here, searches can be refined by including technical skills. 
> "Find me a Developer like Craig Smith, and knows Java, C and Python"

# Technologies
## Watson APIs
User Modeling API
* Rerieve personality traits
..* POST `systemu/api/v2/profile` 
* Visualize personality Traits
..* `systemu/api/v2/visualize`

## Bluemix
* [Liberty for Java Profile](https://ace.ng.bluemix.net/#/store/cloudOEPaneId=store&appTemplateGuid=javawebstarter)

## Other
* Angular.js
* external file manager for reading text files

## Pre-req's
* [Install the Eclipse EE](https://ibm.biz/hackathon-eclipse).
* [Install Java 1.7 JDK](https://ibm.biz/hackathon-java).
* [Install the cf command-line tool](https://ibm.biz/hackathon-cf).
* [Sign up for an IBM Bluemix account](http://bluemix.net).

