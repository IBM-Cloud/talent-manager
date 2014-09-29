# Persona Fusion aka Talent Hotspot

project URL: http://personafusion.stage1.mybluemix.net/

GitLabs Repo: https://git.design.ibm.com/swelleck/persona_fusion/tree/master/personafusion

API Endpoints: 
* Search: api/search
..* http://personafusion.stage1.mybluemix.net/api/search?fname=Emory&lname=Wren
* Visualize: /api/viz
..* http://personafusion.stage1.mybluemix.net/api/viz?fname=Emory&lname=Wren
* List all People: /api/people
..* http://personafusion.stage1.mybluemix.net/api/people

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

## More info
There's more information here at our w3 communities page. 
Let us know in the comments or on our repo page if you have any other questions or curiousities. 
https://w3-connections.ibm.com/wikis/home?lang=en#!/wiki/Wccfe9fbe4af8_4fbc_84ab_d3971c0793dd/page/Persona%20Fusion%20-%20Project%20Wiki
