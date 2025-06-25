package gui;

import utils.Navigation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.MButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeScreen() {
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setResizable(false);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(
					HomeScreen.this,
					"¿Seguro que desea salir?\nEste proyecto es una demo, los cambios no se guardarán.",
					"Confirmación de salida",
					JOptionPane.YES_NO_OPTION
				);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLabel = new JLabel("Bienvenido");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setForeground(new Color(33, 150, 243));
		titleLabel.setBounds(200, 20, 200, 30);
		contentPane.add(titleLabel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(50, 80, 500, 200);
		buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));
		buttonPanel.setBackground(Color.WHITE);
		contentPane.add(buttonPanel);

		JButton btnCandidatos = new MButton("Candidatos", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("CandidateManager");
			}
		});
		buttonPanel.add(btnCandidatos);

		JButton btnEmpresas = new MButton("Empresas", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("CompanyManager");
			}
		});
		buttonPanel.add(btnEmpresas);

		JButton btnReportes = new MButton("Reportes", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("ReportsHome");
			}
		});
		buttonPanel.add(btnReportes);
		
		JButton btnAbout = new MButton("Acerca de", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("AboutScreen");
			}
		});
	
		buttonPanel.add(btnAbout);

		JLabel footerLabel = new JLabel("© 2025 Agencia Empleadora");
		footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		footerLabel.setForeground(new Color(150, 150, 150));
		footerLabel.setBounds(180, 317, 200, 20);
		contentPane.add(footerLabel);
	}
}
