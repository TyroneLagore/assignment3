/**
 * 
 */
package Scene_Manager;

import java.util.*;

import Game_System.*;

/**
 * Description
 *
 * @author	James C. Coté
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManager 
{
	private ArrayList<Scene> m_SceneGraph;
	private ArrayList<Item> m_ItemList;
	private Player player;
	
	public SceneManager()
	{
		m_SceneGraph = new ArrayList<Scene>();
		m_ItemList = new ArrayList<Item>();
		player = new Player();
	}
	
	public Scene addScene()
	{
		return new Scene();
	}
	
	
	public void removeScene (String sTitle)
	{
		
	}
	
	public void connectScene( Scene toConnect )
	{
		/* XXX Edit scene window handles error checking with connection */
		/* XXX connectScene simply adds it */
	}
	
	public void saveItem (String desc, String name )
	{
	}
	
	public ArrayList<Item> getItemList ()
	{
		return m_ItemList;
	}
	
	public ArrayList<Scene> getSceneGraph ()
	{
		return m_SceneGraph;
	}

}
