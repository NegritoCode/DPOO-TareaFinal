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
import java.util.List;
import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;
import utils.Navigation;
import utils.constants.Sector;

public class CompanyManagerScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Agency agency;
    private JTable companyTable;
    private DefaultTableModel tableModel;
    private JTextArea companyDetails;
    private JTextField searchField;
    private ArrayList<String> selectedSectors;

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
        topPanel.add(searchPanel, BorderLayout.EAST);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        selectedSectors = new ArrayList<>(Arrays.asList(Sector.SECTORS));

        JButton filterButton = new JButton("Filtrar por Sector");
        filterButton.setBackground(new Color(33, 150, 243));
        filterButton.setForeground(Color.WHITE);
        filterButton.setFont(new Font("Roboto", Font.BOLD, 18));
        filterButton.setBorderPainted(false);
        filterButton.setFocusPainted(false);
        searchPanel.add(filterButton);

        JPanel titlePanel = new JPanel();
        topPanel.add(titlePanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Gestión de Empresas");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titlePanel.add(titleLabel);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setBackground(new Color(245, 245, 245));
        String[] columnNames = { "Nombre", "Dirección", "Teléfono", "Sector" };
        tableModel = new DefaultTableModel(columnNames, 0);
        companyTable = new JTable(tableModel);
        companyTable.setFont(new Font("Roboto", Font.PLAIN, 14));
        companyTable.setRowHeight(25);
        companyTable.setGridColor(new Color(224, 224, 224));
        companyTable.setSelectionBackground(new Color(33, 150, 243));
        companyTable.setSelectionForeground(Color.WHITE);
        JScrollPane leftScrollPane = new JScrollPane(companyTable);
        centerPanel.add(leftScrollPane);

        companyDetails = new JTextArea();
        companyDetails.setEditable(false);
        JScrollPane rightScrollPane = new JScrollPane(companyDetails);
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
        loadCompanies();

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
                loadCompanies();
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSectorFilterDialog();
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

    private void showSectorFilterDialog() {
        JDialog dialog = new JDialog(this, "Seleccionar Sector", true);
        dialog.setSize(300, 400);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setLayout(new BorderLayout());

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        JCheckBox[] checkboxes = new JCheckBox[Sector.SECTORS.length];
        for (int i = 0; i < Sector.SECTORS.length; i++) {
            checkboxes[i] = new JCheckBox(Sector.SECTORS[i]);
            checkboxes[i].setSelected(selectedSectors.contains(Sector.SECTORS[i]));
            checkboxPanel.add(checkboxes[i]);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
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
                selectedSectors.clear();

                for (int i = 0; i < Sector.SECTORS.length; i++) {
                    JCheckBox checkbox = checkboxes[i];
                    if (checkbox.isSelected()) {
                        selectedSectors.add(Sector.SECTORS[i]);
                    }
                }
                loadCompanies();
                dialog.dispose();
            }
        });

        dialog.getContentPane().add(new JScrollPane(checkboxPanel), BorderLayout.CENTER);
        dialog.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        dialog.getContentPane().add(applyButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void loadCompanies() {
        tableModel.setRowCount(0);
        for (Company company : agency.getCompanyManager().getCompanies()) {
            if (selectedSectors.contains(company.getSector())) {
                tableModel.addRow(new Object[] {
                        company.getName(),
                        company.getAddress(),
                        company.getPhone(),
                        company.getSector()
                });
            }
        }
    }

    private void searchCompanies(String query) {
        tableModel.setRowCount(0);
        for (Company company : agency.getCompanyManager().getCompanies()) {
            if (company.getName().toLowerCase().contains(query.toLowerCase())
                    || company.getAddress().toLowerCase().contains(query.toLowerCase())) {
                tableModel.addRow(new Object[] {
                        company.getName(),
                        company.getAddress(),
                        company.getPhone(),
                        company.getSector()
                });
            }
        }
    }

    private void displayCompanyDetails(Company company) {
        companyDetails.setText(String.format(
                "Nombre: %s\nDirección: %s\nTeléfono: %s\nSector: %s\nOfertas publicadas: %s",
                company.getName(), company.getAddress(), company.getPhone(), company.getSector(), company.getOffers().size()));
    }
}
