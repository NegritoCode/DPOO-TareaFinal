package gui.candidate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Agency;
import logic.candidate.Candidate;

public class CandidateManagerScreen extends JFrame {
    private Agency agency;
    private JTable candidateTable;
    private DefaultTableModel tableModel;

    public CandidateManagerScreen(Agency agency) {
        this.agency = agency;
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
        String[] columnNames = {"ID", "Nombre", "Ramo", "Sexo", "Teléfono", "Especialidad", "Años de Experiencia"};
        tableModel = new DefaultTableModel(columnNames, 0);
        candidateTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(candidateTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("Agregar");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Eliminar");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load data
        loadCandidates();

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CandidateFormDialog(CandidateManagerScreen.this, agency, null).setVisible(true);
                loadCandidates();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = candidateTable.getSelectedRow();
                if (selectedRow != -1) {
                    String cid = (String) tableModel.getValueAt(selectedRow, 0);
                    Candidate candidate = agency.getCandidates().stream()
                            .filter(c -> c.getCid().equals(cid))
                            .findFirst()
                            .orElse(null);
                    if (candidate != null) {
                        new CandidateFormDialog(CandidateManagerScreen.this, agency, candidate).setVisible(true);
                        loadCandidates();
                    }
                } else {
                    JOptionPane.showMessageDialog(CandidateManagerScreen.this, "Seleccione un candidato para editar.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = candidateTable.getSelectedRow();
                if (selectedRow != -1) {
                    String cid = (String) tableModel.getValueAt(selectedRow, 0);
                    agency.getCandidates().removeIf(c -> c.getCid().equals(cid));
                    loadCandidates();
                } else {
                    JOptionPane.showMessageDialog(CandidateManagerScreen.this, "Seleccione un candidato para eliminar.");
                }
            }
        });
    }

    private void loadCandidates() {
        tableModel.setRowCount(0);
        for (Candidate candidate : agency.getCandidates()) {
            tableModel.addRow(new Object[]{
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Agency agency = new Agency("Ĺos papus");
            new CandidateManagerScreen(agency).setVisible(true);
        });
    }
}
