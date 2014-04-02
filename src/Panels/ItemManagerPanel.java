package Panels;

import javax.swing.*;
import Game_System.Item;
import Scene_Manager.SceneManager;
import TableModels.ItemTableModel;
import UserIO.WindowComm;
import Windows.EditItemWindow;
import Windows.MainWindow;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel used for monitoring/managing Items.
 *
 * @author	Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class ItemManagerPanel extends JPanel {
	/* Private Variables */
	private JTable 			m_ItemTable;
	private JButton 		m_AddItmBtn;
	private JButton 		m_RmvItmBtn;
	private JButton 		m_EdtItmBtn;
	private JScrollPane 	scrollPane;
	private WindowComm 		m_WindowComm;
	private SceneManager 	m_SceneManager;
	private ItemTableModel 	m_ItemTableModel;
	
	public class ItemManagerButtonHandler implements ActionListener {
		private ItemManagerPanel parent;

		public ItemManagerButtonHandler(ItemManagerPanel parent) {
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(m_RmvItmBtn))
			{
				removeItem( );
			}
			else if( e.getSource( ).equals( m_AddItmBtn ) )
			{
				addItem( );
			}
			else if( e.getSource( ).equals( m_EdtItmBtn ) )
			{
				editItem( );
			}
		}
	}

	/**
	 * Create the panel.
	 */
	public ItemManagerPanel( ItemTableModel itemsTable, MainWindow mWindow, SceneManager sManager )
	{
		ItemManagerButtonHandler btnHandler = new ItemManagerButtonHandler(this);
		m_WindowComm = new WindowComm(mWindow);
		m_SceneManager = sManager;
		m_ItemTableModel = itemsTable;
		
		m_AddItmBtn = new JButton("Add Item");
		m_AddItmBtn.setBounds(456, 8, 103, 23);
		m_AddItmBtn.addActionListener(btnHandler);
		setLayout(null);
		add(m_AddItmBtn);

		m_RmvItmBtn = new JButton("Remove Item");
		m_RmvItmBtn.setBounds(456, 41, 103, 23);
		m_RmvItmBtn.addActionListener(btnHandler);
		add(m_RmvItmBtn);
		
		m_EdtItmBtn = new JButton("Edit Scene");
		m_EdtItmBtn.setToolTipText("Edit the selected scene.");
		m_EdtItmBtn.setBounds(456, 75, 103, 23);
		m_EdtItmBtn.addActionListener(btnHandler);
		add(m_EdtItmBtn);
		
		m_ItemTableModel = itemsTable;
		m_ItemTable = new JTable (m_ItemTableModel);
		m_ItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m_ItemTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		m_ItemTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		m_ItemTable.setFillsViewportHeight(true);
		m_ItemTable.setColumnSelectionAllowed(false);
		
		scrollPane = new JScrollPane(m_ItemTable);
		scrollPane.setBounds(10, 11, 436, 432);
		add(scrollPane);
		scrollPane.setViewportView(m_ItemTable);	

	}
	
	/**
	 * Contains all functionality of the Item Manager Panel
	 */
	private void addItem( )
	{
		// m_WindowComm.displayMessage( "Add Item Button Clicked!" );
		// TODO: program addItem functionality.
		openEditItemWindow( null );
	}
	
	private void removeItem( )
	{
		m_WindowComm.displayMessage( "Remove Item Button Clicked!" );
		// TODO: program removeItem functionality.
	}
	
	private void editItem( )
	{
		
	}
	
	private void openEditItemWindow(Item itemToEdit)
	{
		toggleSaveEditEnabled(false);
		EditItemWindow esw = new EditItemWindow ( this );
		esw.run();
	}
	
	public void editItemWindowHasClosed()
	{
		toggleSaveEditEnabled(true);
	}
	
	private void toggleSaveEditEnabled(boolean b_Toggle)
	{
		m_AddItmBtn.setEnabled(b_Toggle);
		m_RmvItmBtn.setEnabled(b_Toggle);
	}
}
