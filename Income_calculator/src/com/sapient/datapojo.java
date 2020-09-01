package com.sapient;

public class datapojo {
	
	private String city; 
	private String country; 
	private String gender; 
	private String currency; 
	private double amount;
	private double avgIncome;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	} 
	public double getAvgIncome() {
		return avgIncome;
	}
	public void setAvgIncome(double avgIncome) {
		this.avgIncome = avgIncome;
	}
	@Override
	public String toString(){
		return "Datepojo [ City:" + city + " Country:" + country + ", Gender=" + gender + ", Currency=" + currency
				+ ", Avg Income=" + avgIncome + "]";
	}
	
	public datapojo(String city,String country, String gender,String currency, double avgIncome) {
		
		this.city=city;
		this.country=country;
		this.gender=gender;
		this.currency=currency;
		this.avgIncome=avgIncome;
	}
	
}
