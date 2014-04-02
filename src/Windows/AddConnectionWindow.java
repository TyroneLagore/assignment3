package Windows;

import java.awt.EventQueue;

import javax.swing.*;

import Panels.SceneManagerPanel;
import Scene_Manager.*;
import TableModels.SceneTableModel;
import UserIO.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddConnectionWindow extends JFrame 
{
	private WindowComm m_WindowComm;
	private EditSceneWindow m_Parent;
	private JList <Scene>m_ScenesJList;
	private JScrollPane m_ScenesScrollPane;
	private JButton btnCancel;
	private DefaultListModel<Scene> m_SceneListModel;
	private JButton btnConnectScene;
	
	
	public class ButtonHandler implements ActionListener {
		private AddConnectionWindow window;

		public ButtonHandler(AddConnectionWindow addConnectionWindow) {
			this.window = addConnectionWindow;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(btnConnectScene))
				connectSceneClicked();
			else if (e.getSource().equals(btnCancel))
				closeWindow();
		}
	}
	
	public AddConnectionWindow( Scene scene, SceneTableModel sceneTable, EditSceneWindow parent)
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
		
		m_SceneListModel = new DefaultListModel<Scene>();
		
		for (int i = 0; i < sceneTable.getRowCount(); i++)
			m_SceneListModel.addElement(sceneTable.getSceneAt(i));
		
		m_ScenesScrollPane = new JScrollPane();
		m_ScenesScrollPane.setBounds(10, 34, 210, 415);
		getContentPane().add(m_ScenesScrollPane);
		
		m_ScenesJList = new JList<Scene>();
		m_ScenesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m_ScenesJList.setModel(m_SceneListModel);
		m_ScenesScrollPane.setViewportView(m_ScenesJList);
		
		btnConnectScene = new JButton("Connect Scene");
		btnConnectScene.addActionListener(btnHandler);
		btnConnectScene.setBounds(230, 32, 130, 23);
		getContentPane().add(btnConnectScene);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(btnHandler);
		btnCancel.setBounds(230, 66, 130, 23);
		getContentPane().add(btnCancel);

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
	
	private void connectSceneClicked()
	{
		m_Parent.connectScene(m_ScenesJList.getSelectedValue());
		closeWindow();
	}
	
	private void closeWindow()
	{
		setVisible(false);
		m_Parent.addConnectionWindowHasClosed();
	}
}
