/**
 * 
 */
package Scene_Manager;

import java.util.ArrayList;

import Game_System.Item;

/**
 * Description
 *
 * @author	Tyrone Lagore
 * @version v1.1 - Mar 28, 2014
 */
public class Scene 
{
	private String m_Title;
	private String m_Desc;
	private Item m_ConnectedItem;
	private ArrayList<Scene> m_Connections;
	private ArrayList<String> m_ConnectionLabels;
	private boolean m_NoConnectionsTo;
	private static final int MAX_CONNECTIONS = 4;
	
	public Scene(String title, String desc)
	{
		m_ConnectedItem = null;
		m_Connections = new ArrayList<Scene>();
		m_ConnectionLabels = new ArrayList<String>();
		m_Title = title;
		m_Desc = desc;
		m_NoConnectionsTo = true;
	}
	
	public void connectItem(Item toConnect)
	{
		m_ConnectedItem = toConnect;
	}
	
	public Item getConnectedItem()
	{
		return m_ConnectedItem;
	}
	
	public String getTitle()
	{
		return m_Title;
	}
	
	public String getDesc()
	{
		return m_Desc;
	}
	
	public void setSceneIsConnected(boolean bIsConnected)
	{
		m_NoConnectionsTo = bIsConnected;
	}
	
	public boolean getSceneIsConnected()
	{
		return m_NoConnectionsTo;
	}
	
	
	public void addConnection( Scene toConnect, String connectionLabel )
	{
		if (m_Connections.size() < MAX_CONNECTIONS)
		{
			m_Connections.add(toConnect);
			m_ConnectionLabels.add(connectionLabel);
		}
	}
	
	public void removeConnection ( Scene toRemove )
	{
		int index;
		if (m_Connections.contains(toRemove))
		{
			index = m_Connections.indexOf(toRemove);
			m_Connections.remove(index);
			m_ConnectionLabels.remove(index);
		}
	}
	
	public void modifyLabelByIndex(int indexOfScene, String newLabel)
	{
		m_ConnectionLabels.remove(indexOfScene);
		m_ConnectionLabels.add(indexOfScene, newLabel);
	}
	
	public ArrayList<String> getConnectionLabels()
	{
		return m_ConnectionLabels;
	}
	
	public ArrayList<Scene> getConnections()
	{			
		return m_Connections;
	}

	public void setTitle(String m_Title) 
	{
		this.m_Title = m_Title;
	}

	public void setDesc(String m_Desc) 
	{
		this.m_Desc = m_Desc;
	}
	
	public String toString()
	{
		return getTitle();
	}
	
}
