/**
 * 
 */
package Game_System;

import java.util.*;

import Scene_Manager.*;
import TableModels.ItemTableModel;
import Windows.*;
import TableModels.*;

/**
 * Description
 *
 * @author	James C. Coté
 * @version v1.0 - Mar 25, 2014
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
	
	public void run()
	{
		m_MainWindow.run();
	}
	
	public SceneTableModel getSceneTableModel()
	{
		return m_SceneManager.getSceneModel();
	}
	
	public ItemTableModel getItemTableModel()
	{
		return m_SceneManager.getItemModel();
	}
	
	public boolean loadSceneManager( String fileName )
	{
		
		return false;
	}
	
	public void saveSceneManager( String fileName )
	{
		
	}
	
	public SceneManager getSceneManager()
	{
		return m_SceneManager;
	}
}
