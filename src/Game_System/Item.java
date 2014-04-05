/**
 * 
 */
package Game_System;

import Scene_Manager.Scene;

/**
 * Description
 *
 * @author	James C. Coté
 * @author Tyrone Lagore
 * @version v1.0 - Mar 25, 2014
 */
public class Item 
{
	private String 	m_sName;
	private String 	m_sDesc;
	private Scene 	m_DroppedIn;
	private Scene	m_Unlocks;
	
	public Item (String name, String desc)
	{
		m_sName 	= name;
		m_sDesc 	= desc;
		m_DroppedIn = null;
		m_Unlocks	= null;
	}
	
	// Getters and Setters
	public final String getName ()						{ return m_sName; }
	public void setName( String sNewName ) 				{ m_sName = sNewName; }
	public final String getDesc() 						{ return m_sDesc; }
	public void setDesc( String sNewDesc ) 				{ m_sDesc = sNewDesc; }
	
	// Scene Getters and Setters
	public void setSceneDrop( Scene sceneToDropIn )		{ m_DroppedIn = sceneToDropIn; }
	public final Scene getDropScene( )					{ return m_DroppedIn; }
	public boolean dropsInAScene( )						{ return null != m_DroppedIn; }
	
	public void setSceneUnlock( Scene sceneToUnlock )	{ m_Unlocks = sceneToUnlock; }
	public final Scene getUnlockScene( )				{ return m_Unlocks; }
	public boolean unlocksAScene( )						{ return null != m_Unlocks; }
	
	public String toString ( )							{ return m_sName; }
	
}

