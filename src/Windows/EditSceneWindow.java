package Windows;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Game_System.Item;
import Panels.SceneManagerPanel;
import Scene_Manager.Scene;
import Scene_Manager.SceneManager;
import TableModels.SceneTableModel;
import UserIO.WindowComm;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

/**
 * EditSceneWindow
 * Allows for the editting of an individual scene, passed in upon creation.
 * @author Tyrone Lagore
 */

public class EditSceneWindow extends JFrame {

	private Scene m_Scene;
	private SceneManagerPanel m_Parent;
	private WindowComm m_WindowComm;
	private JLabel lblLargeTitle;
	private JButton btnCancel;
	private JButton btnSaveScene;
	private JButton btnRemoveConnection;
	private JButton btnConnectScene;
	private JLabel lblDescription;
	private JLabel lblConnectedScenes;
	private JTextArea m_DescTextArea;
	private JScrollPane m_DescScrollPane;
	private JScrollPane m_ConnectedScenesScrollPane;
	private JList <Scene>m_ConnectedScenesJList;
	private DefaultListModel<Scene> m_ConnectedScenesModel;
	private JLabel lblTitle;
	private JTextField m_TitleTextField;
	private JTextField []m_ConnectionLabels;
	private ArrayList<Scene> m_Connections;
	private static final int NUM_JLABELS = 4;
	private JLabel lblConnectionLabel;
	private JTextField m_ItemUnlocsTextField;
	private JTextField m_ItemDroppedTextField;
	private JButton btnRemoveDropItem;
	private JLabel lblItemRequiredTo;
	private SceneManager m_SceneManager;
	private JButton btnAddItem;
	private JButton btnRmvUnlockItem;

	public class ButtonHandler implements ActionListener {
		private EditSceneWindow window;

		public ButtonHandler(EditSceneWindow window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(btnSaveScene))
				saveScene();
			
			else if (e.getSource().equals(btnCancel))
			{
				setVisible(false);
				m_Parent.editSceneWindowHasClosed();
			}
			else if (e.getSource().equals(btnConnectScene))
				connectSceneButtonClicked();

			else if (e.getSource().equals(btnRemoveConnection))
				removeConnectionButtonClicked();
			
			else if (e.getSource().equals(btnAddItem))
				addItemButtonClicked();
			
			else if (e.getSource().equals(btnRemoveDropItem))
				m_WindowComm.displayMessage("Remove drop item");
			
			else if (e.getSource().equals(btnRmvUnlockItem))
				m_WindowComm.displayMessage("Remove unlock item");
		}
	}
	
	/**
	 * Create the application.
	 */
	public EditSceneWindow( Scene scene, SceneManagerPanel parent, SceneManager sceneManager)
	{
		m_WindowComm = new WindowComm(this);
		m_SceneManager = sceneManager;

		m_Connections = scene.getConnections();

		m_Parent = parent;
		m_Scene = scene;
		initialize( );
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize( )
	{
		getContentPane().setLayout(null);
		
		m_ConnectionLabels = new JTextField[NUM_JLABELS];
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				closeWindow();
			}
		});
		
		ButtonHandler btnHandler = new ButtonHandler(this);
		
		setBounds( 100, 100, 830, 400 );
		Point parentLocation = m_Parent.getLocation();
		double parentX = parentLocation.getX();
		double parentY = parentLocation.getY();
		
		setLocation((int)(parentX + 125),(int)(parentY + 125));
		
		int x = 563;
		int y = 208;
		int height = 20;
		int width = 238;
		
		
		for (int i = 0; i < 4; i++)
		{
			m_ConnectionLabels[i] = new JTextField();
			m_ConnectionLabels[i].setBounds(x, y, width, height);
			getContentPane().add(m_ConnectionLabels[i]);
			m_ConnectionLabels[i].setEditable(false);
			y+=25;
		}
		
		m_TitleTextField = new JTextField();
		m_TitleTextField.setText(m_Scene.getTitle());
		m_TitleTextField.setBounds(10, 50, 288, 27);
		getContentPane().add(m_TitleTextField);
		m_TitleTextField.setColumns(10);
		
		lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 35, 46, 14);
		getContentPane().add(lblTitle);
		
		m_ConnectedScenesScrollPane = new JScrollPane();
		m_ConnectedScenesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		m_ConnectedScenesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		m_ConnectedScenesScrollPane.setBounds(345, 209, 208, 98);
		getContentPane().add(m_ConnectedScenesScrollPane);
		
		m_ConnectedScenesModel = new DefaultListModel<Scene>();
		m_ConnectedScenesJList = new JList<Scene>();
		m_ConnectedScenesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m_ConnectedScenesJList.setFixedCellHeight(22);
		m_ConnectedScenesJList.setModel(m_ConnectedScenesModel);
		m_ConnectedScenesScrollPane.setViewportView(m_ConnectedScenesJList);
		
		populateConnectedScenes();
		
		m_DescScrollPane = new JScrollPane();
		m_DescScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		m_DescScrollPane.setBounds(10, 209, 325, 168);
		getContentPane().add(m_DescScrollPane);
		
		m_DescTextArea = new JTextArea();
		m_DescTextArea.setText(m_Scene.getDesc());
		m_DescScrollPane.setViewportView(m_DescTextArea);
		
		lblConnectedScenes = new JLabel("Connected Scenes");
		lblConnectedScenes.setBounds(346, 184, 109, 14);
		getContentPane().add(lblConnectedScenes);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 184, 109, 14);
		getContentPane().add(lblDescription);
		
		btnConnectScene = new JButton("Connect A Scene");
		btnConnectScene.addActionListener(btnHandler);
		btnConnectScene.setBounds(345, 318, 208, 23);
		getContentPane().add(btnConnectScene);
		
		btnRemoveConnection = new JButton("Remove Connection");
		btnRemoveConnection.addActionListener(btnHandler);
		btnRemoveConnection.setBounds(345, 354, 208, 23);
		getContentPane().add(btnRemoveConnection);
		
		btnSaveScene = new JButton("Save Scene");
		btnSaveScene.addActionListener(btnHandler);
		btnSaveScene.setBounds(578, 318, 208, 23);
		getContentPane().add(btnSaveScene);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(btnHandler);
		btnCancel.setBounds(578, 354, 208, 23);
		getContentPane().add(btnCancel);
		
		lblLargeTitle = new JLabel("");
		lblLargeTitle.setText(m_Scene.getTitle());
		lblLargeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLargeTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLargeTitle.setBounds(10, 11, 794, 27);
		getContentPane().add(lblLargeTitle);
		
		lblConnectionLabel = new JLabel("Connection Label");
		lblConnectionLabel.setBounds(563, 184, 196, 14);
		getContentPane().add(lblConnectionLabel);
		
		JLabel lblItemConnectedTo = new JLabel("Item dropped on scene\r\n");
		lblItemConnectedTo.setBounds(578, 35, 153, 14);
		getContentPane().add(lblItemConnectedTo);
		
		btnRmvUnlockItem = new JButton("X\r\n");
		btnRmvUnlockItem.setFont(new Font("Courier New", Font.PLAIN, 10));
		btnRmvUnlockItem.addActionListener(btnHandler);
		btnRmvUnlockItem.setBounds(760, 94, 45, 23);
		getContentPane().add(btnRmvUnlockItem);
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(btnHandler);
		btnAddItem.setBounds(578, 128, 109, 23);
		getContentPane().add(btnAddItem);
		
		m_ItemUnlocsTextField = new JTextField();
		m_ItemUnlocsTextField.setBounds(578, 50, 173, 23);
		getContentPane().add(m_ItemUnlocsTextField);
		m_ItemUnlocsTextField.setColumns(10);
		
		m_ItemDroppedTextField = new JTextField();
		m_ItemDroppedTextField.setColumns(10);
		m_ItemDroppedTextField.setBounds(578, 94, 173, 23);
		getContentPane().add(m_ItemDroppedTextField);
		
		btnRemoveDropItem = new JButton("X");
		btnRemoveDropItem.setFont(new Font("Courier New", Font.PLAIN, 10));
		btnRemoveDropItem.addActionListener(btnHandler);
		btnRemoveDropItem.setBounds(760, 50, 45, 23);
		getContentPane().add(btnRemoveDropItem);
		
		lblItemRequiredTo = new JLabel("Item required to enter scene");
		lblItemRequiredTo.setBounds(578, 79, 208, 14);
		getContentPane().add(lblItemRequiredTo);
		
		m_TitleTextField.grabFocus();
		m_TitleTextField.selectAll();
	}
	
	private void populateConnectedScenes()
	{	
		ArrayList<String> connectionLabels = m_Scene.getConnectionLabels();
		int i = 0;
		
		m_ConnectedScenesModel.removeAllElements();
		
		for (Scene o_Scene : m_Connections)
			m_ConnectedScenesModel.addElement(o_Scene);
		
		for (String o_Label : connectionLabels)
		{
			m_ConnectionLabels[i].setText(o_Label);
			m_ConnectionLabels[i].setEditable(true);
			i++;
		}
		
		while (i < NUM_JLABELS)
		{
			m_ConnectionLabels[i].setEditable(false);
			m_ConnectionLabels[i].setText("");
			i++;
		}
		
	}
	

	private void removeConnectionButtonClicked()
	{
		int connectionToRemove = m_ConnectedScenesJList.getSelectedIndex();
		//Scene connectionToRemove = m_ConnectedScenesJList.getSelectedValue();

		if (connectionToRemove != -1)
		{
			m_Scene.removeConnection(connectionToRemove);
			populateConnectedScenes();
		}
	}
	
	private void connectSceneButtonClicked()
	{
		btnConnectScene.setEnabled(false);
		
		AddConnectionWindow acw = new AddConnectionWindow(m_SceneManager.getSceneModel(), this);
		acw.run();
	}
	
	private void saveScene()
	{
		m_Scene.setTitle(m_TitleTextField.getText());
		m_Scene.setDesc(m_DescTextArea.getText());
		for (int i = 0; i < m_Scene.getConnections().size(); i++)
			m_Scene.modifyLabelByIndex(i, m_ConnectionLabels[i].getText());
				
		if (m_Parent.saveEdittedScene( m_Scene ))
			closeWindow();
		else
			m_WindowComm.displayMessage("Name conflict. Please enter a unique title for the scene.");
	}
	
	public void addItemButtonClicked()
	{
		AddItemWindow aiw = new AddItemWindow(m_SceneManager.getItemModel(), this);
		btnAddItem.setEnabled(false);
		aiw.run();
	}
	
	public void addItemWidnowHasClosed()
	{
		btnAddItem.setEnabled(true);
	}

	public void addConnectionWindowHasClosed()
	{
		btnConnectScene.setEnabled(true);
	}
	
	public void connectScene(Scene selectedScene) 
	{
		m_Scene.addConnection(selectedScene, "");
		populateConnectedScenes();
	}
	
	private void closeWindow()
	{
		m_Parent.editSceneWindowHasClosed();
		dispose();
	}


	public int connectItem(Item toConnect, String type) 
	{
		// Need a function that returns an error code. Something like:
		//
		// 0 signifies that the item was added to the scene in the manner that it was requested
		// 1 = trying to add an item that drops on a scene that already drops somewhere else
		// 2 = trying to add an item that drops on a scene unlocked by it
		// TODO continue this method return m_Scene.connectDropItem(toConnect);
		return 0;
	}
}
