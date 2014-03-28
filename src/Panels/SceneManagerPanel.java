package Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;

import Scene_Manager.Scene;
import Windows.EditSceneWindow;
import net.miginfocom.swing.MigLayout;

/**
 * Main Panel for Managing all the scenes in the game, allows the user the ability to 
 * add, remove and edit any scene from the list of scenes in the scene manager.
 *
 * @author	Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManagerPanel extends JPanel {
	private JButton m_RmvScnBtn;
	private JButton m_AddScnBtn;
	private JTable m_SceneTable;
	private JButton m_EdtScnBtn;
	
	
	public class ButtonHandler implements ActionListener {
		private SceneManagerPanel scenePanel;

		public ButtonHandler (SceneManagerPanel scenePanel) {
			this.scenePanel = scenePanel;
		}

		@Override
		public void actionPerformed(ActionEvent btnPressed) 
		{
			if (btnPressed.getSource().equals(m_RmvScnBtn))
				removeSceneClicked();
			else if (btnPressed.getSource().equals(m_AddScnBtn))
				addSceneClicked();
			else if (btnPressed.getSource().equals(m_EdtScnBtn))
				editSceneClicked();
		}		
	}
	
	/**
	 * 
	 */
	public void removeSceneClicked()
	{
		// TODO implement
	}
	
	/**
	 * 
	 */
	public void addSceneClicked()
	{
		// TODO implement
	}
	
	/**
	 * 
	 */
	public void editSceneClicked()
	{
		// TODO implement
		EditSceneWindow esw = new EditSceneWindow( new Scene("testTitle", "testDescription"));
		esw.run();
	}
	/**
	 * Create the panel.
	 */
	public SceneManagerPanel(JTable scenesTable )
	{

		ButtonHandler btnHandler = new ButtonHandler(this);
		
		m_SceneTable = scenesTable;
		add(m_SceneTable, "cell 0 0 6 10,grow");
		
		m_AddScnBtn = new JButton("Add Scene");
		m_AddScnBtn.setBounds(133, 7, 103, 23);
		m_AddScnBtn.addActionListener(btnHandler);
		setLayout(null);
		add(m_AddScnBtn);

		m_RmvScnBtn = new JButton("Remove Scene");
		m_RmvScnBtn.setBounds(133, 34, 103, 23);
		m_RmvScnBtn.addActionListener(btnHandler);
		add(m_RmvScnBtn);
		
		m_EdtScnBtn = new JButton("Edit Scene");
		m_EdtScnBtn.setBounds(133, 61, 103, 23);
		m_EdtScnBtn.addActionListener(btnHandler);
		add(m_EdtScnBtn);

	}
}
