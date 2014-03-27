package TableModels;

import java.util.ArrayList;

import javax.swing.table.*;

import Game_System.*;

public class ItemTableModel extends AbstractTableModel
{
	private String[] headers = { "Name","Description","SceneConnectedTo" };
	private ArrayList<Item> m_itemList;
	
	
	public ItemTableModel( ArrayList<Item> itemList)
	{
		m_itemList = itemList;
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
