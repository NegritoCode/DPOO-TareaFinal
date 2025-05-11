package logic;

import java.util.ArrayList;

public class Candidate {
    private String cid;
    private String branch;
    private String name;
    private char sex;
    private String address;
    private String phone;
    private String schoolLevel;
    private String speciality;
    private int xpYears;
    private ArrayList<Requeriment> requeriments;

    public Candidate(String cid, String branch, String name, char sex, String address, String phone, String schoolLevel, String speciality, int xpYears) {
        this.cid = cid;
        this.branch = branch;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.schoolLevel = schoolLevel;
        this.speciality = speciality;
        this.xpYears = xpYears;
        this.requeriments = new ArrayList<Requeriment>();
    }

    public boolean isElegibleFor(Offer offer) {
        // TODO: implementar cuando Offer esté listo
        // branch.equals(offer.getBranch()) && offer.isAvailable();
        return true; 
    }

    public void createRequeriment(String type, String data) {
      requeriments.add(new Requeriment(type, data));
    }

    public void updateRequeriment(String type, String data) {
        for (Requeriment req : requeriments) {
            if (req.getType().equals(type)) {
                requeriments.remove(req);
                requeriments.add(new Requeriment(type, data));
                return;
            }
        }
    }

    public boolean hasRequeriment(String type) {
        // verifica si el candidato tiene un requerimiento de un tipo específico
        for (Requeriment req : requeriments) {
            if (req.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    // Getters y setters
    public String getCid() {
        return cid;
    }

    public String getBranch() {
        return branch;
    }

    public String getName() {
        return name;
    }

    public char getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getXpYears() {
        return xpYears;
    }

    public ArrayList<Requeriment> getRequeriments() {
        return requeriments;
    }
}