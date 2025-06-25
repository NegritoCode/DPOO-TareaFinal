package gui.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

import gui.components.MButton;
import logic.Agency;
import logic.GlobalAgency;
import logic.company.Company;
import utils.constants.Sector;

public class CompanyFormDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField nameField, addressField, phoneField;
	private JComboBox<String> sectorComboBox;
	private Agency agency;
	private Company company;

	public CompanyFormDialog(JFrame parent, Company company) {
		super(parent, "Formulario de Empresa", true);
		setLocationRelativeTo(parent);
		this.agency = GlobalAgency.getInstance();
		this.company = company;

		initUI();

		if (company != null) {
			nameField.setText(company.getName());
			addressField.setText(company.getAddress());
			phoneField.setText(company.getPhone());
			sectorComboBox.setSelectedItem(company.getSector());
		}
	}

	private void initUI() {
		setSize(400, 243);
		getContentPane().setLayout(new BorderLayout(10, 10));

		JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 10));
		contentPanel.add(formPanel, BorderLayout.CENTER);

		formPanel.add(new JLabel("Nombre:"));
		nameField = new JTextField();
		formPanel.add(nameField);

		formPanel.add(new JLabel("Dirección:"));
		addressField = new JTextField();
		formPanel.add(addressField);

		formPanel.add(new JLabel("Teléfono:"));
		phoneField = new JTextField();
		formPanel.add(phoneField);

		formPanel.add(new JLabel("Sector:"));
		sectorComboBox = new JComboBox<>();
		for (String sector : Sector.SECTORS) {
			sectorComboBox.addItem(sector);
		}
		formPanel.add(sectorComboBox);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton saveButton = new MButton("Guardar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveCompany();
			}
		});
		JButton cancelButton = new MButton("Cancelar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	private void saveCompany() {
		try {
			String name = nameField.getText();
			String address = addressField.getText();
			String phone = phoneField.getText();
			String sector = (String) sectorComboBox.getSelectedItem(); // Obtener valor de JComboBox

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
