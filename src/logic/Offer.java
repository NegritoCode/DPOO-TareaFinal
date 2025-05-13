package logic;

import utils.Id;

public class Offer {
    private String id;
    private String branch;
    private double salary;
    private boolean available;

    public Offer(String branch, double salary) {
        this.id = Id.generateId("OFFER");
        setBranch(branch);
        setSalary(salary);
        setAvailable(true); // disponible por defecto
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

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
}