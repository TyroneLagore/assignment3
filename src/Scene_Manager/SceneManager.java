/**
 * 
 */
package Scene_Manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import Game_System.*;
import TableModels.ItemTableModel;
import TableModels.SceneTableModel;

/**
 * Description
 *
 * @author	Tyrone Lagore
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManager 
{
	private ItemTableModel m_ItemTableModel;
	private SceneTableModel m_SceneTableModel;
	private ArrayList<Scene> m_SceneGraph;
	private ArrayList<Item> m_ItemList;
	private Scene m_StartScene;
	private Scene m_CurrentScene;
	private Scene m_EndScene;
	private Player player;
	
	public SceneManager()
	{
		m_StartScene = new Scene ("Beginning", "<Description>");
		m_EndScene = new Scene ("End", "<Description>");
		
		m_SceneGraph = new ArrayList<Scene>();
		m_SceneGraph.add(m_StartScene);
		m_SceneGraph.add(m_EndScene);
		
		m_ItemList = new ArrayList<Item>();
		m_ItemTableModel = new ItemTableModel ( m_ItemList );
		m_SceneTableModel = new SceneTableModel ( m_SceneGraph );
		player = new Player();
		
		updateSceneConnections();
	}

	public boolean contains (Scene checkScene)		{	return m_SceneGraph.contains(checkScene); }
	
	public Scene addScene() { return new Scene("<Enter a Unique Title>", "<Description>"); }
	
	public void removeScene (Scene toRemove)
	{
		if (toRemove != m_StartScene && toRemove != m_EndScene)
		{
			m_SceneGraph.remove(toRemove);
			updateSceneConnections();
			m_SceneTableModel.fireTableDataChanged();
		}
	}
	
	/**
	 * Attempts to save a scene into the graph.  If it is not saved due to a name conflict,
	 * the caller is informed via a boolean.
	 * 
	 * @param toSave The scene being requested to add to the list
	 * @return sceneAdded true if the scene was added or false if there was a name conflict
	 */
	public boolean saveScene(Scene toSave, String newTitle)
	{
		boolean sceneAdded = false;
		
		if (!isSceneNameConflict(toSave, newTitle))
		{
			if (!m_SceneGraph.contains(toSave))
				m_SceneGraph.add(toSave);
			sceneAdded = true;
			updateSceneConnections();
		}
		
		m_SceneTableModel.fireTableDataChanged();
		return sceneAdded;
	}
	
	/**
	 * 
	 * @return
	 */
	public ItemTableModel getItemModel () 		{	return m_ItemTableModel;  }
	public SceneTableModel getSceneModel ()		{	return m_SceneTableModel; }
	public Scene getStartScene()				{	return m_StartScene;	  }
	public Scene getEndScene()					{	return m_EndScene;		  }
	
	/**
	 * Validates that a chosen name for a scene does not already within the scene graph.
	 * if it is, the caller is informed. 
	 * @param name
	 * @return
	 */
	private boolean isSceneNameConflict(Scene scene, String newTitle)
	{
		boolean conflict = false;
		for (Scene o_Scene : m_SceneGraph)
		{
			if (o_Scene.getTitle().toLowerCase().equals(newTitle.toLowerCase())
			     && o_Scene != scene)
				conflict = true;
		}
		
		return conflict;
	}
	
	private void updateSceneConnections()
	{
		for (Scene o_CurrentScene : m_SceneGraph)
			o_CurrentScene.setSceneIsConnected(false);
		
		findConnections(m_StartScene);
	}
	
	public void findConnections(Scene root)
	{
		ArrayList<Scene> connections = root.getConnections();
		root.setSceneIsConnected(true);
		if (connections.size() == 0)
			return;
		else
			for (Scene o_Connection : connections)
				if (!o_Connection.getSceneIsConnected())
					findConnections(o_Connection);
	}
	
	public boolean loadFromFile (String fileName)
	{
		boolean opened = true;
		String sCurrentLine = "";
		String sSceneGraphInput = "";
		String sItemListInput = "";
        try
        {
            XStream xstream = new XStream(new StaxDriver() );
            Scanner input = new Scanner( new File( fileName ) );
            
            while( sCurrentLine.compareToIgnoreCase("Done Scene Graph") != 0)
            {
            	sCurrentLine = input.nextLine();
            	if (sCurrentLine.compareToIgnoreCase("Done Scene Graph") != 0)
            		sSceneGraphInput += sCurrentLine + "\n";
            }

            while (input.hasNext())
            	sItemListInput += input.nextLine ( ) + "\n";
            
            input.close( );
            
            m_SceneGraph = ( ArrayList< Scene > ) xstream.fromXML( sSceneGraphInput );
            m_ItemList = ( ArrayList<Item> ) xstream.fromXML (sItemListInput);
            
            m_StartScene = m_SceneGraph.get(0);
            m_EndScene = m_SceneGraph.get(1);
            
            updateSceneConnections();
            
            m_SceneTableModel.setNewSceneGraph(m_SceneGraph);
            m_ItemTableModel.setNewItemList(m_ItemList); 
        }
        catch(Exception ex) { opened = false; } 
        
        return opened;
	}
	
	public boolean saveToFile (String fileName)
	{
		XStream xstream = new XStream( new StaxDriver() );
		PrintStream outFile;
		boolean saved = true;

        try
        {
            outFile = new PrintStream( new FileOutputStream( fileName ) );  
            outFile.print( xstream.toXML( m_SceneGraph ) );
            
            outFile.print ("\nDone Scene Graph\n");
            
            outFile.print (xstream.toXML (m_ItemList ));
            
            outFile.close();
        }
        catch(Exception ex) { saved = false; }
        
        return saved;
	}
}
