package logic;

import java.util.ArrayList;

public class Company {
	private String name;
	private String address;
	private String phone;
	private String sector;
	private ArrayList<Offer> offers;
	
	public Company(String name, String address, String phone, String sector) {
		
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.sector = sector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public ArrayList<Offer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<Offer> offers) {
		this.offers = offers;
	}
	
	
}
