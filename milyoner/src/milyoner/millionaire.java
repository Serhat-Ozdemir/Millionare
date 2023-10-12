package milyoner;


import java.util.*;

public class millionaire {
	public static void main(String[] args) throws Exception {		
		int menu;
		int level = 1;
		boolean menu1 = false, menu2 = false, menu3 = false;
		Participant[] participants = null;
		String[] word_cloud_difficulty = { "", "", "", "", "" };
		Question[] questions = null;				
		StatisticCategory[] statisticCategory = null; 
		StatisticParticipant[] statisticParticipant = null;
		
		
		while (true) 
		{
			menu = Operations.menuPrinter(); //This function prints the menu

			if (menu == 1) {
				//User enter the question file
				Scanner scanner = new Scanner(System.in);
				System.out.print("Please enter a file name: ");
				questions = Operations.questionSetter("questions.txt");
				//Checking for misspellings with the spell check algorithm
				questions = Operations.spellChecker(questions);
				//Printing categories and difficulty levels with the number of questions
				Operations.printer(questions, word_cloud_difficulty);
				menu1 = true;
				scanner.close();
			} 
			else if (menu == 2) {
				//User enter the participants file
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter file name to load: ");
				participants = Operations.participantSetter("participants.txt");
				System.out.println("\n");
				menu2 = true;
				scanner.close();
			} 
			else if (menu == 3 && !(menu1 && menu2)) {
				//If user choose menu 3 without choosing menu 1 and 2
				System.out.println("Please be sure that you have visited menu-1 and menu-2!");
			} 
			else if (menu == 3 && menu1 && menu2) {
				// Random participant choose and gameplay
				Scanner scanner = new Scanner(System.in);				
				boolean canParticipantContinue = true, flag = true;
				while (flag) {
					level = 1;
					Joker usingJokers = new Joker(true, true, 0);
					Participant participant = Gameplay.randomParticipant(participants);
					if(participant == null)
						canParticipantContinue = false;
					else
						canParticipantContinue = true;
					while (level <= 5 && canParticipantContinue) {
						canParticipantContinue = Gameplay.game(participant, questions, usingJokers, word_cloud_difficulty, level);
						level++;
					}
					//Screen for after copetition
					flag = Gameplay.endGamePrint(participant);

				}
				menu3 = true;
				//Statistics are taken and stored
				Statistic.readStatistic(questions, participants);
				statisticCategory = Statistic.getStatisticCategory();
				statisticParticipant = Statistic.getStatisticParticipant();
				Statistic.writeStatistic();
				scanner.close();
			} 
			else if (menu == 4 && menu1 && menu2 && menu3) 
			{
				//printing the statistics
				Operations.statisticPrinter(statisticCategory, statisticParticipant);
			} 
			else if (menu == 5) 
			{
				//Exit
				System.out.println("Bye");
				System.exit(0);
			}
		}
	}
}
