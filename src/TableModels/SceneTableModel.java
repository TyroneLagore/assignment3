package TableModels;

import java.util.ArrayList;

import javax.swing.table.*;

import Game_System.Item;
import Scene_Manager.Scene;

public class SceneTableModel extends AbstractTableModel
{ /* XXX isolated infers it has no connections, prior or post */
	private String[] headers = { "Name","Item Connected","# Connected","No path to" };
	private ArrayList<Scene> m_SceneGraph;
	
	private static final int NAME 				= 0;
	private static final int HAS_ITEM 			= 1;
	private static final int NUM_CONNECTIONS 	= 2;
	private static final int NO_PATH			= 3;
	
	public SceneTableModel ( ArrayList<Scene> sceneGraph)
	{
		m_SceneGraph = sceneGraph;
	}
	
	/*
	public void addScene( Scene newScene )
	{
		m_SceneGraph.add( newScene );
		fireTableDataChanged( );
	}
	*/
	
	public String getColumnName(int column)
	{
		return headers[column];
	}
	
	
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return m_SceneGraph.size();
	}

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
			case HAS_ITEM:
				int itemSymbol = 0x0416;
				returnObj = (Object) indexedScene.getConnectedItem() == null ? "" : Character.toString((char)(itemSymbol));
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
