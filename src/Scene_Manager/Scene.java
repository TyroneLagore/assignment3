/**
 * 
 */
package Scene_Manager;

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
	
	public Scene(String title, String desc)
	{
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

	public void setTitle(String m_Title) 
	{
		this.m_Title = m_Title;
	}

	public void setDesc(String m_Desc) 
	{
		this.m_Desc = m_Desc;
	}
	
}
