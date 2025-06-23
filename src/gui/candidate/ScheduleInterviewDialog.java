package gui.candidate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import gui.components.MButton;
import gui.components.MTable;
import logic.Agency;
import logic.candidate.Candidate;
import logic.company.Company;
import logic.company.Offer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScheduleInterviewDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public ScheduleInterviewDialog(JFrame parent, Candidate candidate, Agency agency) {
		super(parent, "Agendar Entrevista", true);
		setSize(600, 400);
		setLocationRelativeTo(parent);

		String[] columnNames = { "Empresa", "Rama", "Salario" };
		DefaultTableModel offersTableModel = new DefaultTableModel(columnNames, 0);
		JTable offersTable = new MTable(offersTableModel);
        ArrayList<Offer> offers = agency.getOffersByBranch(candidate.getBranch());
		for (Offer offer : offers) {
			Company company = agency.getCompanyManager().getCompanyById(offer.getCompanyId());
			offersTableModel.addRow(new Object[] { company.getName(), offer.getBranch(), offer.getSalary() });
		}

		JScrollPane scrollPane = new JScrollPane(offersTable);
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton acceptButton = new MButton("Aceptar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = offersTable.getSelectedRow();
				if (selectedRow != -1) {
					String companyName = (String) offersTableModel.getValueAt(selectedRow, 0);
					Company company = agency.getCompanyManager().getCompanyByName(companyName);
					Offer offer = offers.get(selectedRow);
					agency.createInterview(candidate.getCid(), company.getId(), offer.getId());
					JOptionPane.showMessageDialog(parent, "Entrevista agendada exitosamente.");
					dispose();
				} else {
					JOptionPane.showMessageDialog(parent, "Seleccione una oferta para agendar.");
				}
			}
		});
		buttonPanel.add(acceptButton);

		JButton cancelButton = new MButton("Cancelar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(cancelButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}
}
