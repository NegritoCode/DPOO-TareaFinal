package runner;

import gui.HomeScreen;
import gui.Login;
import gui.candidate.CandidateManagerScreen;
import gui.company.CompanyManagerScreen;
import gui.interview.MonthViewScreen;
import gui.reports.ReportsHomeScreen;
import utils.Navigation;

import java.awt.EventQueue;

public class Runner {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login().setVisible(true);
					
					Navigation.register("Home", new HomeScreen());
					Navigation.register("CandidateManager", new CandidateManagerScreen());
					Navigation.register("CompanyManager", new CompanyManagerScreen());
					Navigation.register("MonthView", new MonthViewScreen());
					Navigation.register("ReportsHome", new ReportsHomeScreen());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
