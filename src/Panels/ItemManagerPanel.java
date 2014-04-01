package Panels;

import javax.swing.*;

import Game_System.Item;
import Scene_Manager.SceneManager;
import TableModels.ItemTableModel;
import UserIO.WindowComm;
import Windows.MainWindow;
import net.miginfocom.swing.MigLayout;

import java.awt.event.*;

//This better fucking work
/**
 * Panel used for monitoring/managing Items.
 *
 * @author	Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class ItemManagerPanel extends JPanel {
	/* Private Variables */
	private JTable m_ItemTable;
	private JButton m_AddItmBtn;
	private JButton m_RmvItmBtn;
	private JScrollPane scrollPane;
	private WindowComm m_WindowComm;
	private SceneManager m_SceneManager;
	
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
			}
			/* TODO button handling goes here */
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
		
		m_AddItmBtn = new JButton("Add Item");
		m_AddItmBtn.setBounds(320, 11, 97, 23);
		m_AddItmBtn.addActionListener(btnHandler);
		setLayout(null);
		add(m_AddItmBtn);

		m_RmvItmBtn = new JButton("Remove Item");
		m_RmvItmBtn.setBounds(320, 45, 97, 23);
		m_RmvItmBtn.addActionListener(btnHandler);
		add(m_RmvItmBtn);
	
		itemsTable.testPopulate( );
		
		m_ItemTable = new JTable (itemsTable);
		
		scrollPane = new JScrollPane(m_ItemTable);
		scrollPane.setBounds(10, 11, 298, 251);
		add(scrollPane);
		scrollPane.setViewportView(m_ItemTable);	

	}
}
