package logic;

import java.util.ArrayList;
import logic.candidate.*;
import logic.company.*;
import logic.interview.*;

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

	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

	public void addCandidate(Candidate c) {
		candidates.add(c);
	}

	public ArrayList<MonthRegister> getMonths() {
		return months;
	}

	public void addMonth(MonthRegister m) {
		months.add(m);
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

	public ArrayList<Offer> getBestOffers() {
		ArrayList<Offer> offers = new ArrayList<Offer>();
		double biggest = 0;
		for (Company c : companyManager.getCompanies())
			for (Offer o : c.getOffers())
				if (o.getSalary() > biggest) {
					biggest = o.getSalary();
					offers.clear();
					offers.add(o);
				} else if (o.getSalary() == biggest)
					offers.add(o);

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

	public ArrayList<Offer> getBestOffersByBranch(String branch) {
		ArrayList<Offer> offers = new ArrayList<>();
		double highestSalary = 0;
		for (Company company : companyManager.getCompanies()) {
			for (Offer offer : company.getOffers()) {
				if (offer.getBranch().equalsIgnoreCase(branch)) {
					if (offer.getSalary() > highestSalary) {
						highestSalary = offer.getSalary();
						offers.clear();
						offers.add(offer);
					} else if (offer.getSalary() == highestSalary) {
						offers.add(offer);
					}
				}
			}
		}
		return offers;
	}
}
