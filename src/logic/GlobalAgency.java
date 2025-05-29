package logic;

import logic.candidate.Candidate;
import logic.company.Company;
import utils.Generator;

public class GlobalAgency {
    private static Agency instance;

    private GlobalAgency() {
    }

    public static Agency getInstance() {
        if (instance == null) {
            instance = new Agency("Los papus");

            // TODO: inicializar datos aqui
            for (Company company : Generator.generateCompanies(21)) {
                instance.getCompanyManager().createCompany(
                        company.getName(),
                        company.getAddress(),
                        company.getPhone(),
                        company.getSector());
            }

            for (Candidate candidate : Generator.generateCandidates(21)) {
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
            }
        }
        return instance;
    }
}