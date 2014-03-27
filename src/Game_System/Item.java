/**
 * 
 */
package Game_System;

/**
 * Description
 *
 * @author	James C. Coté
 * @author Tyrone Lagore
 * @version v1.0 - Mar 25, 2014
 */
public class Item 
{
	private String sName;
	private String sDesc;
	
	public Item (String name, String desc)
	{
		sName = name;
		sDesc = desc;
	}
	
	public String getName ()
	{
		return sName;
	}
	
	public String getDesc()
	{
		return sDesc;
	}
}

