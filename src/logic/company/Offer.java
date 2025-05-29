package logic.company;

import logic.candidate.Candidate;
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
        if (branch == null || branch.isEmpty()) {
            throw new IllegalArgumentException("La rama no puede ser nula o vacía.");
        }
        this.branch = branch;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("El salario debe ser un número positivo.");
        }
        this.salary = salary;
    }
}