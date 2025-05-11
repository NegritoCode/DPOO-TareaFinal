package logic;

import java.util.ArrayList;

public class MonthRegister {
	private String id;
	private int maxDay;
	private ArrayList<Day> days;
	
	public MonthRegister(String id, int maxDay){
		days = new ArrayList<Day>();
		setId(id);
		setMaxDay(maxDay);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(int maxDay) {
		this.maxDay = maxDay;
	}

	public ArrayList<Day> getDays() {
		return days;
	}

	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}
	
}
