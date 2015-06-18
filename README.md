# Talent Manager

To Use
================================================================================

```
cf create-service cloudantNoSQLDB Shared talent-manager-db
cf create-service personality_insights "IBM Watson Personality Insights Monthly Plan" personality-insights-talent-manager
cf push myappname
```

Replace myapp name with the name of your app (ex. talent-manager)

or click the button below

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy)


#Architecture Diagram
![alt tag](https://raw.github.com/IBM-Bluemix/talent-manager/master/talent-manager-architecutre-diagram.png)


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
* Visualize personality Traits

## Cloudant NoSQL Database
* Store personality profiles

## Bluemix
* [Liberty for Java Profile](https://ace.ng.bluemix.net/#/store/cloudOEPaneId=store&appTemplateGuid=javawebstarter)

## Other
* Angular.js
* external file manager for reading text files

# Pre-req's for local development
* [Install the Eclipse EE](https://eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2).
* [Install Java 1.7 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
* [Install the cf command-line tool](https://www.ng.bluemix.net/docs/#starters/install_cli.html).
* [Sign up for an IBM Bluemix account](http://bluemix.net).

