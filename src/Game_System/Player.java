/**
 * 
 */
package Game_System;

import java.util.*;

/**
 * Description
 *
 * @author James C. Cote
 * @author	Tyrone Lagore
 * @version v1.0 - Mar 25, 2014
 */
public class Player 
{
	private ArrayList<Item> m_Inventory;
	private ArrayList<String> m_Notes;
	
	public Player()
	{
		m_Inventory = new ArrayList<Item>();
	}

	public void addItem( Item toAdd )		{   m_Inventory.add(toAdd); 	}
	public ArrayList<Item> getInventory() 	{	return m_Inventory;			}	
	public int inventorySize()				{	return m_Inventory.size();	}	
	public boolean inventoryContains( Item itemCheck )	
											{	return m_Inventory.contains(itemCheck); }
	public void clearPlayerInventory()		{	m_Inventory.clear();		}
	public void addNote(String note)		{   m_Notes.add(note);			}
	public ArrayList<String> getNotes()		{ 	return m_Notes;				}
}
