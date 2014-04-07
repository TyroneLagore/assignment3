package Windows;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Game_System.GameSystem;
import Panels.ItemManagerPanel;
import Panels.SceneManagerPanel;
import Scene_Manager.SceneManager;
import TableModels.*;
import UserIO.WindowComm;

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
	private WindowComm m_WindowComm;
	private JTabbedPane m_MainTabbedPane;
	private JMenu mnFile;
	private JMenuItem mntmSave;
	private JMenuItem mntmLoad;
	private JMenuItem mntmQuit;
	private JMenuItem mntmTest;

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
				saveProject();

			else if (e.getSource().equals(mntmLoad))
				loadProject();
			
			else if (e.getSource().equals(mntmQuit))
				verifyQuit();
			
			else if (e.getSource().equals(mntmTest))
				runTestGame();
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
	 * Name: verifyQuit
	 * 
	 * Purpose: Verifies that the user wants to exit, and asks if they
	 *  would like to save.
	 */
	public void verifyQuit()
	{
		if (m_WindowComm.getYesNo("Would you like to save before quitting?", "Quit") == 0)
				saveProject();
		else
				closeWindow();
	}
	
	/**
	 * Name: loadProject
	 * Purpose: Load has been selected, opens the window explorer for the user
	 * 	to select a file to load.
	 * 
	 */
	public void loadProject()
	{
		String fileName = m_WindowComm.getFileFromExplorer(FileDialog.LOAD);
		SceneManager m_SceneManager = m_MainSystem.loadSceneManager(fileName);
		m_ItemMngrPnl.loadSceneManager();
		m_SceneMngrPnl.loadSceneManager();
	}
	
	/**
	 * Name: saveProject
	 * Purpose: Save has been selected, opens the window explorer for the user
	 * 	to select a file (or enter a filename) that they would like to save as.
	 * 
	 */
	public void saveProject()
	{
		String fileName = m_WindowComm.getFileFromExplorer(FileDialog.SAVE);
		if (!fileName.matches("null") && !fileName.matches("nullnull"))
		{
			if ( m_MainSystem.saveSceneManager(fileName))
				m_WindowComm.displayMessage("Successfully saved to \"" + fileName + "\".");
			else 
				m_WindowComm.displayMessage("Error saving file.");
		}
	}
	
	public void testWindowHasClosed()
	{
		setVisible(true);
	}

	public void runTestGame()
	{
		if (m_MainSystem.getSceneManager().getEndScene().getSceneIsConnected())
		{
			TestGameWindow rgw = new TestGameWindow (this, m_MainSystem.getSceneManager());
			setVisible(false);
			rgw.run();
		}else
			m_WindowComm.displayMessage("The \"Beginning\" scene must connect to the \"End\" scene.");
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				verifyQuit();
			}
		});
		
		m_WindowComm = new WindowComm(this);
		
		MenuHandler btnHandler = new MenuHandler(this);
		
		setBounds(100, 100, 799, 569);

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
		
		mntmTest = new JMenuItem("Test");
		mntmTest.addActionListener(btnHandler);
		mnFile.add(mntmTest);

		mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(btnHandler);
		mnFile.add(mntmQuit);
		

		m_MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(m_MainTabbedPane, BorderLayout.CENTER);
		
		m_SceneMngrPnl = new SceneManagerPanel(m_MainSystem.getSceneTableModel(), this,
													m_MainSystem.getSceneManager());
		
		m_MainTabbedPane.addTab("Scenes", null, m_SceneMngrPnl, null);

		m_ItemMngrPnl = new ItemManagerPanel(m_MainSystem.getItemTableModel(), this,
												m_MainSystem.getSceneManager());
		m_MainTabbedPane.addTab("Items", null, m_ItemMngrPnl, null);
	}
	
	/**
	 * Window closing override
	 */
	private void closeWindow()
	{
		setVisible(false);
		dispose();
	}
}
