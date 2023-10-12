package milyoner;

public class BirthDate 
{
	
	private int day;
	private int month;
	private int year;
	private int age;
	
	
	public BirthDate(int day, int month, int year, int age) 
	{
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.age = age;
	}
	
	
	public int getDay() 
	{
		return day;
	}
	
	public void setDay(int day) 
	{
		this.day = day;
	}
	
	public int getMonth() 
	{
		return month;
	}
	
	public void setMonth(int month) 
	{
		this.month = month;
	}
	
	public int getYear() 
	{
		return year;
	}
	
	public void setYear(int year) 
	{
		this.year = year;
	}	
	
	public int getAge() 
	{
		return age;
	}
	
	public void setAge(int year) 
	{
		this.year = age;
	}	
}
