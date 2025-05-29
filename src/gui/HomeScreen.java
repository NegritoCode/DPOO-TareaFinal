package gui;

import utils.ScreenManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 36, 838, 504);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Negrito\\Documents\\Projects Eclipse\\DPOO-TareaFinal\\assets\\Flux_Dev_A_vibrant_modern_illustration_for_the_background_of_a_2.jpg"));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 833, 36);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCandidatos = new JButton("Candidatos");
		btnCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenManager.show("CandidateManager");
			}
		});
		btnCandidatos.setBounds(149, 11, 134, 23);
		panel.add(btnCandidatos);
		btnCandidatos.setForeground(Color.BLACK);
		btnCandidatos.setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("Empresas");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(0, 11, 149, 23);
		panel.add(btnNewButton);
		
		JButton btnOfertas = new JButton("Ofertas");
		btnOfertas.setBackground(Color.WHITE);
		btnOfertas.setBounds(281, 11, 134, 23);
		panel.add(btnOfertas);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.setBackground(Color.WHITE);
		btnReportes.setBounds(415, 11, 134, 23);
		panel.add(btnReportes);
	}
}
