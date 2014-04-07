/**
 * 
 */
package Game_System;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import Scene_Manager.*;
import TableModels.ItemTableModel;
import Windows.*;
import TableModels.*;

/**
 * Description
 *
 * @author	Tyrone Lagore
 * @version v1.2 - April 4, 2014
 */
public class GameSystem 
{
	private MainWindow m_MainWindow;
	private SceneManager m_SceneManager;
	
	public GameSystem()
	{
		m_SceneManager = new SceneManager();
		m_MainWindow = new MainWindow(this);
	}
	
	public void run() 							{ m_MainWindow.run(); }
	public SceneTableModel getSceneTableModel() { return m_SceneManager.getSceneModel(); }
	public ItemTableModel getItemTableModel() 	{ return m_SceneManager.getItemModel(); }
	public SceneManager getSceneManager()		{ return m_SceneManager; }
	
	public SceneManager loadSceneManager( String fileName ) 
	{
		String sInput = "";
        try
        {
            XStream xstream = new XStream(new StaxDriver() );
            Scanner input = new Scanner( new File( fileName ) );
            
            while( input.hasNext())
            	sInput += input.nextLine();
            
            m_SceneManager = ( SceneManager ) xstream.fromXML( sInput );

            input.close( );
        }
        catch(Exception ex) { } 
        
        return m_SceneManager;
	}
	
	public boolean saveSceneManager( String fileName )
	{
		XStream xstream = new XStream( new StaxDriver() );
		PrintStream outFile;
		boolean saved = true;

        try
        {
            outFile = new PrintStream( new FileOutputStream( fileName ) );  
            outFile.print( xstream.toXML( m_SceneManager ) );           
            outFile.close();
        }
        catch(Exception ex) { saved = false; }
        
        return saved;
	}
	

}
