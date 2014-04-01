package TableModels;

import java.util.ArrayList;
import javax.swing.table.*;
import Game_System.*;


public class ItemTableModel extends AbstractTableModel
{
	private String[] headers = { "Name","Description","SceneConnectedTo" };
	private ArrayList<Item> m_ItemList;
	
	// Constant Variables
	private static final int NAME 		= 0;
	private static final int DESC 		= 1;
	private static final int CONNECTED 	= 2;
	
	
	public ItemTableModel( ArrayList<Item> itemList)
	{
		m_ItemList = itemList;
	}
	
	public void addItem( Item newItem )
	{
		m_ItemList.add( newItem );
		fireTableDataChanged( );
	}
	
	public Item testGetItem()
	{
		return m_ItemList.get(0);
	}
	
	public void testPopulate ()
	{
		for (int i = 0; i < 10; i ++)
			addItem(new Item ("Test" + i, "Test" ));
	}

	@Override
	public String getColumnName( int iColumn )
	{
		return headers[iColumn];
	}
	
	@Override
	public boolean isCellEditable( int rowIndex, int columnIndex )
	{
		return false;
	}
	
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		return m_ItemList.size( );
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		Item aIndexedItem = null;
		Object oReturnObj = null;
		
		if( rowIndex >= 0 && rowIndex < getRowCount( ) )
		{
			aIndexedItem = m_ItemList.get( rowIndex );
			
			switch( columnIndex )
			{
			case NAME:
				oReturnObj = (Object) aIndexedItem.getName( );
				break;
			case DESC:
				oReturnObj = (Object) aIndexedItem.getDesc( );
				break;
			case CONNECTED:
				oReturnObj = (Object) new String( "Bitches!(" + rowIndex + ")" );
				break;
			default:
				break;
			};
		}
		 
		return oReturnObj;
	}

}
