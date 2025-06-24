package logic;

import java.util.ArrayList;
import java.util.HashMap;
import logic.candidate.*;
import logic.company.*;
import logic.interview.*;
import utils.Id;

public class Agency {

	private String name;
	private CompanyManager companyManager;
	private HashMap<String, Candidate> candidates; 
	private ArrayList<MonthRegister> months;
	private HashMap<String, ArrayList<Interview>> candidateInterviewsMap;
	private HashMap<String, ArrayList<Interview>> offerInterviewsMap;

	public Agency(String name) {
		companyManager = new CompanyManager();
		candidates = new HashMap<>();
		months = new ArrayList<MonthRegister>();
		months.add(new MonthRegister(Id.getMonth(months.size()), 30));
		
		candidateInterviewsMap = new HashMap<>();
		offerInterviewsMap = new HashMap<>();

		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("The name cannot be empty");
		else
			this.name = name;
	}

	public CompanyManager getCompanyManager() {
		return companyManager;
	}
    
	public ArrayList<Candidate> getCandidates() {
		return new ArrayList<Candidate>(candidates.values());
	}

	public ArrayList<MonthRegister> getMonths() {
		return months;
	}

	public ArrayList<Interview> getInterviewsByCandidate(String id) {
		if (candidateInterviewsMap.containsKey(id)) {
			return candidateInterviewsMap.get(id);
		}
		return new ArrayList<>();
	}

	public ArrayList<Interview> getInterviewsByOffer(String offerId) {
		if (offerInterviewsMap.containsKey(offerId)) {
			return offerInterviewsMap.get(offerId);
		}
		return new ArrayList<>();
	}

	/**
	 * Las mejores ofertas son las mayores de 10'000 salario
	 */
	public ArrayList<Offer> getBestOffers() {
		ArrayList<Offer> offers = new ArrayList<Offer>();
		for (Company c : companyManager.getCompanies())
			for (Offer o : c.getOffers())
				if (o.getSalary() > 10000) {
					offers.add(o);
				}

		return offers;
	}

	public ArrayList<Candidate> getCandidatesByBranch(String branch) {
		ArrayList<Candidate> result = new ArrayList<>();
		for (Candidate candidate : candidates.values()) {
			if (candidate.getBranch().equalsIgnoreCase(branch)) {
				result.add(candidate);
			}
		}
		return result;
	}

	public Candidate getCandidateByCid(String cid) {
		return candidates.get(cid);
	}

	public ArrayList<Offer> getBestOffersByBranch(String branch) {
		ArrayList<Offer> offers = new ArrayList<>();
		for (Company company : companyManager.getCompanies()) {
			for (Offer offer : company.getOffers()) {
				if (offer.getBranch().equals(branch)) {
					if (offer.getSalary() > 10000) {
						offers.add(offer);
					}
				}
			}
		}
		return offers;
	}
	public ArrayList<Offer> getOffersByBranch(String branch) {
		ArrayList<Offer> offers = new ArrayList<>();
		for (Company company : companyManager.getCompanies()) {
			for (Offer offer : company.getOffers()) {
				if (offer.getBranch().equals(branch)) {
						offers.add(offer);
				}
			}
		}
		return offers;
	}

	public Offer createOffer(String companyId, String branch, double salary) {
		return companyManager.createOffer(companyId, branch, salary);
	}

	public Candidate createCandidate(String cid, String branch, String name, char sex, String address, String phone,
			String schoolLevel, String speciality, int xpYears) {
		
		
		if(candidates.containsKey(cid))
			throw new IllegalArgumentException("Ya esta registrado ese CI");
		else{
		Candidate candidate;
		if (branch.equals("custodio")) {
			candidate = new SegurityCandidate(cid, branch, name, sex, address, phone, schoolLevel, speciality, xpYears,
					"", "");
		} else if (branch.equals("turismo")) {
			candidate = new TourismCandidate(cid, branch, name, sex, address, phone, schoolLevel, speciality, xpYears,
					"");
		} else {
			candidate = new Candidate(cid, branch, name, sex, address, phone, schoolLevel, speciality, xpYears);
		}

		candidates.put(cid, candidate); // Agregar candidato al HashMap
		
		return candidate;
		}
		

		
	}

	public void createInterview(String candidateId, String companyId, String offerId) {
		MonthRegister month = null;
		if (months.isEmpty()
				|| months.get(months.size() - 1).getDays().size() == months.get(months.size() - 1).getMaxDay()) {
			month = new MonthRegister(Id.getMonth(months.size()), 30);
			months.add(month);
		} else {
			month = months.get(months.size() - 1);
		}

		Interview interview = month.createInterview(candidateId, companyId, offerId);

		Candidate candidate = candidates.get(candidateId);
		if (candidate != null) {
			candidate.addInterview(interview); 
		}

		if (!offerInterviewsMap.containsKey(offerId)) {
			offerInterviewsMap.put(offerId, new ArrayList<>());
		}
		offerInterviewsMap.get(offerId).add(interview);
	}

	public ArrayList<Company> getCompaniesMostOffers() {
		ArrayList<Company> companies = new ArrayList<Company>();
		int temp = -1;

		for (Company c : companyManager.getCompanies())
			if (c.getNoOffers() > temp) {
				companies.clear();
				temp = c.getNoOffers();
				companies.add(c);
			} else if (c.getNoOffers() > temp)
				companies.add(c);

		return companies;
	}

	public ArrayList<Candidate> getBestCandidates() { // se tiene en cuenta el nivel de escolaridad y los años de
														// experiencia
		ArrayList<Candidate> bCandidates = new ArrayList<Candidate>();
		for (Candidate c : candidates.values())
			if (c.getSchoolLevel().equalsIgnoreCase("Universidad") && c.getXpYears() >= 10)
				bCandidates.add(c);

		return bCandidates;
	}
}
