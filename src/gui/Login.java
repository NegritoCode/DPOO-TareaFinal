package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;



	public Login(HomeScreen homeScreen) {
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usernameField = new JTextField();
		usernameField.setBounds(10, 69, 338, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(10, 125, 338, 20);
		contentPane.add(passwordField);

		JLabel usernameLabel = new JLabel("Nombre de usuario:");
		usernameLabel.setBounds(10, 49, 129, 14);
		usernameLabel.setForeground(Color.WHITE);
		contentPane.add(usernameLabel);

		JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
		passwordLabel.setBounds(10, 100, 107, 14);
		passwordLabel.setForeground(Color.WHITE);
		contentPane.add(passwordLabel);


		final JLabel errorLabel = new JLabel("Nombre de usuario o contrase\u00F1a incorrecto");
		errorLabel.setBounds(10, 151, 338, 20);
		errorLabel.setForeground(Color.RED);
		contentPane.add(errorLabel);
		errorLabel.setVisible(false);

		JButton loginButton = new JButton("Iniciar Sesi\u00F3n");
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
					JOptionPane.showMessageDialog(null, "Bienvenido");
					dispose();
					
				} else
					errorLabel.setVisible(true);
					
			}
		});
		loginButton.setBounds(185, 227, 119, 23);
		contentPane.add(loginButton);

		JLabel imageLabel = new JLabel("New label");
		imageLabel.setBounds(379, 49, 129, 122);
		imageLabel.setIcon(new ImageIcon("C:\\Users\\Negrito\\Pictures\\Flux_Dev_A_modern_and_sleek_illustration_for_a_login_page_of_a_3.jpg"));
		imageLabel.setForeground(Color.WHITE);
		contentPane.add(imageLabel);





	}
}
