package gui.reports;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import utils.Navigation;

import javax.swing.SwingConstants;

import logic.Agency;
import logic.GlobalAgency;
import logic.candidate.Candidate;
import logic.company.Company;
import logic.company.Offer;
import logic.interview.Interview;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class ReportsHomeScreen extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	
	private JTable table;
	private JTextField btnSearch;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportsHomeScreen frame = new ReportsHomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReportsHomeScreen() {
		final Agency agency = GlobalAgency.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(288, 11, 609, 434);
		contentPane.add(panel_1);

		final JLabel titleTab = new JLabel("Mejores Ofertas");
		titleTab.setHorizontalAlignment(SwingConstants.CENTER);
		titleTab.setBounds(175, 11, 264, 26);
		titleTab.setBorder(UIManager.getBorder("EditorPane.border"));
		titleTab.setFont(new Font("Tahoma", Font.PLAIN, 21));

		final DefaultTableModel mejoresOfertasModel = new DefaultTableModel(
				new Object[][] {
						{null, null, null},
				},
				new String[] {
						"Compañía", "Rama", "Salario"
				}
				);

		for (Offer offer : agency.getBestOffers()) {
			mejoresOfertasModel.addRow(new Object[] {
					agency.getCompanyManager().getCompanyById(offer.getCompanyId()).getName(),
					offer.getBranch(),
					offer.getSalary() + ""
			});
		}

		final DefaultTableModel entrevistasPorDiaModel = new DefaultTableModel(
				new Object[][] {
						{null, null, null, null},
				},
				new String[] {
						"Candidato", "Mes", "Día", "Oferta"
				}
				);
		
		for (Candidate candidate : agency.getCandidates()) {
			for (Interview interview : agency.getInterviewsByCandidate(candidate.getCid())) {
				
				entrevistasPorDiaModel.addRow(new Object[] {
						agency.getCandidateByCid(interview.getCandidateCid()).getName(),
						interview.getMonthlyId(),
						interview.getDayId(),
						interview.getOfferId(),
				});
			}
		}

		table = new JTable();
		table.setModel(mejoresOfertasModel);
		table.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 589, 354);
		panel_1.add(scrollPane);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(10, 11, 268, 434);
		contentPane.add(panel);
		panel.setLayout(null);

		final JButton btnMejoresOfertas = new JButton("Mejores Ofertas");
		btnMejoresOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				titleTab.setText(btnMejoresOfertas.getText());
				table.setModel(mejoresOfertasModel);
			}
		});
		btnMejoresOfertas.setContentAreaFilled(false);
		btnMejoresOfertas.setBorderPainted(false);
		btnMejoresOfertas.setForeground(Color.WHITE);
		btnMejoresOfertas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMejoresOfertas.setBounds(10, 147, 248, 43);
		panel.add(btnMejoresOfertas);
		panel_1.setLayout(null);


		panel_1.add(titleTab);
		
		btnSearch = new JTextField();
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Company company = null;
				ArrayList<Offer> offersByCompany = new ArrayList<Offer>();
				for (Company c : agency.getCompanyManager().getCompanies())
					if(c.getName().equalsIgnoreCase(btnSearch.getText()))
						company = c;
				for (Offer of : company.getOffers())
					for (Offer o : agency.getBestOffers())
						if(of.compareTo(o))
						offersByCompany.add(o);
				mejoresOfertasModel.setRowCount(0);
				for (Offer o : offersByCompany)
					mejoresOfertasModel.addRow(new Object[] {o.getCompanyId(),o.getBranch(),o.getSalary()});
							
				
				
				
			}
		});
		btnSearch.setBounds(10, 38, 179, 20);
		panel_1.add(btnSearch);
		btnSearch.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(10, 21, 46, 14);
		panel_1.add(lblBuscar);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mejoresOfertasModel.setRowCount(0);
				for (Offer offer : agency.getBestOffers()) {
					mejoresOfertasModel.addRow(new Object[] {
							agency.getCompanyManager().getCompanyById(offer.getCompanyId()).getName(),
							offer.getBranch(),
							offer.getSalary() + ""
					});
				}
			}
		});
		button.setIcon(new ImageIcon(ReportsHomeScreen.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-error.png")));
		button.setBounds(185, 38, 37, 20);
		panel_1.add(button);

		final JButton btnEntrevistasPorDia = new JButton("Entrevistas por dia");
		btnEntrevistasPorDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				titleTab.setText(btnEntrevistasPorDia.getText());
				table.setModel(entrevistasPorDiaModel);
			}
		});

		btnEntrevistasPorDia.setForeground(Color.WHITE);
		btnEntrevistasPorDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEntrevistasPorDia.setContentAreaFilled(false);
		btnEntrevistasPorDia.setBorderPainted(false);
		btnEntrevistasPorDia.setBounds(10, 214, 248, 43);
		panel.add(btnEntrevistasPorDia);

		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Navigation.goTo("Home");
			}

		});
		btnAtrs.setBorder(new LineBorder(new Color(255, 255, 255)));
		btnAtrs.setForeground(Color.WHITE);
		btnAtrs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtrs.setContentAreaFilled(false);
		btnAtrs.setBounds(10, 380, 116, 43);
		panel.add(btnAtrs);



		// Table
		String[] columnNames = { "Nombre", "Dirección", "Teléfono", "Sector" };
		tableModel = new DefaultTableModel(columnNames, 1);
		tableModel.addRow(new Object[] {"w", "ww", "ooo", "rtt"});
	}
}
