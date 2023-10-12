package milyoner;

import java.util.*;

public class Joker {
	//This class controls the lifelines
	
	private boolean doubleDip;
	private boolean fiftyPercent;
	private int doubleDipCount;
	
	public Joker(boolean doubleDip, boolean fiftyPercent, int doubleDipCount) {
		this.doubleDip = true;
		this.fiftyPercent = true;
		this.doubleDipCount = 0;
	}
	
	public boolean getDoubleDip() {
		return doubleDip;
	}
	public void setDoubleDip(boolean doubleDip) {
		this.doubleDip = doubleDip;
	}
	public boolean getFiftyPercent() {
		return fiftyPercent;
	}
	public void setFiftyPercent(boolean fiftyPercent) {
		this.fiftyPercent = fiftyPercent;
	}
	public int getDoubleDipCount() {
		return doubleDipCount;
	}
	public void setDoubleDipCount(int doubleDipCount) {
		this.doubleDipCount = doubleDipCount;
	}
	public void usingDoubleDip(Question question, char answer, int x, int y) {
		//For first answer after using double dip
		if(doubleDipCount == 0) {
			//first choosen option replaces with empty spaces 
			if(answer == 'A') {
				question.setOption1("                                                            ");
			}
			else if(answer == 'B') {
				question.setOption2("                                                            ");
			}
			else if(answer == 'C') {
				question.setOption3("                                                            ");
			}
			else if(answer == 'D') {
				question.setOption4("                                                            ");
			}	
			
			setDoubleDipCount(1);			
		}
	}
	public void usingFiftyPercent(Question question) {
		//Randomly deletes two option
		Random rnd = new Random();
		String[] wrongAnswers = new String[3];//Array for options which will be deleted
		
		
		
		switch(question.getCorrectAnswer().toUpperCase()) {
		//Filling array's elements
		case("A"):
			if(!question.getOption1().contains("                                                            "))
				wrongAnswers[0] = "B";
			if(!question.getOption3().contains("                                                            "))
				wrongAnswers[1] = "C";
			if(!question.getOption4().contains("                                                            "))
				wrongAnswers[2] = "D";
			break;
		case("B"):
			if(!question.getOption1().contains("                                                            "))
				wrongAnswers[0] = "A";
			if(!question.getOption3().contains("                                                            "))
				wrongAnswers[1] = "C";
			if(!question.getOption4().contains("                                                            "))
				wrongAnswers[2] = "D";
			break;
		case("C"):
			if(!question.getOption1().contains("                                                            "))
				wrongAnswers[0] = "A";
			if(!question.getOption2().contains("                                                            "))
				wrongAnswers[1] = "B";
			if(!question.getOption4().contains("                                                            "))
				wrongAnswers[2] = "D";
			break;
		case("D"):
			if(!question.getOption1().contains("                                                            "))
				wrongAnswers[0] = "A";
			if(!question.getOption2().contains("                                                            "))
				wrongAnswers[1] = "B";
			if(!question.getOption3().contains("                                                            "))
				wrongAnswers[2] = "C";
			break;
		}
		
		
		for(int j = 0; j < 2; j++) {
			//Deleting random two options
			int deletedChoices = rnd.nextInt(3); 
			while(wrongAnswers[deletedChoices] == null) {
				deletedChoices = rnd.nextInt(3); 
			}
			if(wrongAnswers[deletedChoices].equalsIgnoreCase("A")) {
				question.setOption1("                                                            ");

			}
			else if(wrongAnswers[deletedChoices].equalsIgnoreCase("B")) {
				question.setOption2("                                                            ");

			}
			else if(wrongAnswers[deletedChoices].equalsIgnoreCase("C")) {
				question.setOption3("                                                            ");
                
			}
			else if(wrongAnswers[deletedChoices].equalsIgnoreCase("D")) {
				question.setOption4("                                                            ");

			}
			wrongAnswers[deletedChoices] = null;
		}
	}
            
}