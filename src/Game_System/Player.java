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
	
	public Player()
	{
		m_Inventory = new ArrayList<Item>();
	}

	public void addItem( Item toAdd )		{   m_Inventory.add(toAdd); 	}
	public ArrayList<Item> getInventory() 	{	return m_Inventory;			}	
	public int inventorySize()				{	return m_Inventory.size();	}	
	public boolean inventoryContains( Item itemCheck )	
											{	return m_Inventory.contains(itemCheck); }
	
	
}
