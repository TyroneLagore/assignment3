package Panels;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;

/**
 * Main Panel for Managing all the scenes in the game, allows the user the ability to 
 * add, remove and edit any scene from the list of scenes in the scene manager.
 *
 * @author	Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManagerPanel extends JPanel {
	
	/* Private Variables of this Panel */
	private JTable 		m_SceneTable;
	private JButton 	m_AddScnBtn;
	private JButton 	m_RmvScnBtn;

	/**
	 * Create the panel.
	 */
	public SceneManagerPanel( )
	{
		setLayout(null);
		
		m_SceneTable = new JTable();
		m_SceneTable.setBounds(10, 281, 297, -269);
		add(m_SceneTable);
		
		m_AddScnBtn = new JButton("Add Scene");
		m_AddScnBtn.setBounds(321, 11, 119, 23);
		add(m_AddScnBtn);
		
		m_RmvScnBtn = new JButton("Remove Scene");
		m_RmvScnBtn.setBounds(321, 45, 119, 23);
		add(m_RmvScnBtn);
		
		JButton m_EdtScnBtn = new JButton("Edit Scene");
		m_EdtScnBtn.setBounds(321, 79, 119, 23);
		add(m_EdtScnBtn);

	}

}
