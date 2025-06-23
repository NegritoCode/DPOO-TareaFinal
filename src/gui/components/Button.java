package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
	private static final long serialVersionUID = 1L;
	public Button(String text, ActionListener actionListener) {
        super(text);
        setBackground(new Color(33, 150, 243));
        setForeground(Color.WHITE);
        setFont(new Font("Roboto", Font.BOLD, 16));
        setBorderPainted(false);
        setFocusPainted(false);
        if (actionListener != null) {
            addActionListener(actionListener);
        }
    }
}
