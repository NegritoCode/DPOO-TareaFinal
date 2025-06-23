package gui.company;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;
import logic.company.Offer;
import utils.Navigation;
import utils.constants.Sector;
import gui.components.FilterDialog;
import gui.components.MButton;
import gui.components.MTable;
import gui.components.SearchField;

public class CompanyManagerScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private Agency agency;
	private MTable companyTable;
	private DefaultTableModel companyTableModel;
	private Company currentCompany;
	private JPanel companyDetailsPanel;
	private SearchField searchField;
	private HashMap<String, Boolean> sectorsFilter;
	private JTable offerTable;
	private DefaultTableModel offerTableModel;
	private Offer currentOffer;

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
		JButton backButton = new MButton("Atrás", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navigation.goTo("Home");
			}
		});

		searchField = new SearchField(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				renderCompanies();
			}
		});

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.add(searchField);
		topPanel.add(backButton, BorderLayout.WEST);
		topPanel.add(searchPanel, BorderLayout.EAST);
		getContentPane().add(topPanel, BorderLayout.NORTH);

		sectorsFilter = FilterDialog.listToFilter(Sector.SECTORS);

		JButton filterButton = new MButton("Filtrar por Sector", new ActionListener() {
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
		companyTableModel = new DefaultTableModel(columnNames, 0);
		companyTable = new MTable(companyTableModel);

		JScrollPane leftScrollPane = new JScrollPane(companyTable);
		centerPanel.add(leftScrollPane);

		String[] offerColumnNames = { "No.", "Rama", "Salario", "Disponible" };
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
		JButton addOfferButton = new MButton("Agregar Oferta", "small", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentCompany != null) {
					new OfferFormDialog(CompanyManagerScreen.this, currentCompany);
					renderOffers();
				} else {
					JOptionPane.showMessageDialog(CompanyManagerScreen.this,
							"Seleccione una empresa para agregar una oferta.");
				}
			}
		});
		JButton editOfferButton = new MButton("Editar Oferta", "small", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentOffer != null) {
					new OfferFormDialog(CompanyManagerScreen.this, currentCompany, currentOffer).setVisible(true);
					renderOffers();
				} else {
					JOptionPane.showMessageDialog(CompanyManagerScreen.this, "Seleccione una oferta para editar.");
				}
			}
		});
		final JButton deleteOfferButton = new MButton("Eliminar Oferta", "small", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentOffer != null) {
					currentOffer.setAvailable(!currentOffer.isAvailable());
					renderOffers();
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
		JButton addButton = new MButton("+", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CompanyFormDialog(CompanyManagerScreen.this, null).setVisible(true);
				renderCompanies();
			}
		});
		bottomPanel.add(addButton);

		// Load data
		renderCompanies();

		companyTable.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = companyTable.getSelectedRow();
				if (selectedRow != -1) {
					String name = (String) companyTableModel.getValueAt(selectedRow, 0);
					currentCompany = agency.getCompanyManager().getCompanyByName(name);
					renderCompanyDetails();
					renderOffers();
				}
			}
		});
		offerTable.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (currentCompany != null) {
					String index = (String) offerTableModel.getValueAt(offerTable.getSelectedRow(), 0);
					currentOffer = currentCompany.getOffers().get(Integer.parseInt(index));

					deleteOfferButton.setText(currentOffer.isAvailable() ? "Eliminar Oferta" : "Habilitar Oferta");

				}
			}
		});

	}

	private void showCompanyFilterDialog() {
		FilterDialog dialog = new FilterDialog(this, sectorsFilter);
		sectorsFilter = dialog.getFilter();
		renderCompanies();
	}

	public void renderCompanies() {
		String query = searchField.getText();
		companyTableModel.setRowCount(0);
		for (Company company : agency.getCompanyManager().getCompanies()) {
			if ((company.getName().toLowerCase().contains(query) || company.getAddress().toLowerCase().contains(query))
					&& sectorsFilter.get(company.getSector())) {
				companyTableModel.addRow(new Object[] { company.getName(), company.getAddress(), company.getSector() });
			}
		}
	}

	private void renderCompanyDetails() {
		companyDetailsPanel.removeAll();
		companyDetailsPanel.add(new DetailLabel("Nombre:", currentCompany.getName()));
		companyDetailsPanel.add(new DetailLabel("Dirección:", currentCompany.getAddress()));
		companyDetailsPanel.add(new DetailLabel("Teléfono:", currentCompany.getPhone()));
		companyDetailsPanel.add(new DetailLabel("Sector:", currentCompany.getSector()));
		companyDetailsPanel
				.add(new DetailLabel("Ofertas publicadas:", String.valueOf(currentCompany.getOffers().size())));
		companyDetailsPanel.revalidate();
		companyDetailsPanel.repaint();
	}

	private class DetailLabel extends JLabel {

		private static final long serialVersionUID = 1L;

		public DetailLabel(String title, String value) {
			super(String.format("<html><b>%s</b> %s</html>", title, value));
			setFont(new Font("Roboto", Font.PLAIN, 14));

		}
	}

	private void renderOffers() {
		offerTableModel.setRowCount(0);
		currentOffer = null;
		if (currentCompany != null) {
			ArrayList<Object[]> unavailableRows = new ArrayList<>();
			int i = 0;
			for (Offer offer : currentCompany.getOffers()) {
				if (offer.isAvailable()) {
					offerTableModel.addRow(new Object[] { i + "", offer.getBranch(), offer.getSalary(), "Sí" });
				} else {
					unavailableRows.add(new Object[] { i + "", offer.getBranch(), offer.getSalary(), "No" });
				}
				i++;
			}
			if (unavailableRows.size() > 0) {
				offerTableModel.addRow(new Object[] {});
				for (Object[] row : unavailableRows) {
					offerTableModel.addRow(row);
				}
			}
		}
	}
}
