package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.Navigation;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public Login() {
		setTitle("Iniciar Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLabel = new JLabel("Iniciar Sesión");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setForeground(new Color(33, 150, 243));
		titleLabel.setBounds(120, 30, 200, 30);
		contentPane.add(titleLabel);

		usernameField = new JTextField();
		usernameField.setBounds(50, 100, 300, 40);
		usernameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(usernameField);

		JLabel usernameLabel = new JLabel("Nombre de usuario");
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		usernameLabel.setForeground(new Color(100, 100, 100));
		usernameLabel.setBounds(50, 80, 200, 20);
		contentPane.add(usernameLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(50, 180, 300, 40);
		passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(passwordField);

		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordLabel.setForeground(new Color(100, 100, 100));
		passwordLabel.setBounds(50, 160, 200, 20);
		contentPane.add(passwordLabel);

		final JLabel errorLabel = new JLabel("Nombre de usuario o contraseña incorrecto");
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(50, 230, 300, 20);
		contentPane.add(errorLabel);
		errorLabel.setVisible(false);

		JButton loginButton = new JButton("Iniciar Sesión");
		loginButton.setBounds(50, 280, 300, 40);
		loginButton.setBackground(new Color(33, 150, 243));
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(new Font("Arial", Font.BOLD, 14));
		loginButton.setFocusPainted(false);
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (usernameField.getText().equals("admin") && new String(passwordField.getPassword()).equals("admin")) {
					JOptionPane.showMessageDialog(null, "Bienvenido");
					dispose();
					Navigation.goTo("Home");
				} else
					errorLabel.setVisible(true);
			}
		});
		contentPane.add(loginButton);
	}
}
