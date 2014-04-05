package Panels;

import java.awt.*;

import Windows.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import Scene_Manager.Scene;
import Scene_Manager.SceneManager;
import TableModels.SceneTableModel;
import UserIO.WindowComm;
import net.miginfocom.swing.MigLayout;

import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

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
	private SceneTableModel m_SceneTableModel;


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
		int selectedSceneIndex;
		Scene toRemove;
		if (m_SceneTable.getSelectedRow() != -1)
		{
			selectedSceneIndex = m_SceneTable.getSelectedRow();
			toRemove = m_SceneTableModel.getSceneAt(selectedSceneIndex);
		
			if (m_WindowComm.getYesNo ("Are you sure you want to the Scene \"" + 
					toRemove.getTitle() + "\"?", "Remove Scene") == 0)
			{
				m_SceneManager.removeScene(toRemove);
				m_WindowComm.displayMessage("Scene Removed.");
			}
		}
	}

	/**
	 * 
	 */
	public void addSceneClicked()
	{
		Scene newScene = m_SceneManager.addScene();
		openEditSceneWindow (newScene);
	}

	/**
	 * 
	 */
	public void editSceneClicked() 
	{
		if (m_SceneTable.getSelectedRow() != -1)
			openEditSceneWindow(m_SceneTableModel.getSceneAt(m_SceneTable.getSelectedRow()));
	}
	
	private void openEditSceneWindow(Scene toEdit)
	{
		toggleSaveEditEnabled(false);
		EditSceneWindow esw = new EditSceneWindow (toEdit, this, m_SceneManager);
		esw.run();
	}

	public boolean saveEdittedScene(Scene toSave)
	{
		boolean b_SceneAdded = m_SceneManager.saveScene(toSave);
		if (b_SceneAdded)
			toggleSaveEditEnabled(true);
	
		return b_SceneAdded;
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

		m_SceneTableModel = scenesTable;
		m_SceneTable = new JTable (scenesTable);
		m_SceneTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m_SceneTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		m_SceneTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		m_SceneTable.setFillsViewportHeight(true);
		m_SceneTable.setColumnSelectionAllowed(false);
		
		scrollPane = new JScrollPane(m_SceneTable);
		scrollPane.setBounds(10, 11, 436, 432);
		add(scrollPane);
		scrollPane.setViewportView(m_SceneTable);	

		m_AddScnBtn = new JButton("Add");
		m_AddScnBtn.setToolTipText("Add a new scene.");
		m_AddScnBtn.setBounds(456, 8, 103, 23);
		m_AddScnBtn.addActionListener(btnHandler);
		setLayout(null);
		add(m_AddScnBtn);

		m_RmvScnBtn = new JButton("Remove\r\n");
		m_RmvScnBtn.setToolTipText("Remove the selected scene.");
		m_RmvScnBtn.setBounds(456, 41, 103, 23);
		m_RmvScnBtn.addActionListener(btnHandler);
		add(m_RmvScnBtn);

		m_EdtScnBtn = new JButton("Edit");
		m_EdtScnBtn.setToolTipText("Edit the selected scene.");
		m_EdtScnBtn.setBounds(456, 75, 103, 23);
		m_EdtScnBtn.addActionListener(btnHandler);
		add(m_EdtScnBtn);
	}


}
