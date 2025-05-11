package logic;

import java.util.ArrayList;

public class Agency {

	private String name;
	private ArrayList<Company> companies;
	private ArrayList<Candidate> candidates;
	private ArrayList<MonthRegister> mRegisters;

	public Agency(String name){
		companies = new ArrayList<Company>();
		candidates = new ArrayList<Candidate>();
		mRegisters = new ArrayList<MonthRegister>();
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

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public void addCompany(Company c) {
		companies.add(c);
	}

	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

	public void addCandidate(Candidate c) {
		candidates.add(c);
	}

	public ArrayList<MonthRegister> getmRegisteres() {
		return mRegisters;
	}

	public void addmRegisteres(MonthRegister m) {
		mRegisters.add(m);
	}
	
	public ArrayList<Interview> getInterviewsByCandidate(String id){
		ArrayList<Interview> interviews = new ArrayList<Interview>();
		
		for(MonthRegister m: mRegisters)
			for(Day d: m.getDays())
				for(Interview i: d.getInterviews())
					if(i.getCandidateCid().equals(id))
						interviews.add(i);
		return interviews;
	}

}

