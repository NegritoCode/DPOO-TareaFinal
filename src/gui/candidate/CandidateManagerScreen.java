package gui.candidate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Agency;
import logic.GlobalAgency;
import logic.candidate.Candidate;
import utils.Navigation;

public class CandidateManagerScreen extends JFrame {
    private Agency agency;
    private JTable candidateTable;
    private DefaultTableModel tableModel;

    public CandidateManagerScreen() {
        this.agency = GlobalAgency.getInstance();
        setTitle("Gestión de Candidatos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Gestión de Candidatos", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(63, 81, 181));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, BorderLayout.NORTH);

        // Table
        String[] columnNames = { "Carnet", "Nombre", "Ramo", "Sexo", "Teléfono", "Especialidad", "Años de Experiencia" };
        tableModel = new DefaultTableModel(columnNames, 0);
        candidateTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(candidateTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("Agregar");
        JButton backButton = new JButton("Atrás");
        
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load data
        loadCandidates();

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CandidateFormDialog(CandidateManagerScreen.this, null).setVisible(true);
                loadCandidates();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navigation.goTo("Home");
            }
        });
    }

    private void loadCandidates() {
        tableModel.setRowCount(0);
        for (Candidate candidate : agency.getCandidates()) {
            tableModel.addRow(new Object[] {
                    candidate.getCid(),
                    candidate.getName(),
                    candidate.getBranch(),
                    candidate.getSex(),
                    candidate.getPhone(),
                    candidate.getSpeciality(),
                    candidate.getXpYears()
            });
        }
        
    }
}
