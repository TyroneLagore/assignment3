package Windows;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Scene_Manager.Scene;

/**
 * EditSceneWindow
 * Allows for the editting of an individual scene, passed in upon creation.
 * @author Tyrone Lagore
 */

public class EditSceneWindow extends JFrame {

	private JFrame	frame;
	private Scene m_Scene;

	/**
	 * Create the application.
	 */
	public EditSceneWindow( Scene scene )
	{
		m_Scene = scene;
		initialize( );
	}
	
	
	public void run()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					setVisible(true);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize( )
	{
		setBounds( 100, 100, 800, 640 );
	}

}
