package TableModels;

import java.util.ArrayList;

import javax.swing.table.*;

import Game_System.Item;
import Scene_Manager.Scene;

public class SceneTableModel extends AbstractTableModel
{ /* XXX isolated infers it has no connections, prior or post */
	private String[] headers = { "Name","Item Connected","Number Connected","No path to" };
	private ArrayList<Scene> m_SceneGraph;
	
	public SceneTableModel ( ArrayList<Scene> sceneGraph)
	{
		m_SceneGraph = sceneGraph;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
