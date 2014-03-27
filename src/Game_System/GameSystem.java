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
	private ItemTableModel m_ItemTableModel;
	private SceneTableModel m_SceneTableModel;
	
	public GameSystem()
	{
		m_MainWindow = new MainWindow(this);
		m_SceneManager = new SceneManager();
		m_ItemTableModel = new ItemTableModel ( grabItemList() );
		m_SceneTableModel = new SceneTableModel ( grabSceneGraph() );
	}
	
	public void run()
	{
		m_MainWindow.run();
	}
	
	public SceneTableModel getSceneTableModel()
	{
		return m_SceneTableModel;
	}
	
	public ItemTableModel getItemTableModel()
	{
		return m_ItemTableModel;
	}
	
	public boolean loadSceneManager( String fileName )
	{
		
		return false;
	}
	
	public void saveSceneManager( String fileName )
	{
		
	}
	
	private final ArrayList<Item> grabItemList()
	{
		return m_SceneManager.getItemList();
	}
	
	private final ArrayList<Scene> grabSceneGraph()
	{
		return m_SceneManager.getSceneGraph();
	}

}
