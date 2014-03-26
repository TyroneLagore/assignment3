package Windows;
import java.awt.EventQueue;
import javax.swing.JFrame;
import Game_System.GameSystem;
import Panels.ItemManagerPanel;
import Panels.SceneManagerPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Main Window object for displaying the majority of the game editor functionality.
 *
 * @author	Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class MainWindow {

	/* Private Variables for Main Window */
	private JFrame				frame;
	private GameSystem 			m_MainSystem;
	private SceneManagerPanel 	m_SceneMngrPnl;
	private ItemManagerPanel 	m_ItemMngrPnl;
	private JTabbedPane 		m_MainTabbedPane;
	
	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		EventQueue.invokeLater( new Runnable( ) {
			public void run( )
			{
				try {
					MainWindow window = new MainWindow( );
					window.frame.setVisible( true );
				}
				catch( Exception e ) {
					e.printStackTrace( );
				}
			}
		} );
	}

	/**
	 * Create the application.
	 */
	public MainWindow(/* GameSystem mainSystem */)
	{
		//m_MainSystem = mainSystem;
		initialize( );
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize( )
	{
		frame = new JFrame( );
		frame.setBounds( 100, 100, 450, 300 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		m_MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(m_MainTabbedPane, BorderLayout.CENTER);
		
		m_SceneMngrPnl = new SceneManagerPanel();
		m_MainTabbedPane.addTab("Scenes", null, m_SceneMngrPnl, null);
		
		m_ItemMngrPnl = new ItemManagerPanel();
		m_MainTabbedPane.addTab("Items", null, m_ItemMngrPnl, null);
	}

}
