package milyoner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Statistic 
{
	private static StatisticCategory[] statisticCategory;
	private static StatisticParticipant[] statisticParticipant;
	
	
	public static void readStatistic(Question[] questions, Participant[] participants)
	{
		int counterParticipant = 0, counterCategory = 0;
		String sentence;
		StatisticCategory[] readCategory = null;
		StatisticParticipant[] readParticipant = null;
		
		boolean flag = true;
		try 
		{
			BufferedReader objReader1 = new BufferedReader(new FileReader("statistics.txt"));
			BufferedReader objReader2 = new BufferedReader(new FileReader("statistics.txt"));
			BufferedReader objReader3 = new BufferedReader(new FileReader("statistics.txt"));
			
			while ((sentence = objReader1.readLine()) != null) 
			{
				if(!sentence.equals("")) 
				{
					String[] temp = sentence.trim().split("#");   //temp[7].replaceAll("[^0-9]",""
					if(temp.length == 3 && Operations.arrChecker(temp)) 
					{
						counterCategory++;
					}
					if(temp.length == 4 && Operations.arrChecker(temp)) 
					{
						counterParticipant++;
					}
				}				
			}				
						
			readCategory = new StatisticCategory[counterCategory];
			int i = 0;			
 			while (counterCategory > i) 
			{			
				String[] temp = objReader2.readLine().trim().split("#");
				if(temp.length == 3 && Operations.arrChecker(temp)) 
				{
					readCategory[i] = new StatisticCategory(temp[0], Integer.parseInt(temp[1].replaceAll("[^0-9]","")),
							Integer.parseInt(temp[2].replaceAll("[^0-9]","")));
					i++;
				}			
			}		
 						
 			readParticipant = new StatisticParticipant[counterParticipant];
			i = 0;
			while(counterParticipant > i) 
			{
				String[] temp = objReader3.readLine().trim().split("#");
				if(temp.length == 4 && Operations.arrChecker(temp)) ////Integer.parseInt(arr[1].replaceAll("[^0-9]",""))
				{
					readParticipant[i] = new StatisticParticipant(temp[0], temp[1], 
							Integer.parseInt(temp[2].replaceAll("[^0-9]","")),
							Integer.parseInt(temp[3].replaceAll("[^0-9]","")));
					i++;
				}			
			}
			
			objReader1.close();
			objReader2.close();
			objReader3.close();
		}
		catch(Exception e)
		{
			System.out.println("There is not a file such as 'statistic.txt'");
			flag = false;
		}
		
		//game info
		String newCategorySentence = "";
		for(int i = 0; i < questions.length; i++) 
			if(!newCategorySentence.contains(questions[i].getCategory())) 
				newCategorySentence += questions[i].getCategory() + "#";		
		
		//read txt
		if(flag) 
			for(int i = 0; i < readCategory.length; i++) 
				if(!newCategorySentence.contains(readCategory[i].getCategory())) 
					newCategorySentence += readCategory[i].getCategory() + "#";		
		
		//game info
		String[] tempCategoryArr = newCategorySentence.trim().split("#");
		int[][] tempCategoryInt = new int[tempCategoryArr.length][2];			
		for(int i = 0; i < tempCategoryArr.length; i++) 
			for(int j = 0; j < questions.length; j++) 
				if(tempCategoryArr[i].equals(questions[j].getCategory())) 
					if(questions[j].getIsTrueFalse().equals("true")) 
						tempCategoryInt[i][0]++;
					else if(!questions[j].getIsTrueFalse().equals("")) 
						tempCategoryInt[i][1]++;	
		//read txt
		if(flag) 
			for(int i = 0; i < tempCategoryArr.length; i++) 
				for(int j = 0; j < readCategory.length; j++) 
					if(tempCategoryArr[i].equals(readCategory[j].getCategory())) 
					{															
						tempCategoryInt[i][0] += readCategory[j].getNumcorrect();
						tempCategoryInt[i][1] += readCategory[j].getNumfalse();		
					}				
		//create variable of category
		statisticCategory = new StatisticCategory[tempCategoryArr.length];
		for(int i = 0; i < tempCategoryArr.length; i++) 
			statisticCategory[i] = new StatisticCategory(tempCategoryArr[i], tempCategoryInt[i][0], tempCategoryInt[i][1]);
		
		//-------------------------------------------------------------------------------------------------------------------
		
		//game info
		String newParticipantSentence = "";
		for(int i = 0; i < participants.length; i++) 
			if(!newParticipantSentence.contains(participants[i].getName())) 
				newParticipantSentence += participants[i].getName() + "#";
		
		//read txt
		if(flag) 
			for(int i = 0; i < readParticipant.length; i++) 
				if(!newParticipantSentence.contains(readParticipant[i].getName())) 
					newParticipantSentence += readParticipant[i].getName() + "#";
		
		String[] tempParticipantArr = newParticipantSentence.trim().split("#");
		String[] cities = new String[tempParticipantArr.length];
		int[][] trueAge = new int[tempParticipantArr.length][2];		
		for(int i = 0; i < tempParticipantArr.length; i++) 
			for(int j = 0; j < participants.length; j++) 
				if(tempParticipantArr[i].equals(participants[j].getName())) 
				{
					cities[i] = participants[j].getAddress().getCity();
					trueAge[i][0] += participants[j].getTrueAnswered();
					trueAge[i][1] = participants[j].getBirthDate().getAge();

				}
		
		//read txt
		if(flag) 
			for(int i = 0; i < tempParticipantArr.length; i++) 
				for(int j = 0; j < readParticipant.length; j++) 
					if(tempParticipantArr[i].equals(readParticipant[j].getName())) 
					{															
						cities[i] = readParticipant[j].getCity();
						trueAge[i][0] += readParticipant[j].getCorrect();
						trueAge[i][1] = readParticipant[j].getAge();
					}	
		
		//create variable of category
		statisticParticipant = new StatisticParticipant[tempParticipantArr.length];
			for(int i = 0; i < tempParticipantArr.length; i++) 
				statisticParticipant[i] = new StatisticParticipant(tempParticipantArr[i], cities[i], trueAge[i][0], trueAge[i][1]);	
	}

	public static void writeStatistic() throws FileNotFoundException, UnsupportedEncodingException 
	{
		PrintWriter writer = new PrintWriter("statistics.txt", "UTF-8");
		
		for(int i = 0; i < statisticCategory.length; i++) 
			writer.println(statisticCategory[i].getCategory() + "#" + statisticCategory[i].getNumcorrect() 
					+ "#" + statisticCategory[i].getNumfalse());
		
		for(int i = 0; i < statisticParticipant.length; i++)
			writer.println(statisticParticipant[i].getName() + "#" + statisticParticipant[i].getCity() + "#" +
					statisticParticipant[i].getCorrect() + "#" + statisticParticipant[i].getAge());
			
		writer.close();
	}
	
	
	public static StatisticCategory[] getStatisticCategory()
	{
		return statisticCategory;
	}
	
	public static StatisticParticipant[] getStatisticParticipant()
	{
		return statisticParticipant;
	}
}
