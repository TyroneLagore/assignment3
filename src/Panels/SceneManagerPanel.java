package Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

/**
 * Main Panel for Managing all the scenes in the game, allows the user the ability to 
 * add, remove and edit any scene from the list of scenes in the scene manager.
 *
 * @author	Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class SceneManagerPanel extends JPanel {
	private JButton m_EdtScnBtn;
	private JButton m_RmvScnBtn;
	private JButton m_AddScnBtn;
	private JTable m_SceneTable;
	
	/* Private Variables of this Panel */
	/* private JTable 		m_SceneTable;
	private JButton m_EdtScnBtn;
	private JButton m_RmvScnBtn;
	private JButton m_AddScnBtn;*/

	
	public class SceneManagerButtonHandler implements ActionListener {
		private SceneManagerPanel parent;

		public SceneManagerButtonHandler(SceneManagerPanel parent) {
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			/* TODO button handling goes here */
		}
	}
	
	/**
	 * Create the panel.
	 */
	public SceneManagerPanel(JTable scenesTable )
	{
		SceneManagerButtonHandler btnHandler = new SceneManagerButtonHandler(this);
		setLayout(new MigLayout("", "[grow][][][][][][][][][][][][][][][][][][grow]", "[grow,top][][grow][][][][][grow]"));

		m_SceneTable = scenesTable;
		add(m_SceneTable, "cell 0 0 6 10,grow");
		
		m_AddScnBtn = new JButton("Add Scene");
		m_AddScnBtn.addActionListener(btnHandler);
		add(m_AddScnBtn, "flowy,cell 7 0,growx");

		m_RmvScnBtn = new JButton("Remove Scene");
		m_RmvScnBtn.addActionListener(btnHandler);
		add(m_RmvScnBtn, "cell 7 0,growx");
		
		m_EdtScnBtn = new JButton("Edit Scene");
		m_EdtScnBtn.addActionListener(btnHandler);
		add(m_EdtScnBtn, "cell 7 0,growx");

	}
}
