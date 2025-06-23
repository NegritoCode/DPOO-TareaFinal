package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BigButton extends JButton {
	private static final long serialVersionUID = 1L;

	public BigButton(String text, ActionListener action) {
		super(text);
		setFont(new Font("Arial", Font.BOLD, 14));
		setBackground(new Color(33, 150, 243));
		setForeground(Color.WHITE);
		setFocusPainted(false);
		setBorder(BorderFactory.createEmptyBorder());
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addActionListener(action);
	}
}
