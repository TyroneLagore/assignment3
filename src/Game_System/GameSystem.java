/**
 * 
 */
package Game_System;

import Scene_Manager.*;
import TableModels.ItemTableModel;
import Windows.*;
import TableModels.*;

/**
 * Description
 *
 * @author	Tyrone Lagore
 * @version v1.2 - April 4, 2014
 */
public class GameSystem 
{
	private MainWindow m_MainWindow;
	private SceneManager m_SceneManager;
	
	public GameSystem()
	{
		m_SceneManager = new SceneManager();
		m_MainWindow = new MainWindow(this);
	}
	
	public void run() 							{ m_MainWindow.run(); }
	public SceneTableModel getSceneTableModel() { return m_SceneManager.getSceneModel(); }
	public ItemTableModel getItemTableModel() 	{ return m_SceneManager.getItemModel(); }
	public SceneManager getSceneManager()		{ return m_SceneManager; }
	
	public boolean loadSceneManager( String fileName ) 
												{ return m_SceneManager.loadFromFile( fileName ); }
	public boolean saveSceneManager( String fileName )
												{ return m_SceneManager.saveToFile( fileName );   }
	

}
