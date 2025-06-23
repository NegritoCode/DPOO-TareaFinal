package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FilterDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Boolean> filter;

	public FilterDialog(JFrame parent, HashMap<String, Boolean> initialFilter) {
		super(parent, "Seleccionar Filtros", true);
		filter = new HashMap<>(initialFilter);

		setSize(300, 400);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout());

		JPanel checkboxPanel = new JPanel();
		checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
		final JCheckBox[] checkboxes = new JCheckBox[filter.size()];
		int index = 0;

		for (String key : filter.keySet()) {
			checkboxes[index] = new JCheckBox(key);
			checkboxes[index].setSelected(filter.get(key));
			checkboxPanel.add(checkboxes[index]);
			index++;
		}

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2, 5, 0));

		JButton markAllButton = new MButton("Todo", "small", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox : checkboxes) {
					checkbox.setSelected(true);
				}
			}
		});
		buttonPanel.add(markAllButton);

		JButton unmarkAllButton = new MButton("Desmarcar", "small", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox : checkboxes) {
					checkbox.setSelected(false);
				}
			}
		});
		buttonPanel.add(unmarkAllButton);

		JButton applyButton = new MButton("Aplicar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < checkboxes.length; i++) {
					filter.put(checkboxes[i].getText(), checkboxes[i].isSelected());
				}
				dispose();
			}
		});

		getContentPane().add(new JScrollPane(checkboxPanel), BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(applyButton, BorderLayout.SOUTH);
		setVisible(true);
	}

	public HashMap<String, Boolean> getFilter() {
		return filter;
	}

	public static HashMap<String, Boolean> listToFilter(String[] list) {
		HashMap<String, Boolean> filter = new HashMap<String, Boolean>();
		for (String key : list) {
			filter.put(key, new Boolean(true));
		}
		return filter;
	}
}
