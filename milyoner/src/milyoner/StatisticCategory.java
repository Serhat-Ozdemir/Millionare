package milyoner;

public class StatisticCategory 
{
	private String category;
	private int numcorrect;
	private int numfalse;
	
	public StatisticCategory(String category, int numcorrect, int numfalse) 
	{
		super();
		this.category = category;
		this.numcorrect = numcorrect;//Integer.parseInt(arr[1].replaceAll("[^0-9]",""))
		this.numfalse = numfalse;
	}
		
	public String getCategory() 
	{
		return category;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}
	public int getNumcorrect() 
	{
		return numcorrect;
	}
	public void setNumcorrect(int numcorrect) 
	{
		this.numcorrect = numcorrect;
	}
	public int getNumfalse() 
	{
		return numfalse;
	}
	public void setNumfalse(int numfalse) 
	{
		this.numfalse = numfalse;
	}	
}
