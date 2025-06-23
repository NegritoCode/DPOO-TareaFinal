package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MButton extends JButton {
	private static final long serialVersionUID = 1L;

	public MButton(String text, String type, ActionListener actionListener) {
		super(text);
		setBackground(new Color(33, 150, 243));
		setForeground(Color.WHITE);

		if (type.equals("small")) {
			setFont(new Font("Roboto", Font.BOLD, 12));
		} else if (type.equals("normal")) {
			setFont(new Font("Roboto", Font.BOLD, 16));
		} else {
			throw new IllegalArgumentException("Invalid button type " + type);
		}
		
		setBorderPainted(false);
		setFocusPainted(false);
		if (actionListener != null) {
			addActionListener(actionListener);
		}
	}

	public MButton(String text, ActionListener actionListener) {
		this(text, "normal", actionListener);
	}
}
