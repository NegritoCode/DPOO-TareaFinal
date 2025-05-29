package runner;

import gui.HomeScreen;
import gui.Login;

import java.awt.EventQueue;

public class Runner {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final HomeScreen homeScreen = new HomeScreen();
					homeScreen.setVisible(true);
					homeScreen.setEnabled(false);
					Login login = new Login(homeScreen);
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
