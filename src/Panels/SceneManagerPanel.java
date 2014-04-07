package Panels;

import java.awt.*;

import Windows.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToolTip;

import Scene_Manager.Scene;
import Scene_Manager.SceneManager;
import TableModels.SceneTableModel;
import UserIO.WindowComm;
import net.miginfocom.swing.MigLayout;

import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;

/**
 * Main Panel for Managing all the scenes in the game, allows the user the
 * ability to add, remove and edit any scene from the list of scenes in the
 * scene manager.
 * 
 * @author Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManagerPanel extends JPanel implements MouseMotionListener{
	private int x, y;  // mouse x, y
	private JButton m_RmvScnBtn;
	private JButton m_AddScnBtn;
	private JTable m_SceneTable;
	private JScrollPane scrollPane;
	private JButton m_EdtScnBtn;
	private SceneManager m_SceneManager;
	private WindowComm m_WindowComm;
	private SceneTableModel m_SceneTableModel;
	private JTextField m_SceneTitleTextField;
	private JTextArea m_SceneDescriptionTextArea;
	private JTextField m_ItemDrops;
	private JTextField m_ItemUnlocks;


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
			if (toRemove != m_SceneManager.getStartScene() && toRemove != m_SceneManager.getEndScene())
			{
				if (m_WindowComm.getYesNo ("Are you sure you want to remove the Scene \"" + 
						toRemove.getTitle() + "\"?", "Remove Scene") == 0)
				{
					m_SceneManager.removeScene(toRemove);
					m_WindowComm.displayMessage("Scene Removed.");
				}
			}else
				m_WindowComm.displayMessage("You cannot remove the start scene or the end scene.");
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

	public boolean saveEdittedScene(Scene toSave, String newTitle)
	{
		boolean b_SceneAdded = m_SceneManager.saveScene(toSave, newTitle);
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
		m_SceneTable.addMouseMotionListener(this);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Scene Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(569, 11, 202, 359);
		add(panel);
		panel.setLayout(null);
		
		m_SceneTitleTextField = new JTextField();
		m_SceneTitleTextField.setBackground(SystemColor.control);
		m_SceneTitleTextField.setBounds(16, 45, 170, 23);
		panel.add(m_SceneTitleTextField);
		m_SceneTitleTextField.setColumns(10);
		m_SceneTitleTextField.setEditable(false);
		
		JLabel lblSceneTitle = new JLabel("Scene Title");
		lblSceneTitle.setBounds(16, 29, 84, 14);
		panel.add(lblSceneTitle);
		
		m_SceneDescriptionTextArea = new JTextArea();
		m_SceneDescriptionTextArea.setLineWrap(true);
		m_SceneDescriptionTextArea.setWrapStyleWord(true);
		m_SceneDescriptionTextArea.setBackground(SystemColor.control);
		m_SceneDescriptionTextArea.setBounds(16, 93, 170, 145);
		panel.add(m_SceneDescriptionTextArea);
		m_SceneDescriptionTextArea.setColumns(10);
		m_SceneDescriptionTextArea.setEditable(false);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(16, 79, 84, 14);
		panel.add(lblDescription);
		
		m_ItemUnlocks = new JTextField();
		m_ItemUnlocks.setBackground(SystemColor.control);
		m_ItemUnlocks.setBounds(16, 270, 170, 20);
		panel.add(m_ItemUnlocks);
		m_ItemUnlocks.setEditable(false);
		
		JLabel lblItemUnlockingThis = new JLabel("Item unlocking this scene");
		lblItemUnlockingThis.setBounds(16, 249, 170, 14);
		panel.add(lblItemUnlockingThis);
		
		m_ItemDrops = new JTextField();
		m_ItemDrops.setBackground(SystemColor.control);
		m_ItemDrops.setBounds(14, 322, 172, 20);
		panel.add(m_ItemDrops);
		m_ItemDrops.setColumns(10);
		m_ItemDrops.setEditable(false);
		
		JLabel lblItemDroppedOn = new JLabel("Item dropped on this scene");
		lblItemDroppedOn.setBounds(16, 301, 170, 14);
		panel.add(lblItemDroppedOn);
	}
	
	public void loadSceneManager(SceneManager sceneManager)
	{
		m_SceneManager = sceneManager;
		m_SceneTableModel = m_SceneManager.getSceneModel();
		m_SceneTable.setModel(m_SceneTableModel);
		m_SceneTableModel.fireTableDataChanged();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent event) 
	{
		Point p = event.getPoint();
		int row = m_SceneTable.rowAtPoint(p);
		if (row != -1)
		{
			Scene mouseOverScene = m_SceneTableModel.getSceneAt(row);
			m_SceneTitleTextField.setText(mouseOverScene.getTitle());
			m_SceneDescriptionTextArea.setText(mouseOverScene.getDesc());
			m_ItemDrops.setText(mouseOverScene.getDropItem() != null ? mouseOverScene.getDropItem().getName() : "None");
			m_ItemUnlocks.setText(mouseOverScene.getUnlockItem() != null ? mouseOverScene.getUnlockItem().getName() : "None");
		}else
			clearSceneFields();
	}
	
	public void clearSceneFields()
	{
		m_SceneTitleTextField.setText("");
		m_SceneDescriptionTextArea.setText("");
		m_ItemDrops.setText("");
		m_ItemUnlocks.setText("");
	}
}
