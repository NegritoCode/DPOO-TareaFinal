package gui;

import utils.Navigation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {

	private JPanel contentPane;

	public HomeScreen() {
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLabel = new JLabel("Bienvenido");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setForeground(new Color(33, 150, 243));
		titleLabel.setBounds(200, 20, 200, 30);
		contentPane.add(titleLabel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(50, 80, 500, 200);
		buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));
		buttonPanel.setBackground(Color.WHITE);
		contentPane.add(buttonPanel);

		JButton btnCandidatos = createModernButton("Candidatos", new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Navigation.goTo("CandidateManager");
			}
		});
		buttonPanel.add(btnCandidatos);

		JButton btnEmpresas = createModernButton("Empresas", new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Navigation.goTo("CompanyManager");
			}
		});
		buttonPanel.add(btnEmpresas);

		JButton btnOfertas = createModernButton("Ofertas", new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Navigation.goTo("OfferManager");
			}
		});
		buttonPanel.add(btnOfertas);

		JButton btnReportes = createModernButton("Reportes", new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Navigation.goTo("MonthView");
			}
		});
		buttonPanel.add(btnReportes);

		JLabel footerLabel = new JLabel("© 2023 Mi Aplicación");
		footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		footerLabel.setForeground(new Color(150, 150, 150));
		footerLabel.setBounds(220, 320, 200, 20);
		contentPane.add(footerLabel);
	}

	private JButton createModernButton(String text, ActionListener action) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBackground(new Color(33, 150, 243));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addActionListener(action);
		return button;
	}
}
