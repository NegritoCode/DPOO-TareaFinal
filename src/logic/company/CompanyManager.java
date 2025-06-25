package logic.company;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyManager {
    private HashMap<String, Company> companies;

    public CompanyManager() {
        companies = new HashMap<String, Company>();
    }

    public ArrayList<Company> getCompanies() {
        return new ArrayList<Company>(companies.values());
    }

    public Company getCompanyByName(String name) throws IllegalArgumentException {
        Company company = null;
        ArrayList<Company> values = getCompanies();
        int i = 0;
        while (company == null && i < values.size()) {
            if (values.get(i).getName().equals(name)) {
                company = values.get(i);
            }
            i++;
        }
        if (company == null) {
            throw new IllegalArgumentException("La Empresa " + name + " no se encontró.");
        }
        return company;
    }

    public Company getCompanyById(String id) {
    	if (!companies.containsKey(id)) throw new IllegalArgumentException("La Empresa " + id + " no se encontró.");
        return companies.get(id);
    }

    public Company createCompany(String name, String address, String phone, String sector) {
        Company company = new Company(name, address, phone, sector);
        companies.put(company.getId(), company);
        return company;
    }

    public Offer createOffer(String companyId, String branch, double salary) {
        return getCompanyById(companyId).createOffer(branch, salary);
    }

    public ArrayList<Company> getCompaniesWithoutInterviews() {
        ArrayList<Company> result = new ArrayList<>();
        for (Company company : companies.values()) {
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
