package gui.about;

import gui.components.MButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextArea;

import java.awt.FlowLayout;

import javax.swing.JButton;

import utils.Navigation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	public AboutScreen() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Navigation.goTo("Home");
			}
		});
		setBounds(100, 100, 830, 577);
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 794, 489);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblAcercaDe = new JLabel("Acerca de");
		lblAcercaDe.setForeground(new Color(0, 102, 255));
		lblAcercaDe.setBounds(331, 5, 131, 37);
		lblAcercaDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblAcercaDe);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 63, 758, 272);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setForeground(new Color(0, 102, 255));
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblDescripcin);

		JTextArea descriptionTextArea = new JTextArea();
		// Hacer el fondo transparente
		descriptionTextArea.setOpaque(false);

		// Quitar el borde blanco de fondo
		descriptionTextArea.setBackground(new Color(0, 0, 0, 0));
		descriptionTextArea.setText(
				"[Nombre del Programa] es una plataforma de gestión integral de ofertas de empleo, candidatos y empresas, diseñada para simplificar\r\n los procesos de reclutamiento y conexión laboral.\r\n\r\n*Para Candidatos: Encuentra oportunidades adaptadas a tu perfil.\r\n*Para empresas: Publica ofertas y gestiona postulantes eficientemente.\r\n*Para administradores: Control centralizado con reportes y estadísticas.\r\n\r\n🌟 Características Principales\r\n✅ Gestión de perfiles (candidatos y empresas).\r\n✅ Publicación y búsqueda de ofertas de empleo.\r\n✅ Proceso de selección con seguimiento por etapas.\r\n✅ Reportes personalizados (empresas top, candidatos mejor calificados, etc.)");
		panel_1.add(descriptionTextArea);
		descriptionTextArea.setEditable(false);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(20, 346, 442, 132);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JTextArea contactTextArea = new JTextArea();
		// Hacer el fondo transparente
		contactTextArea.setOpaque(false);

		// Quitar el borde blanco de fondo
		descriptionTextArea.setBackground(new Color(0, 0, 0, 0));
		contactTextArea.setText(
				"📞 Contacto & Desarrolladores\r\n🔹 Martin Alejandro Garcia Babastro\r\n     Github: NegritoCode\r\n🔹 Rodny Roberto Estrada León\r\n     GitHub: Rodnye");
		contactTextArea.setBounds(10, 36, 422, 85);
		panel_2.add(contactTextArea);

		JLabel lblDesarrolladoPor = new JLabel("Desarrollado por:");
		lblDesarrolladoPor.setBounds(148, 5, 155, 25);
		lblDesarrolladoPor.setForeground(new Color(0, 102, 255));
		lblDesarrolladoPor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(lblDesarrolladoPor);

		JButton backButton = new MButton("Atrás", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("Home");
			}
		});
		backButton.setBounds(667, 455, 101, 23);
		panel.add(backButton);

		JLabel lblVersion = new JLabel("version: 1.0");
		lblVersion.setBounds(10, 513, 99, 14);
		getContentPane().add(lblVersion);
	}
}
