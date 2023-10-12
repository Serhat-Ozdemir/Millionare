package milyoner;

import java.io.IOException;
import java.util.*;

public class Dictionary {
	private static String[] dictionary = null;
	private static String[][] dictionaryByLength = null;
		
	public static void setDictionary() throws IOException 
	{
		dictionary = Operations.textReader("dictionary.txt");
		int max = -1;
		for(int i = 0; i < dictionary.length; i++) 
			if(dictionary[i].length() > max)
				max = dictionary[i].length();			
		int[] columnMax = new int[max];
		for(int i = 0; i < dictionary.length; i++) 
			columnMax[dictionary[i].length() - 1]++;
		
		dictionaryByLength = new String[max][];			
		for(int i = 0; i < 31; i++) 
		{
			int counter = 0;
			String[] temp = new String[columnMax[i]];
			for(int j = 0; j < dictionary.length; j++) 
			{				
				if(dictionary[j].length() - 1 == i) 
				{
					temp[counter] = dictionary[j];
					counter++;
				}
			}
			dictionaryByLength[i] = temp;
			Arrays.sort(dictionaryByLength[i]);
		}
	}
	
	public static String[] getDictionary() 
	{
		return dictionary;
	}
	
	public static String[] getDicByLen(int n) 
	{
		return dictionaryByLength[n - 1];
	}
}
