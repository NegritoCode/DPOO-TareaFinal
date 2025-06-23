package gui.reports;

import gui.components.FilterDialog;
import gui.components.MButton;
import gui.components.MTable;
import gui.components.SearchField;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class ReportPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MTable table;
	private SearchField searchField;
	private DefaultTableModel tableModel;
	private HashMap<String, Boolean> filterOptions;
	private ActionListener listener;

	public ReportPanel(String title, String[] filterList, String[] columnNames, ActionListener listener) {
		setBounds(288, 11, 609, 434);
		setLayout(null);
		filterOptions = FilterDialog.listToFilter(filterList);
		this.listener = listener;

		JLabel titleTab = new JLabel(title);
		titleTab.setHorizontalAlignment(SwingConstants.CENTER);
		titleTab.setBounds(175, 11, 264, 26);
		titleTab.setBorder(UIManager.getBorder("EditorPane.border"));
		titleTab.setFont(new Font("Tahoma", Font.PLAIN, 21));
		add(titleTab);

		searchField = new SearchField(listener);
		searchField.setBounds(228, 37, 369, 39);
		add(searchField);

		JButton filterBtn = new MButton("Filtro", "small", null);
		filterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterOptions = (new FilterDialog(null, filterOptions)).getFilter();
				render();
			}
		});
		filterBtn.setBounds(12, 37, 105, 27);
		add(filterBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 589, 347);
		add(scrollPane);
        
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new MTable(tableModel);
		scrollPane.setViewportView(table);
	}

	public MTable getTable() {
		return table;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	public String getSearchText() {
		return searchField.getText();
	}

	public HashMap<String, Boolean> getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(HashMap<String, Boolean> filterOptions) {
		this.filterOptions = filterOptions;
	}
	
	public void render () {
		listener.actionPerformed(null);
	}
}
