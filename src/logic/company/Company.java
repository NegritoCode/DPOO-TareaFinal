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

    public Company(String name, String phone, String address, String sector) {
        this.id = Id.generateId("COMPANY");
        setName(name);
        setAddress(address);
        setPhone(phone);
        setSector(sector);
        this.offers = new ArrayList<Offer>();
    }

    public Offer createOffer(String branch, double salary) {
        Offer offer = new Offer(branch, salary);
        offers.add(offer);
        return offer;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        this.phone = phone;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        if (sector == null || sector.trim().isEmpty()) {
            throw new IllegalArgumentException("El sector no puede estar vacío.");
        }
        this.sector = sector;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }
}
