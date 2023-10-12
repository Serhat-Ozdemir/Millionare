package milyoner;

public class Participant 
{	
	private String name;
	private BirthDate birthDate;
	private String number;
	private Address address;
	private int trueAnswered;
	private boolean isPlayed;
	private int moneyEarned;
	

	public Participant(String name, BirthDate birthDate, String number, Address address, int trueAnswered, boolean isPlayed, int moneyEarned) 
	{
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.number = number;
		this.address = address;
		this.trueAnswered = trueAnswered;
		this.isPlayed = isPlayed;
		this.moneyEarned = moneyEarned;
	}
		
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public BirthDate getBirthDate() 
	{
		return birthDate;
	}
	
	public void setBirthDate(BirthDate birthDate) 
	{
		this.birthDate = birthDate;
	}
	
	public String getNumber() 
	{
		return number;
	}
	
	public void setNumber(String number) 
	{
		this.number = number;
	}
	
	public Address getAddress() 
	{
		return address;
	}
	
	public void setAddress(Address address) 
	{
		this.address = address;
	}
	
	public int getTrueAnswered() 
	{
		return trueAnswered;
	}
	
	public void setTrueAnswered(int trueAnswered) 
	{
		this.trueAnswered = trueAnswered;
	}
	
	public boolean getIsPlayed() 
	{
		return isPlayed;
	}

	public void setIsPlayed(boolean isPlayed) 
	{
		this.isPlayed = isPlayed;
	}
	
	public int getMoneyEarned() 
	{
		return moneyEarned;
	}

	public void setMoneyEarned(int moneyEarned) 
	{
		this.moneyEarned = moneyEarned;
	}
}


