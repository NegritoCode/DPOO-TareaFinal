package gui.offer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;
import logic.company.Offer;
import utils.Navigation;

public class OfferManagerScreen extends JFrame {
    private Agency agency;
    private JTable offerTable;
    private DefaultTableModel tableModel;

    public OfferManagerScreen() {
        this.agency = GlobalAgency.getInstance();
        setTitle("Gestión de Ofertas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Gestión de Ofertas", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(63, 81, 181));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, BorderLayout.NORTH);

        // Table
        String[] columnNames = { "ID", "Rama", "Salario", "Disponible" };
        tableModel = new DefaultTableModel(columnNames, 0);
        offerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(offerTable);
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
        loadOffers();

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OfferFormDialog(OfferManagerScreen.this).setVisible(true);
                loadOffers();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navigation.goTo("Home");
            }
        });
    }

    private void loadOffers() {
        tableModel.setRowCount(0);
        for (Company company : agency.getCompanyManager().getCompanies()) {
            ArrayList<Offer> offers = company.getOffers();
            if (!offers.isEmpty()) {

                tableModel.addRow(new Object[] { "Empresa: " + company.getName(), "", "", "" });

                for (Offer offer : offers) {
                    tableModel.addRow(new Object[] {
                            offer.getId(),
                            offer.getBranch(),
                            offer.getSalary(),
                            offer.isAvailable() ? "Sí" : "No"
                    });
                }

                tableModel.addRow(new Object[] { "", "", "", "" });
            }
        }
    }
}
