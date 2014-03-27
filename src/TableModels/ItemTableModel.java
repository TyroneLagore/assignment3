package TableModels;

import java.util.ArrayList;
import javax.swing.table.*;
import Game_System.*;


public class ItemTableModel extends AbstractTableModel
{
	private String[] headers = { "Name","Description","SceneConnectedTo" };
	private ArrayList<Item> m_itemList;
	
	// Constant Variables
	private static final int NAME 		= 0;
	private static final int DESC 		= 1;
	private static final int CONNECTED 	= 2;
	
	
	public ItemTableModel( ArrayList<Item> itemList)
	{
		m_itemList = itemList;
		testPopulate( );
	}
	
	public void addItem( Item newItem )
	{
		m_itemList.add( newItem );
		fireTableDataChanged( );
	}
	
	public void testPopulate ()
	{
		for (int i = 0; i < 10; i ++)
			addItem(new Item ("Test" + i, "Test" ));
	}
	
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		m_itemList.size( );
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		Item aIndexedItem = null;
		Object oReturnObj = null;
		
		if( rowIndex >= 0 && rowIndex < getRowCount( ) )
		{
			aIndexedItem = m_itemList.get( rowIndex );
			
			switch( columnIndex )
			{
			case NAME:
				oReturnObj = aIndexedItem.getName( );
				break;
			case DESC:
				oReturnObj = aIndexedItem.getDesc( );
				break;
			case CONNECTED:
				oReturnObj = new String( "Bitches!(" + rowIndex + ")" );
				break;
			default:
				break;
			};
		}

		return oReturnObj;
	}

}
