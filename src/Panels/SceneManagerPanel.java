package Panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import Scene_Manager.Scene;
import Scene_Manager.SceneManager;
import TableModels.SceneTableModel;
import UserIO.WindowComm;
import Windows.EditSceneWindow;
import Windows.MainWindow;
import net.miginfocom.swing.MigLayout;

/**
 * Main Panel for Managing all the scenes in the game, allows the user the
 * ability to add, remove and edit any scene from the list of scenes in the
 * scene manager.
 * 
 * @author Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManagerPanel extends JPanel {
	private JButton m_RmvScnBtn;
	private JButton m_AddScnBtn;
	private JTable m_SceneTable;
	private JScrollPane scrollPane;
	private JButton m_EdtScnBtn;
	private SceneManager m_SceneManager;
	private WindowComm m_WindowComm;
	

	public class ButtonHandler implements ActionListener {
		private SceneManagerPanel scenePanel;

		public ButtonHandler(SceneManagerPanel scenePanel) {
			this.scenePanel = scenePanel;
		}

		@Override
		public void actionPerformed(ActionEvent btnPressed) {
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
		if (m_WindowComm.getYesNo ("Are you sure you want to remove the selected scene?", "Remove Scene") == 0)
			// TODO remove the scene
			m_WindowComm.displayMessage("Removed the scene - test message remove upon implementation");
		else
			m_WindowComm.displayMessage("Did not remove the scene - test message remove upon implementation");
	}

	/**
	 * 
	 */
	public void addSceneClicked()
	{
		Scene newScene = m_SceneManager.addScene();
		newScene.addConnection(new Scene("Fun", "Uh yeah"), "Go east");
		newScene.addConnection(new Scene("This is another Scene", "Does stuff"), "Go west");
		newScene.addConnection(new Scene("This is another Scene", "Does stuff"), "Go west");
		openEditSceneWindow (newScene);		
		// TODO implement
	}

	/**
	 * 
	 */
	public void editSceneClicked() {
		// TODO implement
		EditSceneWindow esw = new EditSceneWindow(new Scene("testTitle",
				"testDescription"), this);
		toggleSaveEditEnabled(false);
		esw.run();
	}
	
	private void openEditSceneWindow(Scene toEdit)
	{
		toggleSaveEditEnabled(false);
		EditSceneWindow esw = new EditSceneWindow (toEdit, this);
		esw.run();
	}


	public void saveEdittedScene(Scene toSave)
	{
		if (!m_SceneManager.saveScene(toSave))
		{
			m_WindowComm.displayMessage("Title conflict.  Please rename scene.");
			openEditSceneWindow (toSave);
		}else
			toggleSaveEditEnabled(true);

	}
	
	public void editSceneWindowHasClosed()
	{
		toggleSaveEditEnabled(true);
	}
	
	private void toggleSaveEditEnabled(boolean b_Toggle)
	{
		m_AddScnBtn.setEnabled(b_Toggle);
		m_RmvScnBtn.setEnabled(b_Toggle);
		m_EdtScnBtn.setEnabled(b_Toggle);
	}
	/**
	 * Create the panel.
	 */
	public SceneManagerPanel(SceneTableModel scenesTable, MainWindow mWindow, SceneManager sManager) {

		ButtonHandler btnHandler = new ButtonHandler(this);
		m_WindowComm = new WindowComm (mWindow);
		m_SceneManager = sManager;

		
		m_SceneTable = new JTable (scenesTable);
		
		scrollPane = new JScrollPane(m_SceneTable);
		scrollPane.setBounds(10, 11, 298, 251);
		add(scrollPane);
		scrollPane.setViewportView(m_SceneTable);	

		m_AddScnBtn = new JButton("Add Scene");
		m_AddScnBtn.setBounds(338, 8, 103, 23);
		m_AddScnBtn.addActionListener(btnHandler);
		setLayout(null);
		add(m_AddScnBtn);

		m_RmvScnBtn = new JButton("Remove Scene");
		m_RmvScnBtn.setBounds(338, 42, 103, 23);
		m_RmvScnBtn.addActionListener(btnHandler);
		add(m_RmvScnBtn);

		m_EdtScnBtn = new JButton("Edit Scene");
		m_EdtScnBtn.setBounds(338, 76, 103, 23);
		m_EdtScnBtn.addActionListener(btnHandler);
		add(m_EdtScnBtn);
		

		

	}
}
