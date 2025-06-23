package gui.candidate;

import gui.components.FilterDialog;
import gui.components.MButton;
import gui.components.MTable;
import gui.components.SearchField;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import logic.Agency;
import logic.GlobalAgency;
import logic.candidate.Candidate;
import logic.interview.Interview;
import utils.Navigation;
import utils.constants.Branch;
import javax.swing.table.TableModel;

public class CandidateManagerScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private Agency agency;
	private MTable candidateTable;
	private DefaultTableModel candidatesTableModel;
	private JTextArea candidateDetails;
	private SearchField searchField;
	private HashMap<String, Boolean> branchesFilter;
	private DefaultTableModel interviewTableModel;

	public CandidateManagerScreen() {
		this.agency = GlobalAgency.getInstance();
		setTitle("Gestión de Candidatos");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		branchesFilter = FilterDialog.listToFilter(Branch.BRANCHES);

		//
		// Top Panel
		//
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
				renderCandidates();
			}
		});

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.add(searchField);

		JPanel titlePanel = new JPanel();
		topPanel.add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("Gestión de Candidatos");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titleLabel);
		topPanel.add(backButton, BorderLayout.WEST);
		topPanel.add(searchPanel, BorderLayout.EAST);
		getContentPane().add(topPanel, BorderLayout.NORTH);

		JButton filterButton = new MButton("Filtrar por Ramo", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBranchFilterDialog();
			}
		});
		searchPanel.add(filterButton);

		// Center Panel
		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanel.setBackground(new Color(245, 245, 245));
		String[] columnNames = { "Carnet", "Nombre", "Ramo" };
		candidatesTableModel = new DefaultTableModel(columnNames, 0);

		candidateTable = new MTable(candidatesTableModel);
		JScrollPane leftScrollPane = new JScrollPane(candidateTable);
		centerPanel.add(leftScrollPane);

		JPanel rightPanel = new JPanel();
		centerPanel.add(rightPanel);
		rightPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel detailsPanel = new JPanel();
		rightPanel.add(detailsPanel);
		detailsPanel.setLayout(new BorderLayout(0, 0));

		// Candidate Details
		candidateDetails = new JTextArea();
		detailsPanel.add(candidateDetails, BorderLayout.CENTER);
		candidateDetails.setEditable(false);

		JLabel detailsTitleLabel = new JLabel("Info del Candidato");
		detailsTitleLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		detailsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		detailsPanel.add(detailsTitleLabel, BorderLayout.NORTH);

		// Interview Table
		String[] interviewColumnNames = { "Empresa", "Oferta", "Fecha" };
		interviewTableModel = new DefaultTableModel(interviewColumnNames, 0);

		JPanel interviewsPanel = new JPanel();
		rightPanel.add(interviewsPanel);
		interviewsPanel.setLayout(new BorderLayout(0, 0));

		JLabel interviewsTitleLabel = new JLabel("Entrevistas");
		interviewsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		interviewsTitleLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		interviewsPanel.add(interviewsTitleLabel, BorderLayout.NORTH);
		
		MTable interviewTable = new MTable(interviewTableModel);
		JScrollPane interviewsScrollPanel = new JScrollPane(interviewTable);
		interviewsPanel.add(interviewsScrollPanel, BorderLayout.CENTER);
		
		JPanel interviewButtonsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) interviewButtonsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		interviewsPanel.add(interviewButtonsPanel, BorderLayout.SOUTH);
		JButton scheduleButton = new MButton("Agendar Entrevista", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = candidateTable.getSelectedRow();
				if (selectedRow != -1) {
					String cid = (String) candidatesTableModel.getValueAt(selectedRow, 0);
					Candidate candidate = agency.getCandidateByCid(cid);
					showScheduleDialog(candidate);
				} else {
					JOptionPane.showMessageDialog(CandidateManagerScreen.this, "Seleccione un candidato para agendar.");
				}
			}
		});
		interviewButtonsPanel.add(scheduleButton);
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		// Bottom Panel
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(245, 245, 245));
		bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(224, 224, 224)));
		JButton addButton = new MButton("+", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CandidateFormDialog(CandidateManagerScreen.this, null).setVisible(true);
				renderCandidates();
			}
		});
		addButton.setFont(new Font("Roboto", Font.BOLD, 24));
		addButton.setPreferredSize(new Dimension(50, 50));
		bottomPanel.add(addButton);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		// Load data
		renderCandidates();

		candidateTable.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = candidateTable.getSelectedRow();
				if (selectedRow != -1) {
					String cid = (String) candidatesTableModel.getValueAt(selectedRow, 0);
					Candidate candidate = agency.getCandidateByCid(cid);
					displayCandidateDetails(candidate);
					renderInterviews(candidate);
				}
			}
		});

	}

	private void showBranchFilterDialog() {
		branchesFilter = (new FilterDialog(this, branchesFilter)).getFilter();
		renderCandidates();
	}

	public void renderCandidates() {
		String query = searchField.getText();
		candidatesTableModel.setRowCount(0);
		for (Candidate candidate : agency.getCandidates()) {
			if ((candidate.getCid().contains(query) || candidate.getName().toLowerCase().contains(query.toLowerCase()))
					&& branchesFilter.get(candidate.getBranch())) {
				candidatesTableModel.addRow(new Object[] { candidate.getCid(), candidate.getName(), candidate.getBranch() });
			}
		}
	}

	private void renderInterviews(Candidate candidate) {
		interviewTableModel.setRowCount(0);
		if (candidate != null) {
			for (Interview interview : candidate.getInterviews()) {
				interviewTableModel.addRow(
						new Object[] { agency.getCompanyManager().getCompanyById(interview.getCompanyId()).getName(),
								interview.getOfferId(), interview.getMonthlyId() });
			}
		}
	}

	private void displayCandidateDetails(Candidate candidate) {
		candidateDetails.setText(String.format(
				"Carnet: %s\nNombre: %s\nRamo: %s\nSexo: %s\nTeléfono: %s\nEspecialidad: %s\nAños de Experiencia: %d",
				candidate.getCid(), candidate.getName(), candidate.getBranch(), candidate.getSex(),
				candidate.getPhone(), candidate.getSpeciality(), candidate.getXpYears()));
	}

	private void showScheduleDialog(Candidate candidate) {
		new ScheduleInterviewDialog(this, candidate, agency).setVisible(true);
	}

}
