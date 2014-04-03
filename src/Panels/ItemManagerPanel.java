package Panels;

import javax.swing.*;
import Game_System.Item;
import Scene_Manager.SceneManager;
import TableModels.ItemTableModel;
import UserIO.WindowComm;
import Windows.*;
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
	
	// Constants
	private static final int YES	= 0;
	
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
		
		m_EdtItmBtn = new JButton("Edit Item");
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
	
	/*********************************************************************\
	 * Contains all functionality of the Item Manager Panel				 *
	\*********************************************************************/
	
	/**
	 * Gets the currently selected Item.
	 * 
	 * @return	The Currently Selected Item in the table.
	 */
	private Item getSelectedItem( )
	{
		return m_ItemTableModel.getItemAt( m_ItemTable.getSelectedRow( ) );
	}
	
	/** 
	 * Generates a new item and opens the edit item window to modify the item.
	 */
	private void addItem( )
	{
		Item m_NewItem = new Item( "<Item Title>", "<Item Description>" );
		openEditItemWindow( m_NewItem, true );
	}
	
	private void removeItem( )
	{
		Item m_ItemToRemove = getSelectedItem( );
		
		if( null != m_ItemToRemove )
		{
			int iResult = m_WindowComm.getYesNo( "Are you sure you want to remove \"" + 
												 m_ItemToRemove.getName( ) + "?\"", "Remove" );
			
			if( YES == iResult )
				m_ItemTableModel.removeItem( m_ItemToRemove.getName( ) );
		}
		else
			m_WindowComm.displayMessage( "No Item selected!" );
	}
	
	/**
	 * Opens up the EditItemWindow with a flag to say that it's not a new item being added.
	 * This allows the user to modify the Item with the ability to leave the name the same.
	 * If no item is selected, this function fails.
	 */
	private void editItem( )
	{
		Item m_ItemToEdit = m_ItemTableModel.getItemAt( m_ItemTable.getSelectedRow( ) );
		
		if( null != m_ItemToEdit )
			openEditItemWindow( m_ItemToEdit, false );
		else
			m_WindowComm.displayMessage( "No Item selected!" );
	}
	
	/**
	 * Function called externally to save an item.
	 * If the name is unique, it will skip adding the item and just fire
	 * changes on the model.
	 * 
	 * @param itemToSave The Item to save.
	 */
	public void saveItem( Item itemToSave )
	{
		if( m_ItemTableModel.isNameUnique( itemToSave.getName( ) ) )
			m_ItemTableModel.addItem( itemToSave );
		else
			m_ItemTableModel.fireTableDataChanged( );
	}
	
	/**
	 * Function to check to see if an Item can be added.
	 * 
	 * @param m_ItemToAdd	The Item to check.
	 * @return				True if the name of the item to add is unique.
	 */
	public boolean canAddItem( Item m_ItemToAdd )
	{
		return m_ItemTableModel.isNameUnique( m_ItemToAdd.getName( ) );
	}
	
	/**
	 * Function to launch and run the edit item window.  This also sets
	 * the buttons of this window to disabled so they can't be clicked again.
	 * 
	 * @param itemToEdit	The Item to load the edit item window with.
	 * @param bNewItem		Flag to determine if the Item is a new item or not.  This
	 * 						changes how the edit window handles the saving of the item.
	 */
	private void openEditItemWindow( Item itemToEdit, boolean bNewItem )
	{
		toggleSaveEditEnabled(false);
		EditItemWindow esw = new EditItemWindow ( itemToEdit, this, bNewItem );
		esw.run();
	}
	
	/**
	 * Essentially sets the buttons to be enabled
	 */
	public void editItemWindowHasClosed()
	{
		toggleSaveEditEnabled(true);
	}
	
	/** 
	 * Enables or Disables the buttons depending on the boolean flag passed in.
	 * 
	 * @param b_Toggle	The Boolean flag to set the buttons with.
	 */
	private void toggleSaveEditEnabled(boolean b_Toggle)
	{
		m_AddItmBtn.setEnabled(b_Toggle);
		m_RmvItmBtn.setEnabled(b_Toggle);
		m_EdtItmBtn.setEnabled(b_Toggle);
	}
}
