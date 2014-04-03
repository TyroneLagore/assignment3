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

public class AddItemWindow extends JFrame
{
	private WindowComm m_WindowComm;
	private EditSceneWindow m_Parent;
	private JList <Item>m_ItemList;
	private JScrollPane m_ScenesScrollPane;
	private JButton btnCancel;
	private DefaultListModel<Item> m_ItemListModel;
	private JButton btnConnectItem;
	
	public class ButtonHandler implements ActionListener {
		private AddItemWindow window;

		public ButtonHandler(AddItemWindow addItemWindow) {
			this.window = addItemWindow;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(btnConnectItem))
				
			else if (e.getSource().equals(btnCancel))
				closeWindow();
		}
	}
	
	public AddItemWindow( ItemTableModel itemTable, EditSceneWindow parent)
	{
		m_WindowComm = new WindowComm(this);
		getContentPane().setLayout(null);

		m_Parent = parent;
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				closeWindow();
			}
		});
		ButtonHandler btnHandler = new ButtonHandler(this);
		
		setBounds( 100, 100, 390, 552 );
		Point parentLocation = m_Parent.getLocation();
		double parentX = parentLocation.getX();
		double parentY = parentLocation.getY();
		
		setLocation((int)(parentX + 125),(int)(parentY + 125));
		
		m_ItemListModel = new DefaultListModel<Item>();
		
		for (int i = 0; i < itemTable.getRowCount(); i++)
			m_ItemListModel.addElement(itemTable.getItemAt(i));
		
		m_ScenesScrollPane = new JScrollPane();
		m_ScenesScrollPane.setBounds(10, 34, 210, 415);
		getContentPane().add(m_ScenesScrollPane);
		
		m_ItemList = new JList<Item>();
		m_ItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m_ItemList.setModel(m_ItemListModel);
		m_ScenesScrollPane.setViewportView(m_ItemList);
		
		btnConnectItem = new JButton("Connect Item");
		btnConnectItem.addActionListener(btnHandler);
		btnConnectItem.setBounds(230, 32, 130, 23);
		getContentPane().add(btnConnectItem);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(btnHandler);
		btnCancel.setBounds(230, 66, 130, 23);
		getContentPane().add(btnCancel);

	}
	
	private void closeWindow() {
		// TODO Auto-generated method stub
		
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
