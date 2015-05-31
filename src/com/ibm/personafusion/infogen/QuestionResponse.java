package com.ibm.personafusion.infogen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionResponse {

	// Each complete set of response will probably range from 600-700 words.
	
	public static final int amountQuestion = 5; // Not used. Just something to keep in check.
	public static final int responsePerQuestion = 3; // Not used. Just something to keep in check.
	
	static String[][] responseList = new String[][] {
			// Question 1
			
			// What is the last technical or scientific concept
			// you learned on your own? What prompted you to learn it?
			// How did you teach yourself? 
			{
				"It's actually quite difficult to say, since I frequent a lot of science and discovery websites and videos. But if my memory serves me correctly, it was simply about physics, specifically about the Theory of Relativity by Einstein. "
				+ "I've been a great fanatic of science in general, but I've been intrigued by physics more than others, which tries to understand the world through math and other laws. The reason behind my prompted desire to understand Einstein's Theory of Relativity is because it gives me a great sense of satisfaction once I manage to see things through someone else' eyes. Though in this case, I was piqued by Einstein himself, who managed to create history, who was able to become famous not only to physicians, but also to many others who aren't even well-informed of the nature of physics. I wish to be able to see what he was seeing and by understanding his Theory of Relativity, I believed I took a small step towards that goal. "
				+ "As for how I taught myself, it was a simple matter of web surfing. Einstein's Theory of Relativity is already well-known (in fact, I heard of it from middle or high school but never truly in depth). So I searched on Google, went through Youtube videos and also, albeit very rarely, read about it from books when I frequent a bookstore. ",
				
				"I guess the last technical thing I learned was about coding. Coding in Java, in particular. "
				+ "There wasn't exactly a motivation for me to go learn Java on my own, though I guess I probably should be learning it out of the major benefits that I would get considering how popular it still is in the modern society. I wanted to pass a required course that I took which was necessary to graduate college. Although, I understood that it was going to be fast paced based on what I heard from others using Facebook and other social media for feedback on this course. It was simply that I never learned or used Java, when all my previous courses taught me C++ and never specifically requested for something other than C++. I was pretty satisfied until the professor in this class told us to use Java and didn't give us lectures on the language syntax. Rather, he was expecting us to learn Java on our own time and that really made me annoyed. "
				+ "As for how I managed to learn it, Java is a widely popular language, so looking it up on Google isn't that big of an issue. I just typed in asking for a Java tutorial and voila, there's a whole bunch of links that tries to introduce Java to beginners. I also had to install some other programs to test and play around with the language as well. ",
				
				"I have not learned anything technical or scientific on my own. Science just doesn't really interest me. "
				+ "I'm not saying that it's bad to learn. I would rather just look up on what fashion or trend is showing up next or what drama show should I try looking forward to. I'll admit that there's plenty of others that are certainly interested in technical or scientific stuff, but if so many are into researching, understanding and trying to innovate new stuff, then why should I bother? I doubt that having one more person would help speed up the progress of technology. "
				+ "I mean, I still use technology today. I have an iPhone. I'm using a computer laptop. I have a car. I'm sure I'll learn about the details of these technologies in the near future, but as for now, I'm relying on my family and friends to look them up for me and help me fix the issue. Why bother with all the complicated stuff when you can just go out, live life the way you want to, and have everyone else take care of your problems? "
				+ "So yeah, I haven't bothered learning anything technical or scientific on my own asides from what I know that was taught to me in the education systems. "
			}, 
			
			// Question 2
			
			// Congratulations! After a hard-fought campaign,
			// you are now President of the World. 
			// What is the first change that you would make? 
			{
				"I don't know if the change had to be realistic, but I'll be going with the assumption that it doesn't (otherwise, there isn't a whole lot that you can do). Plus, I'm the President of the World, so there shouldn't be objections, unless there's some sort of democratic system. "
				+ "The change that I would like to first make onto the world would be to abolish racism in general. I can understand that certain races may be more adapted at certain tasks and would encourage them to do what they can do best. However, there is no reason to hate them or force them to do something they don't want to do just because they're great at something. Everyone has something that they can do well, but they are all jealous of others who can do other things better than them. As someone else quoted, the grass is greener on the other side. "
				+ "I think by reducing the amount of racial hate, people can accept each other. Sure, conflicts arise and those can't be helped. But everyone needs to admit that they're not perfect, as much as they want to be. Once you can play to each one's preferences, strengths and weaknesses, everything will work out better. ",
				
				"As president of the world, the first thing I want to do is grant everyone some sort of superpower. I don't care how ridiculous it sounds. If I'm the president of the world, then I should be able to do something of this extent, right? "
				+ "I want to give everyone something new to their lives. Some people may be content with their everyday lives, but there are also many that want something to change, that want something new and fresh. That's where superpowers come in. People can choose to never use their powers, to abuse their powers, or to use their powers to protect. It would allow each person to reveal their true characteristics. Laws won't be able to stop them. Research will have to be done to either diminish or enhance those powers. It'll be a new world altogether, even if it may not be the greatest thing. "
				+ "I don't believe that as the president of the world, that I should force everyone to do as I say, but rather that everyone gets the ability to say what they feel and act on it. I don't mind being killed afterwards either. I'm just giving the world a lot more options and choices, though in the end, the outcomes are probably still the same. ",
				
				"I won't be making a change. The world does not need any changes from the president. "
				+ "The reason is straight-forward. No matter what kind of changes anyone makes, there's both positive and negative outcomes. No matter how you try to perceive your decision as a great decision, it can still be looked at from a different perspective depending on the person. It's for this very reason that no matter what reason, no one can be happy or satisfied for what change you are trying to make. "
				+ "I believe very strongly in the fact that there's complete balance between good and evil no matter what. Meaning that no matter how great some change may be, there would be an equally evil interpretation or meaning behind the very same change. In the end, people would be unhappy with all the negative consequences and as such, I as the president, will still be blamed for everything even though I would have tried my hardest to do the best positive thing for everyone. There will never be a time where everyone is completely willing to understand each other and so it is better to not make a change in case of a backlash. I know this is a depressing way of seeing things, but I don't think things should change at all. "
			},
			
			// Question 3
						
			// Tell me about a time when you had to deal with a difficult person.
			// How did you handle the situation?
			{
				"As a Resident Advisor, I had another RA who often sought me as a person to confide her complaints to and shared quite a bit of information about activities she'd engaged in that violated the rules. Although I did not mind being a resource for this person, I knew that I could not compromise my integrity or her residents' safety. Although she became very outraged and angry with me, I talked to her about the situation and and told her that I would have to tell my supervisor. She eventually understood my responsibility and why I had to come forward with information. She knew that what she had done was against the rules, but never realized before I talked to her that she had jeopardized her residents' safety.",
				
				"When I first began working at the YMCA, I was the youngest member of the staff. An older woman really knew the ropes of the place. When I first got there she barely acknowledged my presence, and through word of mouth I discovered that she thought that I was too young to successfully fulfill my duties because I was so inexperienced. She assumed I was immature. I did my job and took every opportunity to make a good impression. I was a very diligent worker and behaved in a highly professional manner at all times, learning quickly the best way to do things. After about two weeks of the silent treatment from her, she came up to me and told me how impressed she was with me. She told me that I had done an excellent job and was the fastest learner that she had ever seen. She apologized to me for ignoring me and took me under her wing and shared what she knew with me.",
				
				"I have participated in several groups throughout my academic career. Recently, I had to work with a group in my statistics class, and I had some personality clashes with one of the group members. However, I realized the importance of completing the assignment in a prompt and efficient manner. I made it a point to put my differences aside and complete my part, along with offering assistance to the other group members. As a result, we finished our assignment without any conflict."
			},
			
			// Question 4
			
			// How do you deal with conflict?
			{
				"I'm proactive. In other words, I monitor situations closely to prevent minor problems from escalating into major ones. In most cases, minor problems can be resolved by simply meeting individually with affected parties. It's not a good idea to ignore a problem in hopes that it will resolve itself. Some managers avoid confrontation, which frequently results in bigger problems that are more difficult to handle. Effective leaders are also aware of many factors, including jealously, individual differences, and other issues that create conflicts among employees. Therefore, effective managers must meet individually with feuding employees and suggest workable solutions.",
				
				"At a previous job, I was responsible for resolving a conflict between two team members who could no longer work effectively together. Their relationship began affecting the productivity of other employees. The first thing I did was separate them to calm the situation. I then proceeded to meet together with both parties to discuss the problem in a calm and controlled setting. I assumed control of the discussion since emotions frequently trump reason during disputes between two people and made it clear that a compromise must be reached. One of my main objectives during the meeting was to understand the perspectives of both parties without siding with either one. At first, this was difficult since each party presented their arguments without considering other perspectives. To counter this, I made it clear to each party that changes must be made since the status quo was unworkable. Shortly thereafter, we agreed to a workable solution. Before concluding the meeting, I emphasized that during future disagreements each party must act considerably and professionally and avoid getting emotional. After our meeting, work resumed as normal and the overall work atmosphere became more pleasant.",
				
				"I believe I am quite good at handling conflict. Working in retail and in the residence halls required that I make unpopular decisions at times, whether it was terminating an associate or taking judicial action on a resident. Often the person in conflict with me would be upset and sometimes physically outraged. I would always make sure that I fully explained the situation, the policies behind my decision, and why those policies exist. Usually by the end of the conversation, the person could see the other side of the situation."
			},
			
			// Question 5
			
			// Would you say you are good with dealing with high-pressure or stressful situations?
			{
				"Yes. My past experience as an Administrative Coordinator required me to deal with many serious situations since I held emergency on-call duties as a supervisor. One example was when I was called by a Resident Assistant to deal with an attempted suicide on her residence-hall floor. The situation required that I think clearly and quickly in this life-and-death situation. I had to weigh the many tasks that needed to be completed. I had to assign RAs to call 911, make sure that EMS could get into the locked building, while at the same time applying first aid, and ensuring that the rest of the residents on the floor were OK. I also had to make sure the privacy of the resident in need was respected. I basically prioritized and dealt with each task by its importance. I delegated responsibility to RAs for things that they were capable of handling because I could not physically be in many places at once. Once the resident was taken to the hospital, I was responsible for paperwork and follow up to make sure the staff members, residents, and the resident-in-need adjusted back to normal life. I know this is an extreme example not found in the financial consulting field; however, it shows just how well I can deal with extreme pressure.",
				
				"While directing a play, I was faced with numerous problems. The sets were not coming together; the performers were fighting and not working hard; the technical aspects of the play were far from complete, and in general, it was a mess. I was the stage manager, which means that I tell people when to go on stage and tell the crew when to bring pieces of the set on stage. So I organized everything and told people to do specific jobs and asked them in a firm yet positive manner. People began to have fun, and the production went on extremely well (all performances sold out). The play was regarded as one of the smoothest shows to have been produced by the group. ",
				
				"I keep an electronic hand-held organizer that I synchronize with a schedule on my computer. I keep track of each task in order of priority and due date. I use an electronic organizer because it is very portable and has an alarm to remind me of about what is due so I don't have to waste time by looking at my organizer every hour. I start with the projects with the closest due date and the highest priority. I take these tasks and then schedule times in my calendar for me to work on them to ensure I meet deadlines. I stay focused by going over my organizer each night before bed so I know immediately what I have accomplished and where I need to start the next day."
			}	
	};
	
	public static List<String> getResponseList() {
		List<String> responseL = new ArrayList<String>();
		Random rand = new Random();
		for(String[] resp : responseList) {
			responseL.add(resp[rand.nextInt(resp.length)]);
		}
		
		return responseL;
	}
	
	public static String convertToFullString(List<String> responseList) {
		String response = "";
		for(String resp : responseList) {
			response += resp;
		}
		
		return response;
	}
}
