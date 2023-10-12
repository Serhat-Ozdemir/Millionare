package milyoner;

import java.io.*;
import java.util.*;
import enigma.core.Enigma;

public class Operations {
	
	public static enigma.console.Console cn = Enigma.getConsole("Game", 150, 50, 20, 2);
	
	public static Locale ENG = Locale.forLanguageTag("en");
	
	public static int menuPrinter() 
	{
		//prints the menu and takes input from the user for select the relevant part of the menu
		Scanner scannerMenu = new Scanner(System.in);
		int menu;
		System.out.println // menu
		("***** Menu *****\r\n" + "1.Load questions\r\n" + "2.Load participants\r\n" + "3.Start competition \r\n"
				+ "4.Show statistics\r\n" + "5.Exit\r\n");

		do {
			System.out.print("Please select an option: ");
			menu = scannerMenu.nextInt();
		} while (menu < 1 || menu > 5);

		scannerMenu.close();
		return menu;
	}
	
	public static boolean arrChecker(String[] arr) 
	{
		//It checks whether the components in the read files are in desired format.
		for(int i = 0; i < arr.length; i++) 
		{
			if(arr[i].equals(""))
				return false;
		}
		return true;
	}
	
	public static Question[] questionSetter(String fileName) throws IOException {
		//It assigns the questions read from the question file to the question constructor.
		BufferedReader objReader_1 = new BufferedReader(new FileReader(fileName));
		BufferedReader objReader_2 = new BufferedReader(new FileReader(fileName));

		int counter = 0, i = 0;
		String sentence;
		while ((sentence = objReader_1.readLine()) != null) //(strCurrentLine = objReader.readLine()) != null
		{
			String[] tempArr = sentence.trim().split("#");
			if(tempArr.length == 8 && arrChecker(tempArr))
				counter++;
		}			

		Question[] data = new Question[counter];

		while (counter > i) 
		{
			String[] temp = objReader_2.readLine().trim().split("#");
			if(temp.length == 8 && arrChecker(temp))
			{
				data[i] = new Question(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5],
						temp[6], Integer.parseInt(temp[7].replaceAll("[^0-9]","")), "");
				i++;
			}			
		}

		objReader_1.close();
		objReader_2.close();

		System.out.println("File loaded!");
		return data;
	}

	public static Participant[] participantSetter(String fileName) throws IOException {
		//It assigns the information about participants read from the participants file to the participants constructor.
		BufferedReader objReader_1 = new BufferedReader(new FileReader(fileName));
		BufferedReader objReader_2 = new BufferedReader(new FileReader(fileName));

		int counter = 0, i = 0;
		String sentence;
		while ((sentence = objReader_1.readLine()) != null) //(strCurrentLine = objReader.readLine()) != null
		{
			String[] temp = sentence.trim().split("#");
			if(temp.length == 4 && arrChecker(temp)) 
			{
				String[] tempAddress = temp[3].trim().split(";");
				String[] tempBirthDate = temp[1].trim().split("\\.");
				if(tempAddress.length == 5 && arrChecker(tempAddress) && tempBirthDate.length == 3 && arrChecker(tempBirthDate)) 
				{
					counter++;
				}
			}
		}	

		Participant[] data = new Participant[counter];

		while (counter > i) 
		{
			String[] temp = objReader_2.readLine().trim().split("#");
			
			if(temp.length == 4 && arrChecker(temp)) 
			{
				String[] tempAddress = temp[3].trim().split(";");
				String[] tempBirthDate = temp[1].trim().split("\\.");
				if(tempAddress.length == 5 && arrChecker(tempAddress) && tempBirthDate.length == 3 && arrChecker(tempBirthDate)) 
				{
					data[i] = new Participant(temp[0], birthDateSetter(tempBirthDate), temp[2],
							adressSetter(tempAddress), 0, false, 0);
					i++;
				}
				
			}
			
		}

		objReader_1.close();
		objReader_2.close();
		System.out.println("File loaded\n\n");

		return data;
	}

	public static Address adressSetter(String[] arr) {
		//It assigns the address information to the address constructor.
		Address result = new Address(arr[4], arr[3], arr[2], arr[1], arr[0]);
		return result;
	}

	public static BirthDate birthDateSetter(String[] arr) {
		//It assigns the birth date information of participants to the birthdate constructor.
		BirthDate temp = new BirthDate(Integer.parseInt(arr[0].replaceAll("[^0-9]","")), 
				Integer.parseInt(arr[1].replaceAll("[^0-9]","")), Integer.parseInt(arr[2].replaceAll("[^0-9]","")),
				ageCalculator(arr));
		return temp;
	}

	@SuppressWarnings("deprecation")
	public static int ageCalculator(String[] partdate) {
		// It calculates the age of the participants.
		Date date = new Date();
		int age = 1900 + date.getYear() - Integer.parseInt(partdate[2].replaceAll("[^0-9]",""));
		if (date.getMonth() + 1 > Integer.parseInt(partdate[1].replaceAll("[^0-9]","")))
			return age;
		else if (date.getMonth() + 1 < Integer.parseInt(partdate[1].replaceAll("[^0-9]","")))
			return age - 1;
		else {
			if (date.getDate() > Integer.parseInt(partdate[0].replaceAll("[^0-9]","")))
				return age;
			else if (date.getDate() < Integer.parseInt(partdate[0].replaceAll("[^0-9]","")))
				return age - 1;
			else
				return age;
		}
	}

	public static String[] textReader(String filename) throws IOException {
		//This is the file reading function of the project.
		BufferedReader objReader_1 = new BufferedReader(new FileReader(filename));
		BufferedReader objReader_2 = new BufferedReader(new FileReader(filename));

		int counter = 0;
		String sentence;
		while ((sentence = objReader_1.readLine()) != null) 
		{
			if(!sentence.equals(""))
				counter++;
		}			
		String[] data = new String[counter];
		sentence = "";
		int i = 0;
		while (counter > i) 
		{			
			sentence = objReader_2.readLine();
			if(!sentence.equals("")) 
			{
				data[i] = sentence;
				i++;
			}			
		}
			
		objReader_1.close();
		objReader_2.close();

		Arrays.sort(data);
		return data;
	}


	public static int binarySearch(String[] arr, String word) {
		int l = 0, r = arr.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			int res = word.compareTo(arr[m]);

			// Check if x is present at mid
			if (res == 0)
				return res;

			// If x greater, ignore left half
			if (res > 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}
		return -1;
	}
	
	public static Question[] spellChecker(Question[] questions) throws IOException {
		Dictionary.setDictionary();
		String choice;
		int difference_1, difference_2, counter;
		Scanner scannerSpellChecker = new Scanner(System.in);

		for (int i = 0; i < questions.length; i++)// To change questions
		{
			// We are creating two different questionsays to not have upper-lower word
			// problems
			String[] temp_words = questions[i].getQuestion().replaceAll("[^\\p{L}]", " ").replaceAll("  ", " ")
					.split(" ");
			Arrays.sort(temp_words);
			for (String word : temp_words)// searching word in questionsay
			{
				//Checks if dictionary contains the word
				if (binarySearch(Dictionary.getDictionary(), word.toLowerCase(ENG)) == -1 && !word.isBlank())
				{
					choice = "";

					// Asks user if he/she wants to change the word
					while (!("n".equalsIgnoreCase(choice) || "y".equalsIgnoreCase(choice))) {
						System.out.println(
								"Would you like to change this word: " + word.toLowerCase(ENG) + " (Y:yes | N:no)");
						choice = scannerSpellChecker.next();
					}

					// If user says no
					if ("n".equalsIgnoreCase(choice))
						continue;

					// If user says yes this for loop creates temporary variable correct in
					// dictionary
					for (String correct : Dictionary.getDicByLen(word.length())) {
						difference_1 = difference_2 = counter = 0;
						for (int j = 0; j < word.length(); j++) {
							if (word.toLowerCase(ENG).charAt(j) != correct.charAt(j)){
								// Count how many letters are different
								counter++;
							}
						}
						if (counter == 1) // If one letter is different
						{
							choice = "";

							while (!("n".equalsIgnoreCase(choice) || "y".equalsIgnoreCase(choice))) {
								System.out.println("Would you like to change " + word.toLowerCase(ENG) + " to " + correct
										+ " (Y:yes | N:no)");
								choice = scannerSpellChecker.next();
							}

							if ("y".equalsIgnoreCase(choice)) {
								questions[i].setQuestion(questions[i].getQuestion().replace(word, correct));
								break;
							}
						} else if (counter == 2) // If two letters are different
						{
							choice = "";

							for (int j = 0; j < word.length(); j++) {
								difference_1 += (int) word.charAt(j);
								difference_2 += (int) correct.charAt(j);
							}

							if (difference_1 == difference_2) {
								while (!("n".equalsIgnoreCase(choice) || "y".equalsIgnoreCase(choice))) {
									System.out.println(
											"Would you like to change " + word + " to " + correct + " (Y:yes | N:no)");
									choice = scannerSpellChecker.next();
								}

								if ("y".equalsIgnoreCase(choice)) {
									questions[i].setQuestion(questions[i].getQuestion().replace(
											temp_words[binarySearch(temp_words, word.toLowerCase(ENG))], correct));
									break;
								}
							}
						}

					}
				}
			}
		}

		scannerSpellChecker.close();
		System.out.println();
		return questions;
	}

	
	public static void statisticPrinter(StatisticCategory[] statisticCategory, StatisticParticipant[] statisticParticipant) 
	{
		//At the end of the competition it calculates and prints statistics.
		System.out.println("\n");
	
		//most successful participant
		String mostSuccesful = "";
		int maxCorrect = 0;
		for(int i = 0; i < statisticParticipant.length; i++) 
			if(statisticParticipant[i].getCorrect() > maxCorrect) 
			{
				maxCorrect = statisticParticipant[i].getCorrect();
				mostSuccesful = statisticParticipant[i].getName();
			}
		
		//Max participant from cities
		String temp = "";
		for(int i = 0; i < statisticParticipant.length; i++) 
			if(!temp.contains(statisticParticipant[i].getCity()))
				temp += statisticParticipant[i].getCity() + "#";
		String[] cityNames = temp.trim().split("#");
		int[] cityCounter = new int[cityNames.length];
		for(int i = 0; i < statisticParticipant.length; i++)
			for(int j = 0; j < cityNames.length; j++) 
				if(cityNames[j].equals(statisticParticipant[i].getCity()))
					cityCounter[j]++;
		int maxCityNum = 0;
		int cityIndex = -1;
		for(int i = 0; i < cityCounter.length; i++) 
			if(cityCounter[i] > maxCityNum) 
			{
				maxCityNum = cityCounter[i];
				cityIndex = i;
			}
		String mostCity = cityNames[cityIndex];
		
		//ages and correct answers
		int[] ages = new int[3];
		int[] correctAges = new int[3];
		for(int i = 0; i < statisticParticipant.length; i++) 
		{
			if(statisticParticipant[i].getAge() > 50) 
			{
				ages[2]++;
				correctAges[2] += statisticParticipant[i].getCorrect();
			}
			else if(statisticParticipant[i].getAge() <= 30) 
			{
				ages[0]++;
				correctAges[0] += statisticParticipant[i].getCorrect();
			}
			else 
			{
				ages[1]++;
				correctAges[1] += statisticParticipant[i].getCorrect();
			}
		}
		
		//most correct category and false category
		String mostCCategory = "", mostFCategory = "";
		int maxCCategory = 0, maxFCategory = 0;
		for(int i = 0; i < statisticCategory.length; i++) 
		{
			if(statisticCategory[i].getNumcorrect() > maxCCategory) 
			{
				maxCCategory = statisticCategory[i].getNumcorrect();
				mostCCategory = statisticCategory[i].getCategory();
			}
			if(statisticCategory[i].getNumfalse() > maxFCategory) 
			{
				maxFCategory = statisticCategory[i].getNumfalse();
				mostFCategory = statisticCategory[i].getCategory();
			}
		}
		
		System.out.println("•	The most successful contestant: " + mostSuccesful);
		System.out.println("•	The category with the most correctly answered: " + mostCCategory);
		System.out.println("•	The category with the most badly answered: " + mostFCategory);
		System.out.println("•	The city with the highest number of participants: " + mostCity);
		System.out.println("•	On average, how many questions did contestants in each age group answer correctly: ");
		System.out.print("•	Age<=30 | " +  (float)(correctAges[0] / ages[0]) + " | ");
		System.out.print("30<Age<=5 | " + (float)(correctAges[1] / ages[1]) + " | ");
		System.out.println("Age>50 | " + (float)(correctAges[1] / ages[1]));
		System.out.println("\n");
			
	}

	

	public static void printer(Question[] questions, String[] word_cloud_difficulty)
    {
        //This function prints difficulty level and categories of questions.
        String category = "";
     
        int counter = 0;
        //Operation of categorizing
        for(int i = 0; i < questions.length; i++) 
            if(!category.contains(questions[i].getCategory()))
                category += questions[i].getCategory() + "#";    
        String[] categories = category.split("#");        
        
                                
        System.out.println("\n\nCategory        The number of questions");
        //Calculating the same categories
        for(int i = 0; i < categories.length; i++) 
        {
            for(int j = 0; j < questions.length; j++) 
                if(categories[i].contains(questions[j].getCategory()))
                    counter++;
            System.out.println(String.format("%-12s %,15d", categories[i], counter));                
            counter = 0;
        }

         int[] difficulty = new int[5];
         	//Operation of separating difficulty levels.
            for(int i = 0; i < questions.length; i++) 
                difficulty[questions[i].getDifficult() - 1]++;
            System.out.println( "\n\nDifficulty level    The number of questions \r\n");
            //Operation of counting questions of the same difficulty level.
            for (int i = 0; i < 5; i++) 
                System.out.println("\t\t" + (i + 1) + "\t\t\t\t\t  " + difficulty[i]);
            System.out.println("\n");
            
            
            for(int i = 0; i < questions.length;i++) {
                switch(questions[i].getDifficult()) {
                    case(1):
                        difficulty[0]++;
                        word_cloud_difficulty[0]+=questions[i].getQuestion() + " ";
                        break;
                    case(2):
                        difficulty[1]++;
                        word_cloud_difficulty[1]+=questions[i].getQuestion() + " ";
                        break;
                    case(3):
                        difficulty[2]++;
                        word_cloud_difficulty[2]+=questions[i].getQuestion() + " ";
                        break;
                    case(4):
                        difficulty[3]++;
                        word_cloud_difficulty[3]+=questions[i].getQuestion() + " ";
                        break;
                    case(5):
                        difficulty[4]++;
                        word_cloud_difficulty[4]+=questions[i].getQuestion() + " ";
                        break;                        
                }
            }
    }

	
	
	public static void secondPrinter(int second) {
		//Operation of printing seconds in digital format.
		System.out.println(
				  "                                                                                           \n"
				+ "                                                                                           \n"
				+ "                                                                                           \n"
				+ "                                                                                           \n"
				+ "                                                                                           \n"
				+ "                                                                                           \n"
				+ "                                                                                           \n");

		int x = cn.getTextWindow().getCursorX();
		int y = cn.getTextWindow().getCursorY();
		y -= 7;
		cn.getTextWindow().setCursorPosition(x, y);

		switch (second) // " " " ~~~~~"
		{
		case 20: {
			System.out.println(
					  "                                                   ___   ___  \r\n"
					+ "                                                  |__ \\ / _ \\ \r\n"
					+ "                                            ~~~~~    ) | | | |  ~~~~~\r\n"
					+ "                                            ~~~~~   / /| | | |  ~~~~~\r\n"
					+ "                                                   / /_| |_| |\r\n"
					+ "                                                  |____|\\___/ \r\n");
			break;
		}
		case 19: {
			System.out.println(
					  "                                                   __  ___  \r\n"
					+ "                                                  /_ |/ _ \\ \r\n"
					+ "                                            ~~~~~  | | (_) |  ~~~~~\r\n"
					+ "                                            ~~~~~  | |\\__, |  ~~~~~\r\n"
					+ "                                                   | |  / / \r\n"
					+ "                                                   |_| /_/  \r\n");
			break;
		}
		case 18: {
			System.out.println(
					  "                                                   __  ___  \r\n"
					+ "                                                  /_ |/ _ \\ \r\n"
					+ "                                            ~~~~~  | | (_) |  ~~~~~\r\n"
					+ "                                            ~~~~~  | |> _ <   ~~~~~\r\n"
					+ "                                                   | | (_) |\r\n"
					+ "                                                   |_|\\___/ \r\n");
			break;
		}
		case 17: {
			System.out.println(
					  "                                                   __ ______ \r\n"
					+ "                                                  /_ |____  |\r\n"
					+ "                                            ~~~~~  | |   / /  ~~~~~\r\n"
					+ "                                            ~~~~~  | |  / /   ~~~~~\r\n"
					+ "                                                   | | / /   \r\n"
					+ "                                                   |_|/_/    \r\n"

			);
			break;
		}
		case 16: {
			System.out.println(
					  "                                                   __   __  \r\n"
					+ "                                                  /_ | / /  \r\n"
					+ "                                            ~~~~~  | |/ /_    ~~~~~\r\n"
					+ "                                            ~~~~~  | | '_ \\   ~~~~~\r\n"
					+ "                                                   | | (_) |\r\n"
					+ "                                                   |_|\\___/ \r\n");
			break;
		}
		case 15: {
			System.out.println(
					  "                                                   __ _____ \r\n"
					+ "                                                  /_ | ____|\r\n"
					+ "                                            ~~~~~  | | |__    ~~~~~\r\n"
					+ "                                            ~~~~~  | |___ \\   ~~~~~\r\n"
					+ "                                                   | |___) |\r\n"
					+ "                                                   |_|____/ \r\n");
			break;
		}
		case 14: {
			System.out.println(
					  "                                                   __ _  _   \r\n"
					+ "                                                  /_ | || |  \r\n"
					+ "                                            ~~~~~  | | || |_  ~~~~~\r\n"
					+ "                                            ~~~~~  | |__   _| ~~~~~\r\n"
					+ "                                                   | |  | |  \r\n"
					+ "                                                   |_|  |_|  \r\n");
			break;
		}
		case 13: {
			System.out.println(
					  "                                                   __ ____  \r\n"
					+ "                                                  /_ |___ \\ \r\n"
					+ "                                            ~~~~~  | | __) |  ~~~~~\r\n"
					+ "                                            ~~~~~  | ||__ <   ~~~~~\r\n"
					+ "                                                   | |___) |\r\n"
					+ "                                                   |_|____/ \r\n");
			break;
		}
		case 12: {
			System.out.println(
					  "                                                   __ ___  \r\n"
					+ "                                                  /_ |__ \\ \r\n"
					+ "                                            ~~~~~  | |  ) |  ~~~~~\r\n"
					+ "                                            ~~~~~  | | / /   ~~~~~\r\n"
					+ "                                                   | |/ /_ \r\n"
					+ "                                                   |_|____|\r\n");
			break;
		}
		case 11: {
			System.out.println(
					  "                                                   __ __ \r\n"
					+ "                                                  /_ /_ |\r\n"
					+ "                                            ~~~~~  | || |  ~~~~~\r\n"
					+ "                                            ~~~~~  | || |  ~~~~~\r\n"
					+ "                                                   | || |\r\n"
					+ "                                                   |_||_|\r\n");
			break;
		}
		case 10: {
			System.out.println(
					  "                                                   __  ___  \r\n"
					+ "                                                  /_ |/ _ \\ \r\n"
					+ "                                            ~~~~~  | | | | |  ~~~~~\r\n"
					+ "                                            ~~~~~  | | | | |  ~~~~~\r\n"
					+ "                                                   | | |_| |\r\n"
					+ "                                                   |_|\\___/ \r\n");
			break;
		}
		case 9: {
			System.out.println(
					  "                                                    ___  \r\n"
					+ "                                                   / _ \\ \r\n"
					+ "                                            ~~~~~ | (_) | ~~~~~\r\n"
					+ "                                            ~~~~~  \\__, | ~~~~~\r\n"
					+ "                                                     / / \r\n"
					+ "                                                    /_/  \r\n");
			break;
		}
		case 8: {
			System.out.println(
					  "                                                    ___  \r\n"
					+ "                                                   / _ \\ \r\n"
					+ "                                            ~~~~~ | (_) | ~~~~~\r\n"
					+ "                                            ~~~~~  > _ <  ~~~~~\r\n"
					+ "                                                  | (_) |\r\n"
					+ "                                                   \\___/ \r\n");
			break;
		}
		case 7: {
			System.out.println(
					  "                                                  ______ \r\n"
					+ "                                                 |____  |\r\n"
					+ "                                            ~~~~~    / /  ~~~~~\r\n"
					+ "                                            ~~~~~   / /   ~~~~~\r\n"
					+ "                                                   / /   \r\n"
					+ "                                                  /_/    \r\n");
			break;
		}
		case 6: {
			System.out.println(
					  "                                                     __  \r\n"
					+ "                                                    / /  \r\n"
					+ "                                            ~~~~~  / /_   ~~~~~\r\n"
					+ "                                            ~~~~~ | '_ \\  ~~~~~\r\n"
					+ "                                                  | (_) |\r\n"
					+ "                                                   \\___/ \r\n");
			break;
		}
		case 5: {
			System.out.println(
					  "                                                   _____ \r\n"
					+ "                                                  | ____|\r\n"
					+ "                                            ~~~~~ | |__   ~~~~~\r\n"
					+ "                                            ~~~~~ |___ \\  ~~~~~\r\n"
					+ "                                                   ___) |\r\n"
					+ "                                                  |____/ \r\n");
			break;
		}
		case 4: {
			System.out.println(
					  "                                                   _  _   \r\n"
					+ "                                                  | || |  \r\n"
					+ "                                            ~~~~~ | || |_  ~~~~~\r\n"
					+ "                                            ~~~~~ |__   _| ~~~~~\r\n"
					+ "                                                     | |  \r\n"
					+ "                                                     |_|  \r\n");
			break;
		}
		case 3: {
			System.out.println(
					  "                                                   ____  \r\n"
					+ "                                                  |___ \\ \r\n"
					+ "                                            ~~~~~   __) |  ~~~~~\r\n"
					+ "                                            ~~~~~  |__ <   ~~~~~\r\n"
					+ "                                                   ___) |\r\n"
					+ "                                                  |____/ \r\n");
			break;
		}
		case 2: {
			System.out.println(
					  "                                                   ___  \r\n"
					+ "                                                  |__ \\ \r\n"
					+ "                                            ~~~~~    ) | ~~~~~\r\n"
					+ "                                            ~~~~~   / /  ~~~~~\r\n"
					+ "                                                   / /_ \r\n"
					+ "                                                  |____|\r\n");
			break;
		}
		case 1: {
			System.out.println(
					  "                                                   __ \r\n"
					+ "                                                  /_ |\r\n"
					+ "                                            ~~~~~  | | ~~~~~\r\n"
					+ "                                            ~~~~~  | | ~~~~~\r\n"
					+ "                                                   | |\r\n"
					+ "                                                   |_|\r\n");
			break;
		}
		case 0: {
			System.out.println(
					  "                                                    ___  \r\n"
					+ "                                                   / _ \\ \r\n"
					+ "                                            ~~~~~ | | | | ~~~~~\r\n"
					+ "                                            ~~~~~ | | | | ~~~~~\r\n"
					+ "                                                  | |_| |\r\n"
					+ "                                                   \\___/ \r\n");
			break;
		}		
		}	
	}
}
