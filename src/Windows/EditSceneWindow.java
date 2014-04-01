package Windows;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Panels.SceneManagerPanel;
import Scene_Manager.Scene;
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
	private JList m_ConnectedScenesJList;
	private DefaultListModel<String> m_ConnectedScenesModel;
	private JLabel lblTitle;
	private JTextField m_TitleTextField;
	private JTextField []m_ConnectionLabels;
	private ArrayList<Scene> m_Connections;
	private static final int NUM_JLABELS = 4;
	private JLabel lblConnectionLabel;

	
	
	
	public class ButtonHandler implements ActionListener {
		private EditSceneWindow window;

		public ButtonHandler(EditSceneWindow window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(btnSaveScene))
			{
				lblLargeTitle.setText(m_TitleTextField.getText());
				saveScene();
				m_WindowComm.displayMessage("You clicked save");
			}
				/* TODO add save functionality */
			else if (e.getSource().equals(btnCancel))
			{
				setVisible(false);
				m_Parent.editSceneWindowHasClosed();
			}
			else if (e.getSource().equals(btnConnectScene))
			{
				m_WindowComm.displayMessage("You clicked connect scene");
			}
			else if (e.getSource().equals(btnRemoveConnection))
				m_WindowComm.displayMessage("You clicked remove connection");
		}
	}
	
	/**
	 * Create the application.
	 */
	public EditSceneWindow( Scene scene, SceneManagerPanel parent)
	{
		m_WindowComm = new WindowComm(this);

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
		
		setBounds( 100, 100, 830, 440 );
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
		m_TitleTextField.setBounds(10, 60, 288, 27);
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
		
		m_ConnectedScenesModel = new DefaultListModel<String>();
		m_ConnectedScenesJList = new JList();
		m_ConnectedScenesJList.setFixedCellHeight(22);
		m_ConnectedScenesJList.setModel(m_ConnectedScenesModel);
		m_ConnectedScenesScrollPane.setViewportView(m_ConnectedScenesJList);
		
		populatedConnectedScenes();
		
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
		btnConnectScene.setBounds(385, 318, 168, 23);
		getContentPane().add(btnConnectScene);
		
		btnRemoveConnection = new JButton("Remove Connection");
		btnRemoveConnection.addActionListener(btnHandler);
		btnRemoveConnection.setBounds(385, 354, 168, 23);
		getContentPane().add(btnRemoveConnection);
		
		btnSaveScene = new JButton("Save Scene");
		btnSaveScene.addActionListener(btnHandler);
		btnSaveScene.setBounds(633, 318, 168, 23);
		getContentPane().add(btnSaveScene);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(btnHandler);
		btnCancel.setBounds(633, 354, 168, 23);
		getContentPane().add(btnCancel);
		
		lblLargeTitle = new JLabel("");
		lblLargeTitle.setText(m_Scene.getTitle());
		lblLargeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLargeTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLargeTitle.setBounds(10, 11, 794, 27);
		getContentPane().add(lblLargeTitle);
		
		lblConnectionLabel = new JLabel("Connection Label");
		lblConnectionLabel.setBounds(582, 184, 196, 14);
		getContentPane().add(lblConnectionLabel);
	}
	
	private void populatedConnectedScenes()
	{	
		ArrayList<String> connectionLabels = m_Scene.getConnectionLabels();
		int i = 0;
		
		m_ConnectedScenesModel.removeAllElements();
		
		for (Scene o_Scene : m_Connections)
			m_ConnectedScenesModel.addElement(o_Scene.getTitle());
		
		for (String o_Label : connectionLabels)
		{
			m_ConnectionLabels[i].setText(o_Label);
			m_ConnectionLabels[i].setEditable(true);
			i++;
		}
		
		while (i < NUM_JLABELS)
		{
			m_ConnectionLabels[i].setEditable(false);
			i++;
		}
		
	}
	
	private void saveScene()
	{
		m_Parent.saveEdittedScene( m_Scene );
		setVisible(false);
	}
	
	private void closeWindow()
	{
		m_Parent.editSceneWindowHasClosed();
	}
}
