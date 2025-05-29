package gui.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;
import utils.Navigation;

public class CompanyManagerScreen extends JFrame {
    private Agency agency;
    private JTable companyTable;
    private DefaultTableModel tableModel;

    public CompanyManagerScreen() {
        this.agency = GlobalAgency.getInstance();
        setTitle("Gestión de Empresas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Gestión de Empresas", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(63, 81, 181));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, BorderLayout.NORTH);

        // Table
        String[] columnNames = { "Nombre", "Dirección", "Teléfono", "Sector" };
        tableModel = new DefaultTableModel(columnNames, 0);
        companyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(companyTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("Agregar");
        buttonPanel.add(addButton);

        JButton backButton = new JButton("Atrás");
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Load data
        loadCompanies();

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CompanyFormDialog(CompanyManagerScreen.this, null).setVisible(true);
                loadCompanies();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navigation.goTo("Home");
            }
        });
    }

    private void loadCompanies() {
        tableModel.setRowCount(0);
        for (Company company : agency.getCompanyManager().getCompanies()) {
            tableModel.addRow(new Object[] {
                    company.getName(),
                    company.getAddress(),
                    company.getPhone(),
                    company.getSector()
            });
        }
    }
}
