package logic;

import logic.candidate.Candidate;
import logic.company.Company;
import logic.company.Offer;
import utils.Generator;

public class GlobalAgency {
    private static Agency instance;

    private GlobalAgency() {
    }

    public static Agency getInstance() {
        if (instance == null) {
            instance = new Agency("Agencia Empleadora");

            for (Company company : Generator.generateOffers(30, Generator.generateCompanies(10))) {
                String id = instance.getCompanyManager().createCompany(
                        company.getName(),
                        company.getAddress(),
                        company.getPhone(),
                        company.getSector()).getId();
                for (Offer offer : company.getOffers()) {
                    instance.createOffer(id, offer.getBranch(), offer.getSalary());
                }
            }

            for (Candidate candidate : Generator.generateCandidates(50)) {
                instance.createCandidate(
                        candidate.getCid(),
                        candidate.getBranch(),
                        candidate.getName(),
                        candidate.getSex(),
                        candidate.getAddress(),
                        candidate.getPhone(),
                        candidate.getSchoolLevel(),
                        candidate.getSpeciality(),
                        candidate.getXpYears());
                
                for (Company c : instance.getCompanyManager().getCompanies()) {
                	for (Offer o:c.getOffers()) {
                		if (o.isElegibleTo(candidate)) {
                			instance.createInterview(candidate.getCid(), c.getId(), o.getId());
                		}
                	}
                }
            }
        }
        return instance;
    }
}