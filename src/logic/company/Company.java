package logic.company;

import java.util.ArrayList;
import utils.Id;

public class Company {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String sector;
    private ArrayList<Offer> offers;

    public Company(String name, String phone, String sector) {
        this.id = Id.generateId("COMPANY");
        setName(name);
        setAddress(address);
        setPhone(phone);
        setSector(sector);
        this.offers = new ArrayList<Offer>();
    }

    // Getters and setters
    public String getId() {
        return id;
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
}
