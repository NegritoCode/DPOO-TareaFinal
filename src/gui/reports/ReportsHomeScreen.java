package gui.reports;

import gui.components.FilterDialog;
import gui.components.MButton;
import gui.components.MTable;
import gui.components.SearchField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import utils.Navigation;
import utils.constants.Branch;
import utils.constants.Sector;
import utils.constants.Specialty;

import javax.swing.SwingConstants;

import logic.Agency;
import logic.GlobalAgency;
import logic.candidate.Candidate;
import logic.company.Company;
import logic.company.Offer;
import logic.interview.Interview;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import gui.reports.ReportPanel;

public class ReportsHomeScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	final Agency agency = GlobalAgency.getInstance();
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private ReportPanel panelReport1, panelReport2, panelReport3, panelReport4;

	private void initializePanels() {
		panelReport1 = new ReportPanel("Mejores Ofertas", Branch.BRANCHES,
				new String[] { "Salario", "Rama", "Compañía" }, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String query = panelReport1.getSearchText();
						HashMap<String, Boolean> filter = panelReport1.getFilterOptions();
						DefaultTableModel table = panelReport1.getTableModel();

						table.setRowCount(0);
						for (Offer offer : agency.getBestOffers()) {
							Company company = agency.getCompanyManager().getCompanyById(offer.getCompanyId());
							if ((company.getName().toLowerCase().contains(query)) && filter.get(offer.getBranch())) {
								table.addRow(
										new Object[] { offer.getSalary() + "", offer.getBranch(), company.getName() });
							}
						}
					}
				});

		// obtener lista de nombres de candidatos para el filtro
		final ArrayList<Candidate> candidates = agency.getCandidates();
		String[] names = new String[candidates.size()];
		for (int i = 0; i < candidates.size(); i++) {
			names[i] = candidates.get(i).getName();
		}
		panelReport2 = new ReportPanel("Entrevistas por día", names,
				new String[] { "Candidato", "Mes", "Día", "Oferta" }, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String query = panelReport2.getSearchText();
						HashMap<String, Boolean> filter = panelReport2.getFilterOptions();
						DefaultTableModel table = panelReport2.getTableModel();

						table.setRowCount(0);
						for (Candidate candidate : candidates) {
							for (Interview interview : candidate.getInterviews()) {
								if ((candidate.getName().toLowerCase().contains(query)
										|| interview.getMonthlyId().contains(query))
										&& filter.get(candidate.getName())) {
									table.addRow(new Object[] { candidate.getName(), interview.getMonthlyId(),
											interview.getDayId(), interview.getOfferId() });
								}
							}
						}
					}
				});

		panelReport3 = new ReportPanel("Mayores Empleadores", Sector.SECTORS,
				new String[] { "Nombre", "Dirección", "Teléfono", "Sector" }, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String query = panelReport3.getSearchText();
						HashMap<String, Boolean> filter = panelReport3.getFilterOptions();
						DefaultTableModel table = panelReport3.getTableModel();
						
						table.setRowCount(0);
						for (Company c : agency.getCompaniesMostOffers()) {
							if ((c.getName().toLowerCase().contains(query)
									|| c.getAddress().toLowerCase().contains(query)
									|| c.getPhone().toLowerCase().contains(query)) && filter.get(c.getSector())) {
								table.addRow(new Object[] { c.getName(), c.getAddress(), c.getPhone(), c.getSector() });
							}
						}
					}
				});

		// Fusionar los arreglos
		String[] mergedArray = new String[Specialty.specialties.length + Branch.BRANCHES.length];
		System.arraycopy(Specialty.specialties, 0, mergedArray, 0, Specialty.specialties.length);
		System.arraycopy(Branch.BRANCHES, 0, mergedArray, Specialty.specialties.length, Branch.BRANCHES.length);
		panelReport4 = new ReportPanel("Candidatos Destacados", mergedArray, new String[] { "Cid", "Rama", "Nombre",
				"Sexo", "Dirección", "Teléfono", "Escolaridad", "Especialidad", "Experiencia" }, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String query = panelReport4.getSearchText();
						HashMap<String, Boolean> filter = panelReport4.getFilterOptions();
						DefaultTableModel table = panelReport4.getTableModel();
						
						table.setRowCount(0);
						for (Candidate c : GlobalAgency.getInstance().getBestCandidates()) {
							if ((filter.get(c.getBranch()) || filter.get(c.getSpeciality()))
									&& (c.getCid().toLowerCase().contains(query)
											|| c.getName().toLowerCase().contains(query)
											|| c.getAddress().toLowerCase().contains(query)
											|| c.getPhone().toLowerCase().contains(query)
											|| c.getSchoolLevel().toLowerCase().contains(query)
											|| c.getSpeciality().toLowerCase().contains(query))) {
								table.addRow(new Object[] { c.getCid(), c.getBranch(), c.getName(), c.getSex(),
										c.getAddress(), c.getPhone(), c.getSchoolLevel(), c.getSpeciality(),
										c.getXpYears() });
							}
						}
					}
				});

		panelReport1.render();
		panelReport2.render();
		panelReport3.render();
		panelReport4.render();
		contentPane.add(panelReport1);
		contentPane.add(panelReport2);
		contentPane.add(panelReport3);
		contentPane.add(panelReport4);

		switchPanel(panelReport1);
	}

	private void switchPanel(JPanel panelToShow) {
		panelReport1.setVisible(false);
		panelReport2.setVisible(false);
		panelReport3.setVisible(false);
		panelReport4.setVisible(false);
		panelToShow.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ReportsHomeScreen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		initializePanels();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, -15, 278, 491);
		contentPane.add(panel);
		panel.setLayout(null);

		final JButton btnMejoresOfertas = new JButton("Mejores Ofertas");
		btnMejoresOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(panelReport1);
			}
		});
		btnMejoresOfertas.setContentAreaFilled(false);
		btnMejoresOfertas.setBorderPainted(false);
		btnMejoresOfertas.setForeground(Color.WHITE);
		btnMejoresOfertas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMejoresOfertas.setBounds(10, 92, 248, 43);
		panel.add(btnMejoresOfertas);

		final JButton btnEntrevistasPorDia = new JButton("Entrevistas por día");
		btnEntrevistasPorDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(panelReport2);
			}
		});
		btnEntrevistasPorDia.setForeground(Color.WHITE);
		btnEntrevistasPorDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEntrevistasPorDia.setContentAreaFilled(false);
		btnEntrevistasPorDia.setBorderPainted(false);
		btnEntrevistasPorDia.setBounds(10, 163, 248, 43);
		panel.add(btnEntrevistasPorDia);

		final JButton btnReport3 = new JButton("Mayores Empleadores");
		btnReport3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(panelReport3);
			}
		});
		btnReport3.setForeground(Color.WHITE);
		btnReport3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReport3.setContentAreaFilled(false);
		btnReport3.setBorderPainted(false);
		btnReport3.setBounds(10, 237, 248, 43);
		panel.add(btnReport3);

		final JButton btnReport4 = new JButton("Candidatos Destacados");
		btnReport4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(panelReport4);
			}
		});
		btnReport4.setForeground(Color.WHITE);
		btnReport4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReport4.setContentAreaFilled(false);
		btnReport4.setBorderPainted(false);
		btnReport4.setBounds(10, 310, 248, 43);
		panel.add(btnReport4);

		// Table
		String[] columnNames = { "Nombre", "Dirección", "Teléfono", "Sector" };
		tableModel = new DefaultTableModel(columnNames, 1);
		tableModel.addRow(new Object[] { "w", "ww", "ooo", "rtt" });

		//
		// Boton para regresar
		//
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
		btnAtrs.setBounds(22, 411, 116, 43);
		panel.add(btnAtrs);
	}
}
