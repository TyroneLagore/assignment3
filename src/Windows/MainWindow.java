package Windows;

import java.awt.*;

import javax.swing.JFrame;

import Game_System.GameSystem;
import Panels.ItemManagerPanel;
import Panels.SceneManagerPanel;
import TableModels.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.JPanel;

/**
 * Main Window object for displaying the majority of the game editor
 * functionality.
 * 
 * @author Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class MainWindow extends JFrame{

	/* Private Variables for Main Window */
	
	private GameSystem m_MainSystem;
	private SceneManagerPanel m_SceneMngrPnl;
	private ItemManagerPanel m_ItemMngrPnl;
	private JTabbedPane m_MainTabbedPane;
	private JMenu mnFile;
	private JMenuItem mntmSave;
	private JMenuItem mntmLoad;
	private JMenuItem mntmQuit;
	private JTable itemTable, sceneTable;
	private ItemTableModel m_ItemTableModel;
	private SceneTableModel m_SceneTableModel;

	// ================== Inner Class ==============================
	public class MenuHandler implements ActionListener {
		private MainWindow window;

		public MenuHandler(MainWindow window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(mntmSave))
			{
				/* TODO add save functionality */
			}
			else if (e.getSource().equals(mntmLoad))
			{
				/* TODO add load functionality */
			}
			else if (e.getSource().equals(mntmQuit))
			{
				/* TODO add quit functionality */
			}
			

		}
	}

	// =============================================================
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
	 * Create the application.
	 */
	public MainWindow( GameSystem mainSystem ) {
		m_MainSystem = mainSystem;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		itemTable = new JTable( m_MainSystem.getItemTableModel() );
		sceneTable = new JTable( m_MainSystem.getSceneTableModel() );
		
		MenuHandler btnHandler = new MenuHandler(this);
		
		setBounds(100, 100, 448, 296);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(btnHandler);
		mnFile.add(mntmSave);

		mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(btnHandler);
		mnFile.add(mntmLoad);

		mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(btnHandler);
		mnFile.add(mntmQuit);

		m_MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(m_MainTabbedPane, BorderLayout.CENTER);
		
		m_SceneMngrPnl = new SceneManagerPanel(sceneTable);
		m_MainTabbedPane.addTab("Scenes", null, m_SceneMngrPnl, null);

		m_ItemMngrPnl = new ItemManagerPanel(itemTable);
		m_MainTabbedPane.addTab("Items", null, m_ItemMngrPnl, null);
	}

}
