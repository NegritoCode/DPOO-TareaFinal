package gui.company;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;
import logic.company.Offer;
import utils.Navigation;
import utils.constants.Sector;
import gui.components.Button;
import gui.components.MTable;

public class CompanyManagerScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private Agency agency;
	private JTable companyTable;
	private DefaultTableModel tableModel;
	private JPanel companyDetailsPanel;
	private JTextField searchField;
	private ArrayList<String> selectedSectors;
	private JTable offerTable;
	private DefaultTableModel offerTableModel;

	public CompanyManagerScreen() {
		this.agency = GlobalAgency.getInstance();
		setTitle("Gestión de Empresas");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Top Panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(245, 245, 245));
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224)));
		JButton backButton = new Button("Atrás", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("Home");
			}
		});

		searchField = new JTextField(20);
		searchField.setFont(new Font("Roboto", Font.PLAIN, 14));
		searchField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(224, 224, 224)), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		JButton searchButton = new Button("Buscar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = searchField.getText().trim();
				searchCompanies(query);
			}
		});

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		topPanel.add(backButton, BorderLayout.WEST);
		topPanel.add(searchPanel, BorderLayout.EAST);
		getContentPane().add(topPanel, BorderLayout.NORTH);

		selectedSectors = new ArrayList<>(Arrays.asList(Sector.SECTORS));

		JButton filterButton = new Button("Filtrar por Sector", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCompanyFilterDialog();
			}
		});
		searchPanel.add(filterButton);

		JPanel titlePanel = new JPanel();
		topPanel.add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("Gestión de Empresas");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titleLabel);

		// Center Panel
		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanel.setBackground(new Color(245, 245, 245));

		String[] columnNames = { "Nombre", "Dirección", "Sector" };
		tableModel = new DefaultTableModel(columnNames, 0);
		companyTable = new MTable(tableModel);

		JScrollPane leftScrollPane = new JScrollPane(companyTable);
		centerPanel.add(leftScrollPane);
		String[] offerColumnNames = { "Rama", "Salario", "Disponible" };
		offerTableModel = new DefaultTableModel(offerColumnNames, 0);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		JPanel rightPane = new JPanel();
		centerPanel.add(rightPane);
		rightPane.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel detailsPanel = new JPanel();
		rightPane.add(detailsPanel);
		detailsPanel.setLayout(new BorderLayout(0, 0));

		JLabel detailsPanelTitle = new JLabel("Detalles de la empresa");
		detailsPanelTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		detailsPanel.add(detailsPanelTitle, BorderLayout.NORTH);
		detailsPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);

		companyDetailsPanel = new JPanel();
		companyDetailsPanel.setLayout(new GridLayout(0, 1, 5, 5));
		companyDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		detailsPanel.add(new JScrollPane(companyDetailsPanel), BorderLayout.CENTER);

		// Panel de ofertas
		JPanel offerPanel = new JPanel(new BorderLayout());
		rightPane.add(offerPanel);

		JLabel offerPanelTitle = new JLabel("Ofertas de la empresa");
		offerPanelTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		offerPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		offerPanel.add(offerPanelTitle, BorderLayout.NORTH);
		offerTable = new MTable(offerTableModel);
		JScrollPane offerScrollPane = new JScrollPane(offerTable);
		offerPanel.add(offerScrollPane, BorderLayout.CENTER);

		JPanel offerButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton addOfferButton = new Button("Agregar Oferta", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = companyTable.getSelectedRow();
				if (selectedRow != -1) {
					String companyName = (String) tableModel.getValueAt(selectedRow, 0);
					Company company = agency.getCompanyManager().getCompanyByName(companyName);
					new OfferFormDialog(CompanyManagerScreen.this, company).setVisible(true);
					renderOffers(company);
				} else {
					JOptionPane.showMessageDialog(CompanyManagerScreen.this,
							"Seleccione una empresa para agregar una oferta.");
				}
			}
		});
		JButton editOfferButton = new Button("Editar Oferta", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = offerTable.getSelectedRow();
				if (selectedRow != -1) {
					String companyName = (String) tableModel.getValueAt(companyTable.getSelectedRow(), 0);
					Company company = agency.getCompanyManager().getCompanyByName(companyName);
					Offer offer = company.getOffers().get(selectedRow);
					new OfferFormDialog(CompanyManagerScreen.this, company, offer).setVisible(true);
					renderOffers(company);
				} else {
					JOptionPane.showMessageDialog(CompanyManagerScreen.this, "Seleccione una oferta para editar.");
				}
			}
		});
		JButton deleteOfferButton = new Button("Eliminar Oferta", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = offerTable.getSelectedRow();
				if (selectedRow != -1) {
					String companyName = (String) tableModel.getValueAt(companyTable.getSelectedRow(), 0);
					Company company = agency.getCompanyManager().getCompanyByName(companyName);
					company.getOffers().remove(selectedRow);
					renderOffers(company);
				} else {
					JOptionPane.showMessageDialog(CompanyManagerScreen.this,
							"Primero seleccione una oferta para eliminar.");
				}
			}
		});
		offerButtonPanel.add(addOfferButton);
		offerButtonPanel.add(editOfferButton);
		offerButtonPanel.add(deleteOfferButton);
		offerPanel.add(offerButtonPanel, BorderLayout.SOUTH);

		// Bottom Panel
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(245, 245, 245));
		bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(224, 224, 224)));
		JButton addButton = new Button("+", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CompanyFormDialog(CompanyManagerScreen.this, null).setVisible(true);
				renderCompanies();
			}
		});
		addButton.setFont(new Font("Roboto", Font.BOLD, 24));
		addButton.setPreferredSize(new Dimension(50, 50));
		bottomPanel.add(addButton);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		// Load data
		renderCompanies();

		// Button actions
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("Home");
			}
		});

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = searchField.getText().trim();
				searchCompanies(query);
			}
		});

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CompanyFormDialog(CompanyManagerScreen.this, null).setVisible(true);
				renderCompanies();
			}
		});

		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCompanyFilterDialog();
			}
		});

		companyTable.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = companyTable.getSelectedRow();
				if (selectedRow != -1) {
					String name = (String) tableModel.getValueAt(selectedRow, 0);
					Company company = agency.getCompanyManager().getCompanyByName(name);
					displayCompanyDetails(company);
					renderOffers(company);
				}
			}
		});

		companyTable.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = companyTable.getSelectedRow();
				if (selectedRow != -1) {
					String name = (String) tableModel.getValueAt(selectedRow, 0);
					Company company = agency.getCompanyManager().getCompanyByName(name);
					displayCompanyDetails(company);
				}
			}
		});
	}

	private void showCompanyFilterDialog() {
		new CompanyFilterDialog(this, selectedSectors);
	}

	public void renderCompanies() {
		tableModel.setRowCount(0);
		for (Company company : agency.getCompanyManager().getCompanies()) {
			if (selectedSectors.contains(company.getSector())) {
				tableModel.addRow(new Object[] { company.getName(), company.getAddress(),
						company.getSector() });
			}
		}
	}

	private void searchCompanies(String query) {
		tableModel.setRowCount(0);
		for (Company company : agency.getCompanyManager().getCompanies()) {
			if (company.getName().toLowerCase().contains(query.toLowerCase())
					|| company.getAddress().toLowerCase().contains(query.toLowerCase())) {
				tableModel.addRow(new Object[] { company.getName(), company.getSector() });
			}
		}
	}

	private void displayCompanyDetails(Company company) {
		companyDetailsPanel.removeAll();
		companyDetailsPanel.add(createDetailLabel("Nombre:", company.getName()));
		companyDetailsPanel.add(createDetailLabel("Dirección:", company.getAddress()));
		companyDetailsPanel.add(createDetailLabel("Teléfono:", company.getPhone()));
		companyDetailsPanel.add(createDetailLabel("Sector:", company.getSector()));
		companyDetailsPanel.add(createDetailLabel("Ofertas publicadas:", String.valueOf(company.getOffers().size())));
		companyDetailsPanel.revalidate();
		companyDetailsPanel.repaint();
	}

	private JLabel createDetailLabel(String title, String value) {
		JLabel label = new JLabel(String.format("<html><b>%s</b> %s</html>", title, value));
		label.setFont(new Font("Roboto", Font.PLAIN, 14));
		return label;
	}

	private void renderOffers(Company company) {
		offerTableModel.setRowCount(0);
		for (Offer offer : company.getOffers()) {
			offerTableModel.addRow(new Object[] { offer.getBranch(), offer.getSalary(),
					offer.isAvailable() ? "Sí" : "No" });
		}
	}
}
