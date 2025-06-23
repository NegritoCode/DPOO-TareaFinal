package gui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class MTable extends JTable {

	private static final long serialVersionUID = 1L;
	public MTable (TableModel tableModel) {
		super(tableModel);
		setFont(new Font("Roboto", Font.PLAIN, 14));
		setRowHeight(25);
		setGridColor(new Color(224, 224, 224));
		setSelectionBackground(new Color(33, 150, 243));
		setSelectionForeground(Color.WHITE);
		isCellEditable(0,0);
	}
	public boolean isCellEditable(int row, int column){
		return false;
	}
}
