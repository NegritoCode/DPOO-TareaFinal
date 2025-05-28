package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
	private JTextField usuario;
	private JPasswordField contrasenna;



	public Login() {
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usuario = new JTextField();
		usuario.setBounds(10, 69, 338, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);

		contrasenna = new JPasswordField();
		contrasenna.setBounds(10, 125, 338, 20);
		contentPane.add(contrasenna);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(10, 49, 129, 14);
		lblNombreDeUsuario.setForeground(Color.WHITE);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 100, 107, 14);
		lblContrasea.setForeground(Color.WHITE);
		contentPane.add(lblContrasea);


		final JLabel aviso = new JLabel("Nombre de usuario o contrase\u00F1a incorrecto");
		aviso.setBounds(10, 151, 338, 20);
		aviso.setForeground(Color.RED);
		contentPane.add(aviso);
		aviso.setVisible(false);

		JButton btnIniciarSesin = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (usuario.getText().equals("admin")&& contrasenna.getText().equals("admin")){
					JOptionPane.showMessageDialog(null,"Bienvenido");
			        dispose();
				}
				else
					aviso.setVisible(true);
					
			}
		});
		btnIniciarSesin.setBounds(185, 227, 119, 23);
		contentPane.add(btnIniciarSesin);

		JLabel label = new JLabel("New label");
		label.setBounds(379, 49, 129, 122);
		label.setIcon(new ImageIcon("C:\\Users\\Negrito\\Pictures\\Flux_Dev_A_modern_and_sleek_illustration_for_a_login_page_of_a_3.jpg"));
		label.setForeground(Color.WHITE);
		contentPane.add(label);





	}
}
