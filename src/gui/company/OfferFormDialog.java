package gui.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;
import logic.company.Offer;
import utils.constants.Branch;

public class OfferFormDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField salaryField;
    private JComboBox<String> companyComboBox, branchComboBox;
    private Agency agency;
    private Company company;
    private Offer offer;

    public OfferFormDialog(JFrame parent, Company company) {
        this(parent, company, null);
    }

    public OfferFormDialog(JFrame parent, Company company, Offer offer) {
        super(parent, "Formulario de Oferta", true);
        setResizable(false);
        this.company = company;
        this.offer = offer;

        setSize(400, 350);
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

        contentPanel.add(new JLabel("Empresa:"), gbc);
        companyComboBox = new JComboBox<>();
        for (Company currentCompany : agency.getCompanyManager().getCompanies()) {
            companyComboBox.addItem(currentCompany.getName());
        }
        gbc.gridx = 1;
        contentPanel.add(companyComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Rama:"), gbc);
        branchComboBox = new JComboBox<>(Branch.BRANCHES);
        branchComboBox.setPreferredSize(textFieldSize);
        gbc.gridx = 1;
        contentPanel.add(branchComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Salario:"), gbc);
        salaryField = new JTextField();
        salaryField.setPreferredSize(textFieldSize);
        gbc.gridx = 1;
        contentPanel.add(salaryField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOffer();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        if (offer != null) {
            branchComboBox.setSelectedItem(offer.getBranch());
            salaryField.setText(String.valueOf(offer.getSalary()));
        }
        
        setVisible(true);
    }

    private void saveOffer() {
        try {
            String branch = (String) branchComboBox.getSelectedItem();
            double salary = Double.parseDouble(salaryField.getText());

            if (offer == null) {
                company.createOffer(branch, salary);
            } else {
                offer.setBranch(branch);
                offer.setSalary(salary);
            }
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El salario debe ser un número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la oferta: " + ex.getMessage());
        }
    }
}
