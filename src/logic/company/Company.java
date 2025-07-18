package logic.company;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Id;

public class Company {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String sector;
    private HashMap<String, Offer> offers;

    public Company(String name, String address, String phone, String sector) {
        this.id = Id.generateId("COM");
        setName(name);
        setAddress(address);
        setPhone(phone);
        setSector(sector);
        this.offers = new HashMap<>();
    }

    public Offer createOffer(String branch, double salary) {
        Offer offer = new Offer(this.id, branch, salary);
        offers.put(offer.getId(), offer);
        return offer;
    }

    public Offer getOfferById(String offerId) {
    	if (!offers.containsKey(offerId)) throw new IllegalArgumentException("La Oferta " + offerId + " no se encontró.");
        return offers.get(offerId);
    }

    public ArrayList<Offer> getOffers() {
        return new ArrayList<>(offers.values());
    }

    public ArrayList<Offer> getAvailableOffers() {
        ArrayList<Offer> availableOffers = new ArrayList<>();
        for (Offer offer : offers.values()) {
            if (offer.isAvailable()) {
                availableOffers.add(offer);
            }
        }
        return availableOffers;
    }

    public int getNoOffers() {
        return offers.size(); 
    }

    // Getters y setters
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
    	if (phone == null || !phone.trim().matches("\\d{8}")) {
            throw new IllegalArgumentException("El teléfono debe ser un número de 8 dígitos.");
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
}
