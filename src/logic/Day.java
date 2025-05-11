package logic;

import java.util.ArrayList;

public class Day {
	private String id;
	private String monthId;
	private ArrayList<Interview> interviews;
	
	public Day(String id, String monthId) {
		setId(id);
		setMonthId(monthId);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonthId() {
		return monthId;
	}

	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}

	public ArrayList<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(ArrayList<Interview> interviews) {
		this.interviews = interviews;
	}
}

