package logic;

import java.util.ArrayList;
import logic.candidate.*;
import logic.company.*;
import logic.interview.*;
import utils.Id;

public class Agency {

	private String name;
	private CompanyManager companyManager;
	private ArrayList<Candidate> candidates;
	private ArrayList<MonthRegister> months;

	public Agency(String name) {
		companyManager = new CompanyManager();
		candidates = new ArrayList<Candidate>();
		months = new ArrayList<MonthRegister>();

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
		return candidates;
	}

	public ArrayList<MonthRegister> getMonths() {
		return months;
	}

	public ArrayList<Interview> getInterviewsByCandidate(String id) {
		ArrayList<Interview> interviews = new ArrayList<Interview>();

		for (MonthRegister m : months)
			for (Day d : m.getDays())
				for (Interview i : d.getInterviews())
					if (i.getCandidateCid().equals(id))
						interviews.add(i);
		return interviews;
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
		for (Candidate candidate : candidates) {
			if (candidate.getBranch().equalsIgnoreCase(branch)) {
				result.add(candidate);
			}
		}
		return result;
	}
	
	public Candidate getCandidateByCid(String cid) {
		Candidate result = null;
		int i = 0;
		while(result == null && i < candidates.size()) {
			Candidate candidate = candidates.get(i);
			if (candidate.getCid().equals(cid)) {
				result = candidate;
			}
			i++;
		}
		
		return result;
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

	public void createOffer(String companyId, String branch, double salary) {
		Offer offer = companyManager.createOffer(companyId, branch, salary);
		
		for (Candidate candidate : candidates) {
			if (offer.isElegibleTo(candidate)) {
				createInterview(candidate.getCid(), companyId, offer.getId());
			}
		}
	}

	public Candidate createCandidate(String cid, String branch, String name, char sex, String address, String phone,
			String schoolLevel, String speciality, int xpYears) {

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

		candidates.add(candidate);
		
		for (Company company : companyManager.getCompanies()) {
			for (Offer offer : company.getOffers()) {
				if (offer.isElegibleTo(candidate)) {
					createInterview(candidate.getCid(), company.getId(), offer.getId());
				}
			}
		}

		return candidate;
	}

	private void createInterview(String candidateId, String companyId, String offerId) {
		MonthRegister month = null;
		if (months.isEmpty()
				|| months.get(months.size() - 1).getDays().size() == months.get(months.size() - 1).getMaxDay()) {
			month = new MonthRegister(Id.getMonth(months.size()), 30);
			months.add(month);
		} else {
			month = months.get(months.size() - 1);
		}

		month.createInterview(candidateId, companyId, offerId);
	}
	public ArrayList<Company> getCompaniesMostOffers(){
		ArrayList<Company> companies = new ArrayList<Company>();
		int temp = -1;
		
		for (Company c : companyManager.getCompanies())
			if (c.getNoOffers() > temp){
				companies.clear();
				temp = c.getNoOffers();
			}
			else if (c.getNoOffers() > temp)
				companies.add(c);
		
		
		return companies;
	}
	public ArrayList<Candidate> getBestCandidates(){		// se tiene en cuenta el nivel de escolaridad y los años de experiencia
		ArrayList<Candidate> bCandidates = new ArrayList<Candidate>();
			for (Candidate c :candidates)
				if (c.getSchoolLevel().equals("Universidad") && c.getXpYears() >= 10)
					bCandidates.add(c);
		
		
		return bCandidates;
	}
}
