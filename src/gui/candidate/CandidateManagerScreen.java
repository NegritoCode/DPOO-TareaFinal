package gui.candidate;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logic.Agency;
import logic.GlobalAgency;
import logic.candidate.Candidate;
import utils.Navigation;
import utils.constants.Branch;

public class CandidateManagerScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private Agency agency;
	private JTable candidateTable;
	private DefaultTableModel tableModel;
	private JTextArea candidateDetails;
	private JTextField searchField;
	private ArrayList<String> selectedBranches;

	public CandidateManagerScreen() {
		this.agency = GlobalAgency.getInstance();
		setTitle("Gestión de Candidatos");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Top Panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(245, 245, 245));
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224)));
		JButton backButton = new JButton("Atrás");
		backButton.setBackground(new Color(33, 150, 243));
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Roboto", Font.BOLD, 18));
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);

		searchField = new JTextField(20);
		searchField.setFont(new Font("Roboto", Font.PLAIN, 14));
		searchField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(224, 224, 224)), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		JButton searchButton = new JButton("Buscar");
		searchButton.setBackground(new Color(33, 150, 243));
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Roboto", Font.BOLD, 18));
		searchButton.setBorderPainted(false);
		searchButton.setFocusPainted(false);

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		topPanel.add(backButton, BorderLayout.WEST);
		topPanel.add(searchPanel, BorderLayout.CENTER);
		getContentPane().add(topPanel, BorderLayout.NORTH);

		selectedBranches = new ArrayList<String>(Arrays.asList(Branch.BRANCHES));

		JButton filterButton = new JButton("Filtrar por Ramo");
		filterButton.setBackground(new Color(33, 150, 243));
		filterButton.setForeground(Color.WHITE);
		filterButton.setFont(new Font("Roboto", Font.BOLD, 18));
		filterButton.setBorderPainted(false);
		filterButton.setFocusPainted(false);
		searchPanel.add(filterButton);
		
		JPanel titlePanel = new JPanel();
		topPanel.add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Gestión de Candidatos");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titleLabel);

		// Center Panel
		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanel.setBackground(new Color(245, 245, 245));
		String[] columnNames = { "Carnet", "Nombre", "Ramo" };
		tableModel = new DefaultTableModel(columnNames, 0);
		candidateTable = new JTable(tableModel);
		candidateTable.setFont(new Font("Roboto", Font.PLAIN, 14));
		candidateTable.setRowHeight(25);
		candidateTable.setGridColor(new Color(224, 224, 224));
		candidateTable.setSelectionBackground(new Color(33, 150, 243));
		candidateTable.setSelectionForeground(Color.WHITE);
		JScrollPane leftScrollPane = new JScrollPane(candidateTable);
		centerPanel.add(leftScrollPane);

		candidateDetails = new JTextArea();
		candidateDetails.setEditable(false);
		JScrollPane rightScrollPane = new JScrollPane(candidateDetails);
		centerPanel.add(rightScrollPane);
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		// Bottom Panel
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(245, 245, 245));
		bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(224, 224, 224)));
		JButton addButton = new JButton("+");
		addButton.setBackground(new Color(33, 150, 243));
		addButton.setForeground(Color.WHITE);
		addButton.setFont(new Font("Roboto", Font.BOLD, 24));
		addButton.setBorderPainted(false);
		addButton.setFocusPainted(false);
		addButton.setPreferredSize(new Dimension(50, 50));
		bottomPanel.add(addButton);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		// Load data
		loadCandidates();

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
				searchCandidates(query);
			}
		});

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CandidateFormDialog(CandidateManagerScreen.this, null).setVisible(true);
				loadCandidates();
			}
		});

		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBranchFilterDialog();
			}
		});

		candidateTable.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = candidateTable.getSelectedRow();
				if (selectedRow != -1) {
					String cid = (String) tableModel.getValueAt(selectedRow, 0);
					Candidate candidate = agency.getCandidateByCid(cid);
					displayCandidateDetails(candidate);
				}
			}
		});
	}

	private void showBranchFilterDialog() {
		JDialog dialog = new JDialog(this, "Seleccionar Ramo", true);
		setSize(300, 400);
		setLocationRelativeTo(this);
		getContentPane().setLayout(new BorderLayout());

		JPanel checkboxPanel = new JPanel();
		checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
		JCheckBox[] checkboxes = new JCheckBox[Branch.BRANCHES.length];
		for (int i = 0; i < Branch.BRANCHES.length; i++) {
			checkboxes[i] = new JCheckBox(Branch.BRANCHES[i]);
			checkboxes[i].setSelected(selectedBranches.contains(Branch.BRANCHES[i]));
			checkboxPanel.add(checkboxes[i]);
		}

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton markAllButton = new JButton("Marcar todas");
		markAllButton.setBackground(new Color(33, 150, 243));
		markAllButton.setForeground(Color.WHITE);
		markAllButton.setFont(new Font("Roboto", Font.BOLD, 18));
		markAllButton.setBorderPainted(false);
		markAllButton.setFocusPainted(false);

		JButton unmarkAllButton = new JButton("Desmarcar todas");
		unmarkAllButton.setBackground(new Color(33, 150, 243));
		unmarkAllButton.setForeground(Color.WHITE);
		unmarkAllButton.setFont(new Font("Roboto", Font.BOLD, 18));
		unmarkAllButton.setBorderPainted(false);
		unmarkAllButton.setFocusPainted(false);

		markAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox : checkboxes) {
					checkbox.setSelected(true);
				}
			}
		});

		unmarkAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox : checkboxes) {
					checkbox.setSelected(false);
				}
			}
		});

		buttonPanel.add(markAllButton);
		buttonPanel.add(unmarkAllButton);

		JButton applyButton = new JButton("Aplicar");
		applyButton.setBackground(new Color(33, 150, 243));
		applyButton.setForeground(Color.WHITE);
		applyButton.setFont(new Font("Roboto", Font.BOLD, 18));
		applyButton.setBorderPainted(false);
		applyButton.setFocusPainted(false);

		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedBranches.clear();

				for (int i = 0; i < Branch.BRANCHES.length; i++) {
					JCheckBox checkbox = checkboxes[i];
					if (checkbox.isSelected()) {
						selectedBranches.add(Branch.BRANCHES[i]);
					}
				}
				loadCandidates();
				dispose();
			}
		});

		getContentPane().add(new JScrollPane(checkboxPanel), BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(applyButton, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void loadCandidates() {
		tableModel.setRowCount(0);
		for (Candidate candidate : agency.getCandidates()) {
			if (selectedBranches.contains(candidate.getBranch())) {
				tableModel.addRow(new Object[] { candidate.getCid(), candidate.getName(), candidate.getBranch() });
			}
		}
	}

	private void searchCandidates(String query) {
		tableModel.setRowCount(0);
		for (Candidate candidate : agency.getCandidates()) {
			if ((candidate.getCid().contains(query)
					|| candidate.getName().toLowerCase().contains(query.toLowerCase()))) {
				tableModel.addRow(new Object[] { candidate.getCid(), candidate.getName(), candidate.getBranch() });
			}
		}
	}

	private void displayCandidateDetails(Candidate candidate) {
		candidateDetails.setText(String.format(
				"Carnet: %s\nNombre: %s\nRamo: %s\nSexo: %s\nTeléfono: %s\nEspecialidad: %s\nAños de Experiencia: %d",
				candidate.getCid(), candidate.getName(), candidate.getBranch(), candidate.getSex(),
				candidate.getPhone(), candidate.getSpeciality(), candidate.getXpYears()));
	}
}
