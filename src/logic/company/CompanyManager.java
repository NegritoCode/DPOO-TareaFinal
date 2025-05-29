package logic.company;

import java.util.ArrayList;

public class CompanyManager {
    private ArrayList<Company> companies;
    public CompanyManager () {
        companies = new ArrayList<Company>();
    }

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public void addCompany(Company c) {
		companies.add(c);
	}

    public void removeCompany(String companyId) {
        companies.removeIf(company -> company.getId().equals(companyId));
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
