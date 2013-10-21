package gui.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


public class ManageKeywordsPanel extends JPanel implements TableModel {
	private static final long serialVersionUID = 9035836985008417742L;

	JTable table;
	JTextField keywordTextField;
	JButton addButton;

	ArrayList<String> keywords = new ArrayList<String>();

	ManageKeywordsPanelHandler handler;


	/**
	 * Sets up the panel and adds action listener to the button
	 */
	public ManageKeywordsPanel(ManageKeywordsPanelHandler handler) {
		this.handler = handler;

		setName("Administrer søkeord");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(createTable());
		
		AddKeywordActionListener actionListener = new AddKeywordActionListener(this);

		keywordTextField = new JTextField();
		keywordTextField.addActionListener(actionListener);
		keywordTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, keywordTextField.getPreferredSize().height));

		addButton = new JButton("Legg til");
		addButton.addActionListener(actionListener);

		JPanel hPanel = new JPanel();
		hPanel.setLayout(new BoxLayout(hPanel, BoxLayout.X_AXIS));
		hPanel.add(keywordTextField);
		hPanel.add(addButton);
		this.add(hPanel);
	}


	/**
	 * Creates the table component and sets the table model to this.
	 */
	private JComponent createTable() {
		table = new JTable(this);

		new ButtonColumn(table, new AbstractAction() {	
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				//JTable table = (JTable)e.getSource();
				int row = Integer.valueOf(e.getActionCommand());
				String keyword = getValueAt(row, 0).toString();
				handler.deleteKeyword(keyword);
			}
		}, 1);
		
		return new JScrollPane(table);
	}


	/**
	 * Action listener for the "Add keyword" button
	 */
	private class AddKeywordActionListener implements ActionListener {
		private ManageKeywordsPanel view;

		public AddKeywordActionListener(ManageKeywordsPanel view) {
			this.view = view;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			String keyword = view.keywordTextField.getText();
			
			if (keyword.length() >= 0) {
				if (view.handler.addKeyword(keyword)) {
					view.keywordTextField.setText("");
					refreshTable();
				}
			}
		}
	}


	/* ***************************************************************************
	 * Below here is mostly implementations of TableModel. The
	 * function names should be self explanatory. Or see
	 * http://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableModel.html
	 */

	public String[] columns = {"Søkeord", "Delete"};

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int c) {
		return columns[c];
	}

	@Override
	public int getRowCount() {
		return handler.getKeywords().size();
	}

	@Override
	public Object getValueAt(int r, int c) {
		if (c == 1) {
			return "delete";
		} else {
			return handler.getKeywords().get(r);
		}
	}

	private LinkedList<TableModelListener> tableModelListeners = new LinkedList<TableModelListener>();

	@Override
	public void addTableModelListener(TableModelListener l) {
		tableModelListeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		tableModelListeners.remove(l);
	}

	public void refreshTable() {
		TableModelEvent e = new TableModelEvent(this);

		for (TableModelListener l : tableModelListeners) {
			l.tableChanged(e);
		}
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {}
}



/**
 *  @url http://tips4java.wordpress.com/2009/07/12/table-button-column/
 *
 *  The ButtonColumn class provides a renderer and an editor that looks like a
 *  JButton. The renderer and editor will then be used for a specified column
 *  in the table. The TableModel will contain the String to be displayed on
 *  the button.
 *
 *  The button can be invoked by a mouse click or by pressing the space bar
 *  when the cell has focus. Optionally a mnemonic can be set to invoke the
 *  button. When the button is invoked the provided Action is invoked. The
 *  source of the Action will be the table. The action command will contain
 *  the model row number of the button that was clicked.
 *
 */
class ButtonColumn extends AbstractCellEditor
	implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
{
	private static final long serialVersionUID = -4946699871336901488L;

	private JTable table;
	private Action action;
	private int mnemonic;
	private Border originalBorder;
	private Border focusBorder;

	private JButton renderButton;
	private JButton editButton;
	private Object editorValue;
	private boolean isButtonColumnEditor;

	/**
	 *  Create the ButtonColumn to be used as a renderer and editor. The
	 *  renderer and editor will automatically be installed on the TableColumn
	 *  of the specified column.
	 *
	 *  @param table the table containing the button renderer/editor
	 *  @param action the Action to be invoked when the button is invoked
	 *  @param column the column to which the button renderer/editor is added
	 */
	public ButtonColumn(JTable table, Action action, int column)
	{
		this.table = table;
		this.action = action;

		renderButton = new JButton();
		editButton = new JButton();
		editButton.setFocusPainted( false );
		editButton.addActionListener( this );
		originalBorder = editButton.getBorder();
		setFocusBorder( new LineBorder(Color.BLUE) );

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer( this );
		columnModel.getColumn(column).setCellEditor( this );
		// table.addMouseListener( this );
	}


	/**
	 *  Get foreground color of the button when the cell has focus
	 *
	 *  @return the foreground color
	 */
	public Border getFocusBorder()
	{
		return focusBorder;
	}

	/**
	 *  The foreground color of the button when the cell has focus
	 *
	 *  @param focusBorder the foreground color
	 */
	public void setFocusBorder(Border focusBorder)
	{
		this.focusBorder = focusBorder;
		editButton.setBorder( focusBorder );
	}

	public int getMnemonic()
	{
		return mnemonic;
	}

	/**
	 *  The mnemonic to activate the button when the cell has focus
	 *
	 *  @param mnemonic the mnemonic
	 */
	public void setMnemonic(int mnemonic)
	{
		this.mnemonic = mnemonic;
		renderButton.setMnemonic(mnemonic);
		editButton.setMnemonic(mnemonic);
	}

	@Override
	public Component getTableCellEditorComponent(
		JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (value == null)
		{
			editButton.setText( "" );
			editButton.setIcon( null );
		}
		else if (value instanceof Icon)
		{
			editButton.setText( "" );
			editButton.setIcon( (Icon)value );
		}
		else
		{
			editButton.setText( value.toString() );
			editButton.setIcon( null );
		}
		
		this.editorValue = value;
		return editButton;
	}

	@Override
	public Object getCellEditorValue()
	{
		return editorValue;
	}

//
//  Implement TableCellRenderer interface
//
	public Component getTableCellRendererComponent(
		JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if (isSelected)
		{
			renderButton.setForeground(table.getSelectionForeground());
	 		renderButton.setBackground(table.getSelectionBackground());
		}
		else
		{
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if (hasFocus)
		{
			renderButton.setBorder( focusBorder );
		}
		else
		{
			renderButton.setBorder( originalBorder );
		}

//		renderButton.setText( (value == null) ? "" : value.toString() );
		if (value == null)
		{
			renderButton.setText( "" );
			renderButton.setIcon( null );
		}
		else if (value instanceof Icon)
		{
			renderButton.setText( "" );
			renderButton.setIcon( (Icon)value );
		}
		else
		{
			renderButton.setText( value.toString() );
			renderButton.setIcon( null );
		}

		return renderButton;
	}

//
//  Implement ActionListener interface
//
	/*
	 *	The button has been pressed. Stop editing and invoke the custom Action
	 */
	public void actionPerformed(ActionEvent e)
	{
		int row = table.convertRowIndexToModel( table.getEditingRow() );
		fireEditingStopped();

		//  Invoke the Action

		ActionEvent event = new ActionEvent(
			table,
			ActionEvent.ACTION_PERFORMED,
			"" + row);
		action.actionPerformed(event);
	}

//
//  Implement MouseListener interface
//
	/*
	 *  When the mouse is pressed the editor is invoked. If you then then drag
	 *  the mouse to another cell before releasing it, the editor is still
	 *  active. Make sure editing is stopped when the mouse is released.
	 */
    public void mousePressed(MouseEvent e)
    {
    	if (table.isEditing()
		&&  table.getCellEditor() == this)
			isButtonColumnEditor = true;
    }

    public void mouseReleased(MouseEvent e)
    {
    	if (isButtonColumnEditor
    	&&  table.isEditing())
    		table.getCellEditor().stopCellEditing();

		isButtonColumnEditor = false;
    }

    public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
