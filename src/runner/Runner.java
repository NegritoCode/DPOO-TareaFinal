package runner;

import gui.HomeScreen;
import gui.Login;
import gui.candidate.CandidateManagerScreen;
import utils.ScreenManager;

import java.awt.EventQueue;

public class Runner {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login().setVisible(true);
					
					ScreenManager.register("Home", new HomeScreen());
					ScreenManager.register("CandidateManager", new CandidateManagerScreen());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
