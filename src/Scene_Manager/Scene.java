/**
 * 
 */
package Scene_Manager;

import java.util.ArrayList;

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
	private ArrayList<Scene> m_Connections;
	private ArrayList<String> m_ConnectionLabels;
	private static final int MAX_CONNECTIONS = 4;
	
	public Scene(String title, String desc)
	{
		m_Connections = new ArrayList<Scene>();
		m_ConnectionLabels = new ArrayList<String>();
		m_Title = title;
		m_Desc = desc;
	}
	
	public String getTitle()
	{
		return m_Title;
	}
	
	public String getDesc()
	{
		return m_Desc;
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
	
}
