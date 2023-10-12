package milyoner;


public class Address 
{
	//A class that we storage participants address variables. We are using this class in partipicantSetter
	private String country;
	private String city;
	private String district;
	private String no;
	private String street;
	
	
	public Address(String country, String city, String district, String no, String street) {
		super();
		this.country = country;
		this.city = city;
		this.district = district;
		this.no = no;
		this.street = street;
	}
	
	public String getCountry() 
	{
		return country;
	}

	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getDistrict() 
	{
		return district;
	}

	public void setDistrict(String district) 
	{
		this.district = district;
	}

	public String getNo() 
	{
		return no;
	}

	public void setNo(String no) 
	{
		this.no = no;
	}

	public String getStreet() 
	{
		return street;
	}

	public void setStreet(String street) 
	{
		this.street = street;
	}
}
