package Windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import Scene_Manager.Scene;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

/**
 * Scene Info Window
 * 
 * Displays information of a scene that an item is connected to.
 * 
 * @author Chris Gonzalez
 */
public class SceneInfoWindow extends JFrame {

	private Scene m_Scene;
	private JButton btnClose;
	private JLabel lblDescription;
	private JLabel lblTitle;
	private JTextArea m_DescTextArea;
	private JScrollPane m_DescScrollPane;
	private JScrollPane m_ConnectedScenesScrollPane;
	private JList <Scene>m_ConnectedScenesJList;
	private DefaultListModel<Scene> m_ConnectedScenesModel;
	private JTextField []m_ConnectionLabels;
	private ArrayList<Scene> m_Connections;
	private static final int NUM_JLABELS = 4;
	private JLabel m_ConnectedScenesLabel;
	
	/**
	 * Launch the application.
	 */
	public class ButtonHandler implements ActionListener {
		private SceneInfoWindow window;

		public ButtonHandler(SceneInfoWindow window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(btnClose))
			{
				setVisible(false);
				dispose();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public SceneInfoWindow( Scene scene)
	{
		
		m_Connections = scene.getConnections();
		m_Scene = scene;
		initialize();
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
	private void initialize()
	{
		ButtonHandler btnHandler = new ButtonHandler(this);
		getContentPane().setLayout(null);
		
		m_ConnectionLabels = new JTextField[NUM_JLABELS];
		
		setBounds( 100, 100, 830, 500 );
		btnClose = new JButton("Close");
		btnClose.addActionListener(btnHandler);
		btnClose.setBounds(513, 356, 101, 34);
		getContentPane().add(btnClose);
		
	    lblTitle = new JLabel("New label");
	    lblTitle.setText(m_Scene.getTitle());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(11, 12, 794, 27);
		getContentPane().add(lblTitle);
		
		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(21, 49, 205, 19);
		getContentPane().add(lblDescription);
		
		m_DescScrollPane = new JScrollPane();
		m_DescScrollPane.setBounds(10, 79, 216, 311);
		getContentPane().add(m_DescScrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		m_DescScrollPane.setRowHeaderView(scrollBar);
		
		m_DescTextArea = new JTextArea();
		m_DescTextArea.setEditable(false);
		m_DescScrollPane.setViewportView(m_DescTextArea);
		m_DescTextArea.setText(m_Scene.getDesc());
		m_DescScrollPane.setViewportView(m_DescTextArea);
		
		
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
		
		m_ConnectedScenesLabel = new JLabel("Connected Scenes");
		m_ConnectedScenesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		m_ConnectedScenesLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		m_ConnectedScenesLabel.setBounds(345, 171, 208, 27);
		getContentPane().add(m_ConnectedScenesLabel);
		
		populateConnectedScenes();
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
}
