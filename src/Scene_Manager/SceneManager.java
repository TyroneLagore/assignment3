/**
 * 
 */
package Scene_Manager;

import java.util.*;

import Game_System.*;
import TableModels.ItemTableModel;
import TableModels.SceneTableModel;

/**
 * Description
 *
 * @author	Tyrone Lagore
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManager 
{
	private ItemTableModel m_ItemTableModel;
	private SceneTableModel m_SceneTableModel;
	private ArrayList<Scene> m_SceneGraph;
	private ArrayList<Item> m_ItemList;
	private Scene m_StartScene;
	private Scene m_CurrentScene;
	private Scene m_EndScene;
	private Player player;
	
	public SceneManager()
	{
		m_SceneGraph = new ArrayList<Scene>();
		m_ItemList = new ArrayList<Item>();
		m_ItemTableModel = new ItemTableModel ( m_ItemList );
		m_SceneTableModel = new SceneTableModel ( m_SceneGraph );
		player = new Player();
	}

	
	public Scene addScene()
	{
		return new Scene("<Enter a Unique Title>", "<Description>");
	}
	
	
	public void removeScene (Scene toRemove)
	{
		m_SceneGraph.remove(toRemove);
		m_SceneTableModel.fireTableDataChanged();
	}
	
	/**
	 * Attempts to save a scene into the graph.  If it is not saved due to a name conflict,
	 * the caller is informed via a boolean.
	 * 
	 * @param toSave The scene being requested to add to the list
	 * @return sceneAdded true if the scene was added or false if there was a name conflict
	 */
	public boolean saveScene(Scene toSave)
	{
		boolean sceneAdded = false;
		
		if (m_SceneGraph.contains(toSave))
			sceneAdded = true;
		else if (!isSceneNameConflict(toSave))
		{
			m_SceneGraph.add(toSave);
			sceneAdded = true;
		}
		
		m_SceneTableModel.fireTableDataChanged();
		return sceneAdded;
	}
	
	/**
	 * 
	 * @param desc
	 * @param name
	 */
	public void saveItem (String desc, String name )
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public ItemTableModel getItemModel ()
	{
		return m_ItemTableModel;
	}
	
	public SceneTableModel getSceneModel ()
	{
		return m_SceneTableModel;
	}
	
	/**
	 * Validates that a chosen name for a scene does not already within the scene graph.
	 * if it is, the caller is informed. 
	 * @param name
	 * @return
	 */
	private boolean isSceneNameConflict(Scene scene)
	{
		boolean conflict = false;
		for (Scene o_Scene : m_SceneGraph)
		{
			if (o_Scene.getTitle().toLowerCase().equals(scene.getTitle().toLowerCase()))
				conflict = true;
		}
		
		return conflict;
	}
	
	private void updateSceneConnections()
	{
		for (Scene o_CurrentScene : m_SceneGraph)
			o_CurrentScene.setSceneIsConnected(false);
		
		for (Scene o_CurrentScene : m_SceneGraph)
			for (Scene o_ConnectedScene : o_CurrentScene.getConnections())
				o_ConnectedScene.setSceneIsConnected(true);
	}

}
