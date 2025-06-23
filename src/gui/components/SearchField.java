package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchField extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton searchButton;

	public SearchField(ActionListener searchAction) {
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		textField = new JTextField(20);
		textField.setFont(new Font("Roboto", Font.PLAIN, 14));
		textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		searchButton = new MButton("Buscar", "small", searchAction);

		add(textField);
		add(searchButton);
	}

	public void addActionListener(ActionListener action) {
		searchButton.addActionListener(action);
	}

	public String getText() {
		return textField.getText().trim().toLowerCase();
	}

	public void setText(String text) {
		textField.setText(text);
	}
}
