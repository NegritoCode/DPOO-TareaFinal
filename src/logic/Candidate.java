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

    public Candidate(String cid, String branch, String name, char sex, String address, String phone, String schoolLevel,
            String speciality, int xpYears) {
        setCid(cid);
        setBranch(branch);
        setName(name);
        setSex(sex);
        setAddress(address);
        setPhone(phone);
        setSchoolLevel(schoolLevel);
        setSpeciality(speciality);
        setXpYears(xpYears);
        this.requeriments = new ArrayList<>();
    }

    public boolean isElegibleFor(Offer offer) {
        // es el mismo que el de oferta
        // se implementó este método duplicado debido a un requisito del ejercicio
        return offer.isElegibleTo(this);
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

    // Getters
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

    // Setters
    public void setCid(String cid) {
        if (cid == null || cid.trim().isEmpty() || cid.length() != 11) { 
            throw new IllegalArgumentException("El CID del candidato debe tener exactamente 11 caracteres.");
        }
        this.cid = cid;
    }

    public void setBranch(String branch) {
        if (branch == null || branch.trim().isEmpty()) {
            throw new IllegalArgumentException("La rama no puede ser nula o vacía.");
        }
        this.branch = branch;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (!name.matches("[a-zA-Z ]+")) { // Solo letras y espacios
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }
        this.name = name;
    }

    public void setSex(char sex) {
        if (sex != 'M' && sex != 'F') {
            throw new IllegalArgumentException("El sexo debe ser 'M' (masculino) o 'F' (femenino).");
        }
        this.sex = sex;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.address = address.trim();
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.trim().matches("\\d{8}")) {
            throw new IllegalArgumentException("El teléfono debe ser un número de 8 dígitos.");
        }
        this.phone = phone.trim();
    }

    public void setSchoolLevel(String schoolLevel) {
        if (schoolLevel == null || schoolLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("El nivel escolar no puede estar vacío.");
        }
        this.schoolLevel = schoolLevel.trim().toLowerCase();
    }

    public void setSpeciality(String speciality) {
        if (speciality == null || speciality.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.speciality = speciality.trim().toLowerCase();
    }

    public void setXpYears(int xpYears) {
        if (xpYears < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }
        this.xpYears = xpYears;
    }
}