package logic;

public class Offer {
    private String id;
    private String branch;
    private double salary;
    private boolean available;

    public Offer(String id, String branch, double salary) {
        this.id = id;
        this.branch = branch;
        this.salary = salary;
        this.available = true; // disponible por defecto
    }

    public boolean isElegibleTo(Candidate candidate) {
        // TODO: falta realizar la implementación de la verificación según
        //       requerimientos de la oferta vs requerimientos del candidato
        return branch.equals(candidate.getBranch()) && available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }

    public double getSalary() {
        return salary;
    }
}