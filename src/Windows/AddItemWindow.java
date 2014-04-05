package Windows;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Game_System.Item;
import Scene_Manager.Scene;
import TableModels.ItemTableModel;
import TableModels.SceneTableModel;
import UserIO.WindowComm;
import Windows.AddConnectionWindow.ButtonHandler;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Font;

public class AddItemWindow extends JFrame
{
	private WindowComm m_WindowComm;
	private EditSceneWindow m_Parent;
	private JList <Item>m_ItemList;
	private JScrollPane m_ScenesScrollPane;
	private JButton btnCancel;
	private DefaultListModel<Item> m_ItemListModel;
	private JButton btnConnectItem;
	private Choice m_ItemTypeSelection;
	
	
	public class ButtonHandler implements ActionListener {
		private AddItemWindow window;

		public ButtonHandler(AddItemWindow window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(btnConnectItem))
				connectItemButtonClicked();
			else if (e.getSource().equals(btnCancel))
				closeWindow();
		}
	}
	
	
	public AddItemWindow( ItemTableModel itemTable, EditSceneWindow parent)
	{
		m_WindowComm = new WindowComm(this);
		getContentPane().setLayout(null);
		ButtonHandler btnHandler = new ButtonHandler(this);

		m_Parent = parent;
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}
		});
		
		setBounds( 100, 100, 390, 552 );
		Point parentLocation = m_Parent.getLocation();
		double parentX = parentLocation.getX();
		double parentY = parentLocation.getY();
		
		setLocation((int)(parentX + 125),(int)(parentY + 125));
		
		m_ItemListModel = new DefaultListModel<Item>();
		
		for (int i = 0; i < itemTable.getRowCount(); i++)
			m_ItemListModel.addElement(itemTable.getItemAt(i));
		
		m_ScenesScrollPane = new JScrollPane();
		m_ScenesScrollPane.setBounds(10, 54, 178, 415);
		getContentPane().add(m_ScenesScrollPane);
		
		m_ItemList = new JList<Item>();
		m_ItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m_ItemList.setModel(m_ItemListModel);
		m_ScenesScrollPane.setViewportView(m_ItemList);
		
		btnConnectItem = new JButton("Connect Item");
		btnConnectItem.addActionListener(btnHandler);
		btnConnectItem.setBounds(198, 54, 166, 23);
		getContentPane().add(btnConnectItem);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(btnHandler);
		btnCancel.setBounds(198, 93, 166, 23);
		getContentPane().add(btnCancel);
		
		m_ItemTypeSelection = new Choice();
		m_ItemTypeSelection.setBounds(198, 132, 166, 20);
		getContentPane().add(m_ItemTypeSelection);
		
		JLabel lblConnectItemTo = new JLabel("Connect Item to Scene");
		lblConnectItemTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConnectItemTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnectItemTo.setBounds(10, 11, 354, 14);
		getContentPane().add(lblConnectItemTo);
		
		m_ItemTypeSelection.add("Item drops on this scene");
		m_ItemTypeSelection.add("Item unlocks this scene");
	}
	
	public void connectItemButtonClicked() 
	{
		int connectFlag;
		Item toConnect = m_ItemList.getSelectedValue();
		if (toConnect != null)
		{
			connectFlag = m_Parent.connectItem(toConnect, m_ItemTypeSelection.getSelectedItem());
			switch (connectFlag)
			{
			case 0:
				closeWindow();
				break;
			case 1:
				m_WindowComm.displayMessage("That item is already dropped somewhere else.");
				break;
			case 2:
				m_WindowComm.displayMessage("You can't have an item drop on the scene unlocked by it.");
				break;
			}
		}
	}
	
	private void closeWindow()
	{
		m_Parent.addItemWidnowHasClosed();
		dispose();
	}
	
	public void run()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					setVisible(true);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}
