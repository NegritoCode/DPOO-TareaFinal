package gui.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utils.constants.Sector;
import java.awt.GridLayout;

public class CompanyFilterDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public CompanyFilterDialog(CompanyManagerScreen screen, ArrayList<String> selectedSectors) {
		super(screen, "Seleccionar Sector", true);
		setSize(300, 400);
		setLocationRelativeTo(this);
		getContentPane().setLayout(new BorderLayout());

		JPanel checkboxPanel = new JPanel();
		checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
		JCheckBox[] checkboxes = new JCheckBox[Sector.SECTORS.length];
		for (int i = 0; i < Sector.SECTORS.length; i++) {
			checkboxes[i] = new JCheckBox(Sector.SECTORS[i]);
			checkboxes[i].setSelected(selectedSectors.contains(Sector.SECTORS[i]));
			checkboxPanel.add(checkboxes[i]);
		}

		JPanel buttonPanel = new JPanel();
		JButton markAllButton = new JButton("Todo");
		markAllButton.setBackground(new Color(33, 150, 243));
		markAllButton.setForeground(Color.WHITE);
		markAllButton.setFont(new Font("Roboto", Font.BOLD, 18));
		markAllButton.setBorderPainted(false);
		markAllButton.setFocusPainted(false);

		JButton unmarkAllButton = new JButton("Desmarcar");
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
		buttonPanel.setLayout(new GridLayout(1, 2, 5, 0));

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
				screen.renderCompanies();
				dispose();
			}
		});

		getContentPane().add(new JScrollPane(checkboxPanel), BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(applyButton, BorderLayout.SOUTH);
		setVisible(true);
	}

}
