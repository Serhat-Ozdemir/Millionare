package milyoner;

import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import javax.sound.sampled.*;


public class Gameplay {
	
	public static KeyListener klis; 
	public static int keypr;   // key pressed?
	public static int rkey;    // key   (for press/release)
	
	public static Participant randomParticipant(Participant[] participants) 
	{	
		Random rnd = new Random();
		int randomParticipant = 0, isParticipantsLeft =0;
		for(int i = 0;i< participants.length;i++)//Checks if there is participant to play.
			if(participants[i].getIsPlayed())
				isParticipantsLeft++;
		if(isParticipantsLeft == participants.length)//If there is no participant game ends. 
		{
			System.out.println("There is no more participants!! Thanks for playing <3");
			return null;
		}
		while(true) //Random participant choose until the find participant who did not play yet
		{
			randomParticipant = rnd.nextInt(participants.length);
			if(!participants[randomParticipant].getIsPlayed())
				break;
		}
		participants[randomParticipant].setIsPlayed(true);
		System.out.println(participants[randomParticipant].getName()+"   was choosen randomly");
		return participants[randomParticipant];//Returns participant to play
	}

	public static boolean game(Participant participant, Question[] questions, Joker usingJokers, String[] word_cloud_difficulty, int level) throws Exception//Function for gameplay
	{	
		File questionSound = new File("gamestart.wav");
		Clip clip = playSound(questionSound,"question");
		boolean canParticipantContinue = true;
		String[] wordsOfWordCloud;
		String wordChoice, answer = "";
		Question question = null;
		
		//Main game loop
		wordsOfWordCloud = Wordcloud.wordsOfWordCloud( word_cloud_difficulty[level-1], level);
		clip.start();
		Wordcloud.wordCloudPrint(wordsOfWordCloud);	 
	    wordChoice = Wordcloud.wordCloudCheck(wordsOfWordCloud);
	    clip.stop();
	    question = questionWordCloudMatch(questions,wordsOfWordCloud,usingJokers,word_cloud_difficulty,level,wordChoice);
	    questionPrint(participant, level, usingJokers, question);
	    answer = answerCheck(participant, question, usingJokers, level);
        question.setUseInfo(true);
        
        
		switch(level) //For update money, questions etc... istatistics according to levels
		{		
		case 1://For first question		    
		    if(answer.equalsIgnoreCase("true"))//If answered true 
		    {
                question.setIsTrueFalse("true");
		    	participant.setTrueAnswered(participant.getTrueAnswered()+1);
		    	participant.setMoneyEarned(20000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = true;
		    	
		    }
		    else if(answer.equalsIgnoreCase("false"))//If answered false 
		    {
                question.setIsTrueFalse("false");
		    	participant.setMoneyEarned(0);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    else if(answer.equalsIgnoreCase("leave"))//If decided to leave
		    {
		    	participant.setMoneyEarned(0);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    else if(answer.equalsIgnoreCase("timeisup"))//If could not answered
		    {
		    	participant.setMoneyEarned(0);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    break;
		case 2:
			if(answer.equalsIgnoreCase("true")) //If answered true
			{
                question.setIsTrueFalse("true");
				participant.setTrueAnswered(participant.getTrueAnswered()+1);
				participant.setMoneyEarned(100000);
				System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
				canParticipantContinue = true;
			}
			else if(answer.equalsIgnoreCase("false"))//If answered false 
			{
                question.setIsTrueFalse("false");
				participant.setMoneyEarned(0);
				System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
				canParticipantContinue = false;
			}
			else if(answer.equalsIgnoreCase("leave"))//If decided to leave
			{
		    	participant.setMoneyEarned(20000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
			else if(answer.equalsIgnoreCase("timeisup"))//If could not answered
			{
		    	participant.setMoneyEarned(0);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
			break;
		case 3:
			if(answer.equalsIgnoreCase("true")) //If answered true
		    {
                question.setIsTrueFalse("true");
				participant.setTrueAnswered(participant.getTrueAnswered()+1);
		    	participant.setMoneyEarned(250000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = true;
		    }
		    else if(answer.equalsIgnoreCase("false"))//If answered false
		    {
                question.setIsTrueFalse("false");
		    	participant.setMoneyEarned(100000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    else if(answer.equalsIgnoreCase("leave"))//If decided to leave
		    {
		    	participant.setMoneyEarned(100000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    else if(answer.equalsIgnoreCase("timeisup"))//If could not answered
		    {
		    	participant.setMoneyEarned(0);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    break;
		case 4:
			if(answer.equalsIgnoreCase("true"))//If answered true
		    {
                question.setIsTrueFalse("true");
				participant.setTrueAnswered(participant.getTrueAnswered()+1);
		    	participant.setMoneyEarned(500000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = true;
		    }
		    else if(answer.equalsIgnoreCase("false"))//If answered false 
		    {
                question.setIsTrueFalse("false");
		    	participant.setMoneyEarned(100000);
		    	System.out.println(participant.getName() +" : $"+ participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    else if(answer.equalsIgnoreCase("leave"))//If decided to leave
		    {
		    	participant.setMoneyEarned(250000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    else if(answer.equalsIgnoreCase("timeisup"))//If could not answered
		    {
		    	participant.setMoneyEarned(0);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    break;
		case 5:
			if(answer.equalsIgnoreCase("true"))//If answered true 
		    {
                question.setIsTrueFalse("true");
				participant.setTrueAnswered(participant.getTrueAnswered()+1);
		    	participant.setMoneyEarned(1000000);
		    	System.out.println("Congratulations you won the top price!!! \n" + participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = true;
		    }
		    else if(answer.equalsIgnoreCase("false"))//If answered false 
		    {
                question.setIsTrueFalse("false");
		    	participant.setMoneyEarned(500000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    else if(answer.equalsIgnoreCase("leave"))//If decided to leave
		    {
		    	participant.setMoneyEarned(500000);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    
		    else if(answer.equalsIgnoreCase("timeisup"))//If could not answered
		    {
		    	participant.setMoneyEarned(0);
		    	System.out.println(participant.getName() +" : $" + participant.getMoneyEarned());
		    	canParticipantContinue = false;
		    }
		    break;
			
		}
		return canParticipantContinue;
	}

	public static Question questionWordCloudMatch(Question[] questions, String[] wordsOfWordcloud, Joker usingJokers,String[] word_cloud_difficulty,int level, String wordChoice) throws Exception {
        //Finds quesition which is choosen from wordcloud
		boolean flag = true;
        String tempstr = "";//For hold question
        Question question = null;//For return question
        
        for(int i = 0; i<questions.length;i++)//Looks every question from questions array
        {
            if(!flag )
                break;
            else if(!questions[i].getUseInfo()) //Takes question if it was not used
            {
            	tempstr = questions[i].getQuestion().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", " ");//Clears question from its punctuations         
                 while(tempstr.contains("  ")) 
                 {
                	 tempstr = tempstr.replaceAll("  ", " ");//Clears question from its extra spaces  
                 }
                 String[] wordsOfQuestion = tempstr.split(" ");//Splits question's word into an array
                 for(int j =0; j< wordsOfQuestion.length;j++) 
                 {
                	 if(wordsOfQuestion[j].equalsIgnoreCase(wordChoice))//If question's word matches with choosen word 
                     {
                		 question = questions[i];//For return question
                		 word_cloud_difficulty[level-1] = word_cloud_difficulty[level-1].replaceAll(question.getQuestion(), " ");  //Deletes used question                      
                         flag=false;//For break
                         break;
                     }
                          
                 }
            }                  
        }
        return question;
    }

	public static void questionPrint(Participant participant, int level, Joker usingJokers, Question question)//Function for printing question
	{
		//Prints game screen
		if(usingJokers.getDoubleDip() && usingJokers.getFiftyPercent())
			System.out.println("     ------------------------------------------");
		else if((usingJokers.getDoubleDip() && !usingJokers.getFiftyPercent()) || (!usingJokers.getDoubleDip() && usingJokers.getFiftyPercent()))
			System.out.println("     -------------------------------------------");
		else if(!usingJokers.getDoubleDip() && !usingJokers.getFiftyPercent())
			System.out.println("     --------------------------------------------");
        System.out.println("    | Double Dip(Z) = " + usingJokers.getDoubleDip() + "    50%(X) = "  + usingJokers.getFiftyPercent() +"    |");
        if(usingJokers.getDoubleDip() && usingJokers.getFiftyPercent()) {
            System.out.println(String.format("%4s|%17s %s%-21d|", " ",participant.getName(),"  $", participant.getMoneyEarned()));
			System.out.println("     ------------------------------------------");
        }
		else if((usingJokers.getDoubleDip() && !usingJokers.getFiftyPercent()) || (!usingJokers.getDoubleDip() && usingJokers.getFiftyPercent())) {
            System.out.println(String.format("%4s|%17s %s%-22d|", " ",participant.getName(),"  $", participant.getMoneyEarned()));
			System.out.println("     -------------------------------------------");
		}
		else if(!usingJokers.getDoubleDip() && !usingJokers.getFiftyPercent()) {
            System.out.println(String.format("%4s|%17s %s%-23d|", " ",participant.getName(),"  $", participant.getMoneyEarned()));
			System.out.println("     --------------------------------------------");
		}
        System.out.println("Q" + level + ": " + question.getQuestion());
        System.out.println("  A:" + question.getOption1());
        System.out.println("  B:" + question.getOption2());
        System.out.println("  C:" + question.getOption3());
        System.out.println("  D:" + question.getOption4());
        
        
	}
 
   public static String answerCheck(Participant participant, Question question, Joker usingJokers, int level) throws Exception {
	   //Checks the answer and decides what to do	
	   enigma.console.Console cn = Operations.cn;
       	String questionLevelSound = "question"+String.valueOf(level)+".wav";
       	File questionSound = new File(questionLevelSound);
   		Clip clip = playSound(questionSound,"question");
	   
	   klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      cn.getTextWindow().addKeyListener(klis);
	      klis=new KeyListener() {
	          public void keyTyped(KeyEvent e) {}
	          public void keyPressed(KeyEvent e) {
	             if(keypr==0) {
	                keypr=1;
	                rkey=e.getKeyCode();
	             }
	          }
	          public void keyReleased(KeyEvent e) {}
	       };
	       
	       
    	boolean flag = true;//Game continue condition
        String answer = "";//For statistics
        File sound = new File("");//For sound files
        int x = cn.getTextWindow().getCursorX();
        int y = cn.getTextWindow().getCursorY();
        keypr = 0;
        int timer = 33, time =20;
        while(flag) {
        	if(timer == 33) {
        		
        		Operations.secondPrinter(time);//Prints second
        		cn.getTextWindow().setCursorPosition(x, y - 8);//For game screen
        		time --;
        		timer = 0;
        		
        	}
        	if(time == -1) {//Checks the time
    			cn.getTextWindow().setCursorPosition(x, y);
    			sound = new File("timeisup.wav");
    			playSound(sound,"answer");
        		System.out.println("\nTime is up!!");            		
        		flag = false;
        		answer = "timeisup";
        	}
    		
        	
        	else if(keypr ==1) {//If key pressed     	
            	
        		sound = new File("correct.wav");
        		
        		if(rkey == KeyEvent.VK_E) {
                	flag = false;
                	answer = "leave";           	
                }
        		 //for correct answer with doubledip
                else if(!usingJokers.getDoubleDip() && question.getCorrectAnswer().charAt(0) == (char)rkey) 
                { //Updates question and flag and double dip at each answer
                	cn.getTextWindow().setCursorPosition(x, y);//Sets cursor's position
                	answerWait(question, (char) rkey);
                	clip.stop();
                	playSound(sound,"answer");
                    System.out.println("\nCorrect Answer! ");
                    flag = false;
                    answer = "true";
                    usingJokers.setDoubleDipCount(1);//Updates douple dip          
                }
                //for correct answer
                else if(question.getCorrectAnswer().charAt(0) == (char)rkey) {
                	cn.getTextWindow().setCursorPosition(x, y);
                	answerWait(question, (char) rkey);
                	clip.stop();//Stops sound
                	playSound(sound,"answer");
                    System.out.println("\nCorrect Answer! ");
                    flag = false;
                    answer = "true";
                }            
                //for using double dip
                else if((char)rkey == 'Z') {
                	if(usingJokers.getDoubleDip() == false) {
                	}
                	else {
                        usingJokers.setDoubleDip(false);
                        cn.getTextWindow().setCursorPosition(x, y - 17);
                        questionPrint(participant, level, usingJokers, question);
                        cn.getTextWindow().setCursorPosition(x, y - 8);
                	}

                }
                //for using 50%
                else if((char)rkey == 'X') {
                	if(usingJokers.getFiftyPercent() == false) {
                	}
                	else {
                		usingJokers.setFiftyPercent(false);
                        usingJokers.usingFiftyPercent(question);  
                        cn.getTextWindow().setCursorPosition(x, y - 17);
                        questionPrint(participant, level, usingJokers, question);
                        cn.getTextWindow().setCursorPosition(x, y - 8);
                	}
                    
                }
                //for wrong answers
                else if((((char)rkey == 'A' && !question.getOption1().equalsIgnoreCase("                                                            ")) || ((char)rkey == 'B'&& !question.getOption2().equalsIgnoreCase("                                                            ")) || 
                		((char)rkey == 'C' && !question.getOption3().equalsIgnoreCase("                                                            "))|| ((char)rkey == 'D'&& !question.getOption4().equalsIgnoreCase("                                                            "))) && (question.getCorrectAnswer().charAt(0) != (char)rkey)){
                	sound = new File("wrong.wav");       
                    if(!usingJokers.getDoubleDip() && usingJokers.getDoubleDipCount() == 0) //First wrong with double dip
                    {
                    	cn.getTextWindow().setCursorPosition(x, y);
                    	answerWait(question, (char) rkey);
                    	playSound(sound,"answer");
                    	usingJokers.usingDoubleDip(question, (char)rkey, x, y);                    	
                    	cn.getTextWindow().setCursorPosition(x, y - 17);
                        questionPrint(participant, level, usingJokers, question);
                        cn.getTextWindow().setCursorPosition(x, y - 8);
                    }
                    else if(!usingJokers.getDoubleDip() && usingJokers.getDoubleDipCount() == 1 ) //Second wrong with double dip
                    {
                    	cn.getTextWindow().setCursorPosition(x, y); 
                    	answerWait(question, (char) rkey);  
                    	clip.stop();
                    	playSound(sound,"answer");
                    	System.out.println("\nWrong answer, GAME OVER!");
                        flag = false;
                        answer = "false";                       
                    }
                    else if(usingJokers.getDoubleDip() && usingJokers.getFiftyPercent()) //Wrong answers without jokers
                    {
                    	cn.getTextWindow().setCursorPosition(x, y); 
                    	answerWait(question, (char) rkey);   
                    	clip.stop();
                    	playSound(sound,"answer");
                        System.out.println("\nWrong answer, GAME OVER!");
                        flag = false;
                        answer = "false";
                    }
                        
                    else if(!usingJokers.getFiftyPercent()) //Wrong answer with fifty percent
                    {
                    	cn.getTextWindow().setCursorPosition(x, y); 
                    	clip.stop();
                    	answerWait(question, (char) rkey);
                    	playSound(sound,"answer");
                        System.out.println("\nWrong answer, GAME OVER!");
                        flag = false;
                        answer = "false";
                    }                                       
                }

            	keypr =0;
            }

            Thread.sleep(20);
            timer++;
        }
        return answer;
    }
 
   public static boolean endGamePrint(Participant participant) throws InterruptedException 
    {
	   //Screen for after copetition
	   enigma.console.Console cn = Operations.cn;
	   int x  =cn.getTextWindow().getCursorX();
	   int y  =cn.getTextWindow().getCursorY();
	   klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      cn.getTextWindow().addKeyListener(klis);
	      klis=new KeyListener() {
	          public void keyTyped(KeyEvent e) {}
	          public void keyPressed(KeyEvent e) {
	             if(keypr==0) {
	                keypr=1;
	                rkey=e.getKeyCode();
	             }
	          }
	          public void keyReleased(KeyEvent e) {}
	       };
    	while(true) 
    	{
    		if(participant == null) //If there is no participants
			{
				return false;
			}
    		if(keypr ==1) {
    			cn.getTextWindow().setCursorPosition(x, y);
    			if(rkey == KeyEvent.VK_Y && participant != null) //If there is participant and user wants to continue
    			{
    				keypr = 0;
    				return true;
    			}
    			keypr = 0;
    			return false;
    			
    		}
    		//Prints an end game screen
        	System.out.println(" - - - - - - - - - - - - - - - -\n"
			          +"|       Congratulations         |");
			System.out.println(String.format("|%13s %s%-16d|",participant.getName(), "$", participant.getMoneyEarned()));
			System.out.print("  - - - - - - - - - - - - - - - "
				      +"\nPress y to play again"
				      +"\nPress any key to exit");

        	cn.getTextWindow().setCursorPosition(x, y-5);
        	Thread.sleep(500);
        	System.out.println("  - - - - - - - - - - - - - - - \n"
	          		  +"|       Congratulations         |");
	        System.out.println(String.format("|%13s %s%-16d|",participant.getName(), "$", participant.getMoneyEarned()));
	        System.out.print(" - - - - - - - - - - - - - - - -"
	        		  +"\nPress y to play again"
			          +"\nPress any key to exit");
	        cn.getTextWindow().setCursorPosition(x, y-5);
        	Thread.sleep(500);        	
    	}
    	

    }
   public static void answerWait(Question question, char rkey) throws Exception {
	   enigma.console.Console cn = Operations.cn;
	   int x  =cn.getTextWindow().getCursorX();
	   int y  =cn.getTextWindow().getCursorY();
	   cn.getTextWindow().setCursorPosition(x, y);
	   int loop =0;
	   switch(rkey)
	   {
	   		//Deletes visually answer after player answered according to given answer with set cursors
		   case 'A':
			   while(loop < 4) {
				   cn.getTextWindow().setCursorPosition(x, y-12);
				   System.out.println("                                                             ");
				   Thread.sleep(300);
				   cn.getTextWindow().setCursorPosition(x, y-12);
				   System.out.println("  A:" + question.getOption1());
				   Thread.sleep(300);
				   
				   loop++;
			   }
			   cn.getTextWindow().setCursorPosition(x, y);
			   break;
		   case 'B':
			   while(loop < 4) {
				   cn.getTextWindow().setCursorPosition(x, y-11);
				   System.out.println("                                                             ");
				   Thread.sleep(300);
				   cn.getTextWindow().setCursorPosition(x, y-11);
				   System.out.println("  B:" + question.getOption2());
				   Thread.sleep(300);

				   
				   loop++;
			   }
			   cn.getTextWindow().setCursorPosition(x, y);
			   break;
		   case 'C':
			   while(loop < 4) {
				   cn.getTextWindow().setCursorPosition(x, y-10);
				   System.out.println("                                                             ");
				   Thread.sleep(300);
				   cn.getTextWindow().setCursorPosition(x, y-10);
				   System.out.println("  C:" + question.getOption3());
				   Thread.sleep(300);

				   
				   loop++;
			   }
			   cn.getTextWindow().setCursorPosition(x, y);
			   break;
		   case 'D':
			   while(loop < 4) {
				   cn.getTextWindow().setCursorPosition(x, y-9);
				   System.out.println("                                                             ");
				   Thread.sleep(300);
				   cn.getTextWindow().setCursorPosition(x, y-9);
				   System.out.println("  D:" + question.getOption4());
				   Thread.sleep(300);

				   
				   loop++;
			   }
			   cn.getTextWindow().setCursorPosition(x, y);
			   break;
	   }
   }
   public static Clip playSound(File Sound,String answerOrQuestion) throws Exception {
	   //Function for play sounds
	   Clip clip = AudioSystem.getClip();
	   clip.open(AudioSystem.getAudioInputStream(Sound));
	   if(answerOrQuestion.equalsIgnoreCase("answer")) {
		   clip.start();
		   Thread.sleep(clip.getMicrosecondLength()/1000);
	   }
	   else if(answerOrQuestion.equalsIgnoreCase("question")) 
			   clip.start();
	   
	   return clip;
   }

}