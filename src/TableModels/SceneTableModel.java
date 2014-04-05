package TableModels;

import java.util.ArrayList;

import javax.swing.table.*;

import Game_System.Item;
import Scene_Manager.Scene;

public class SceneTableModel extends AbstractTableModel
{ /* XXX isolated infers it has no connections, prior or post */
	private String[] headers = { "Name","Item Drops", "Item Unlocks","# Connected","Reachable" };
	private ArrayList<Scene> m_SceneGraph;
	
	private static final int NAME 				= 0;
	private static final int DROP 				= 1;
	private static final int UNLOCK				= 2;
	private static final int NUM_CONNECTIONS 	= 3;
	private static final int NO_PATH			= 4;
	
	public SceneTableModel ( ArrayList<Scene> sceneGraph)
	{
		m_SceneGraph = sceneGraph;
		fireTableDataChanged();
	}
	
	public void setNewSceneGraph (ArrayList<Scene> sceneGraph )
												{ m_SceneGraph = sceneGraph; }
	public String getColumnName(int column)		{ return headers[column]; }
	@Override
	public int getColumnCount() 				{ return headers.length;  }
	@Override
	public int getRowCount() 					{ return m_SceneGraph.size(); }
	@Override
	public Class getColumnClass(int arg0) 		{ return getValueAt(0, arg0).getClass(); }

	@Override
	public Object getValueAt(int row, int col) 
	{
		Scene indexedScene = null;
		Object returnObj = null;
		
		if (row >= 0 && row <= getRowCount())
		{
			indexedScene = m_SceneGraph.get(row);
			
			switch (col)
			{
			case NAME:
				returnObj = (Object) indexedScene.getTitle();
				break;
			case DROP:
				returnObj = (Object) indexedScene.getDropItem() == null ? "None" : 
					indexedScene.getDropItem().getName();
				break;
			case UNLOCK:
				returnObj = (Object) indexedScene.getUnlockItem() == null ? "None" :
							indexedScene.getUnlockItem().getName();
				break;
			case NUM_CONNECTIONS:
				returnObj = (Object) indexedScene.getConnections().size();
				break;
			case NO_PATH:
				returnObj = (Object) indexedScene.getSceneIsConnected();
				break;
			}
		}
		
		return returnObj;
	}
	
	/**
	 * Gets a scene from the specified index
	 * @param index The row in which the item is kept.
	 * @return Scene The indexed item.
	 */
	public Scene getSceneAt(int index)
	{
		return m_SceneGraph.get(index);
	}

}
