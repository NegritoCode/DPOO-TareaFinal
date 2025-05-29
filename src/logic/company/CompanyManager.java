package logic.company;

import java.util.ArrayList;

public class CompanyManager {
    private ArrayList<Company> companies;

    public CompanyManager() {
        companies = new ArrayList<Company>();
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompanyByName(String name) throws IllegalArgumentException {
        Company company = null;
        int i = 0;
        while (company == null && i < companies.size()) {
            if (companies.get(i).getName().equals(name)) {
                company = companies.get(i);
            }
            i++;
        }
        if (company == null) {
            throw new IllegalArgumentException("La Empresa " + name + " no se encontró.");
        }
        return company;
    }

    public Company getCompanyById(String id) {
        Company company = null;
        int i = 0;
        while (company == null && i < companies.size()) {
            if (companies.get(i).getId().equals(id)) {
                company = companies.get(i);
            }
            i++;
        }
        if (company == null) {
            throw new IllegalArgumentException("La Empresa " + id + " no se encontró.");
        }
        return company;
    }

    public Company createCompany(String name, String address, String phone, String sector) {
        Company company = new Company(name, address, phone, sector);
        companies.add(company);
        return company;
    }

    public Offer createOffer(String companyId, String branch, double salary) {
        return getCompanyById(companyId).createOffer(branch, salary);
    }

    public ArrayList<Company> getCompaniesWithoutInterviews() {
        ArrayList<Company> result = new ArrayList<>();
        for (Company company : companies) {
            boolean hasInterviews = false;
            for (Offer offer : company.getOffers()) {
                if (!offer.isAvailable()) {
                    hasInterviews = true;
                    break;
                }
            }
            if (!hasInterviews) {
                result.add(company);
            }
        }
        return result;
    }
}
