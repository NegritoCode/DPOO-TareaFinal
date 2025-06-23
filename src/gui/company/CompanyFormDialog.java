package gui.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;

public class CompanyFormDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField nameField, addressField, phoneField, sectorField;
    private Agency agency;
    private Company company;

    public CompanyFormDialog(JFrame parent, Company company) {
        super(parent, "Formulario de Empresa", true);
        this.agency = GlobalAgency.getInstance();
        this.company = company;

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Dimension textFieldSize = new Dimension(200, 25);

        contentPanel.add(new JLabel("Nombre:"), gbc);
        nameField = new JTextField();
        nameField.setPreferredSize(textFieldSize);
        gbc.gridx = 1;
        contentPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Dirección:"), gbc);
        addressField = new JTextField();
        addressField.setPreferredSize(textFieldSize);
        gbc.gridx = 1;
        contentPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Teléfono:"), gbc);
        phoneField = new JTextField();
        phoneField.setPreferredSize(textFieldSize);
        gbc.gridx = 1;
        contentPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Sector:"), gbc);
        sectorField = new JTextField();
        sectorField.setPreferredSize(textFieldSize);
        gbc.gridx = 1;
        contentPanel.add(sectorField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        if (company != null) {
            loadCompanyData();
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCompany();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadCompanyData() {
        nameField.setText(company.getName());
        addressField.setText(company.getAddress());
        phoneField.setText(company.getPhone());
        sectorField.setText(company.getSector());
    }

    private void saveCompany() {
        try {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String sector = sectorField.getText();

            if (company == null) {
                agency.getCompanyManager().createCompany(name, address, phone, sector);
            } else {
                company.setName(name);
                company.setAddress(address);
                company.setPhone(phone);
                company.setSector(sector);
            }

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la empresa: " + ex.getMessage());
        }
    }
}
