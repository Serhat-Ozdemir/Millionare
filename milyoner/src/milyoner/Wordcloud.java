package milyoner;


import java.io.IOException;
import java.util.*;


public class Wordcloud {
	
	
	public static String[] wordsOfWordCloud (String textForRemove,  int level) throws IOException
	//Function for removing stopwords and other invalid characters from questions
    {
		String[] stopWords = Operations.textReader("stop_words.txt");
		
		
        int count = 0;
        String[] questionWords = null;
        String[] removedWords = null;
        String preventDuplicatedWords = " ";
        textForRemove =textForRemove.toLowerCase();
        
        textForRemove = textForRemove.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", " ");
        
        while(textForRemove.contains("  ")) {
            textForRemove = textForRemove.replaceAll("  ", " ");
        }
        
        //removing and initializing stop words
        questionWords = textForRemove.split(" "); 
        //we replace stop words with empty string
        for(int i = 0; i < questionWords.length; i++) { 
            if(preventDuplicatedWords.contains(" "+questionWords[i]+" ")) {
                questionWords[i] = "";
                count++;
            }
                
            for(int j = 0; j < stopWords.length; j++) {
                
                if(questionWords[i].equalsIgnoreCase(stopWords[j])) {
                    questionWords[i] = "";
                    count++;
                    break;
                }
            }
            //for counting each word once
            preventDuplicatedWords = preventDuplicatedWords + questionWords[i] + " ";
        }
        //Created an array with just word clouds
        removedWords = new String[questionWords.length-count];
        count = 0;
        for(int i =0; i<questionWords.length;i++)
            if(!questionWords[i].equalsIgnoreCase("")) {
                removedWords[count] = questionWords[i];
                count++;
            }
        return removedWords;
    }
    public static void wordCloudPrint(String[] wordCloudWords) 
    {
        //This function prints word clouds with randomly spaces
        Random rnd = new Random();
        int row = 0, count =0, randomIndex =0;
        int random = 0;
        String str_row = "  ";
        boolean[] randomPrint = new boolean[wordCloudWords.length];
        for(int i = 0; i< randomPrint.length;i++)
            randomPrint[i] = true;
        
        while(count < wordCloudWords.length) 
        {
            randomIndex = rnd.nextInt(randomPrint.length);
            //used randomIndex turning false
            if(randomPrint[randomIndex] == true && wordCloudWords[randomIndex] != null) {
                //we replace used word clouds with null characters
                if(row <= 1) { //to have 3 words per line
                    random = rnd.nextInt(4);
                    for(int j = 0; j < random; j++) {
                        str_row += str_row;
                        
                    }
                    System.out.print(str_row + wordCloudWords[randomIndex] + str_row);
                    count++;
                    row++; 
                    str_row = "  ";
                    randomPrint[randomIndex] = false;
                }
                else {  
                    
                    System.out.print( wordCloudWords[randomIndex] + "\n");                            
                    count++;
                    row = 0;
                    randomPrint[randomIndex] = false;
                }
            }
            //for null elements
            else if(randomPrint[randomIndex] == true && wordCloudWords[randomIndex] == null){
                count++;
                randomPrint[randomIndex] = false;
        }
    
      }
    }
    public static String wordCloudCheck(String[] wordCloudWords) {
    	//This function checks if the user enter a word which word cloud includes
    	
        System.out.print("\nPlease select a word: ");
        
        String wordChoice = "";
        boolean flag = false;
        while(flag == false) {
            Scanner scannerWordCloudCheck = new Scanner(System.in);
            wordChoice = scannerWordCloudCheck.nextLine();
            
            for(int i = 0; i < wordCloudWords.length;i++) {
                if(wordCloudWords[i]!= null && wordCloudWords[i].equalsIgnoreCase(wordChoice) ) {
                    flag = true;
                    scannerWordCloudCheck.close();
                    break;
                }
                
            }
            if(!flag)
                System.out.println("Please select an option from above!!");
                
        }
        return wordChoice;

    }
}