package Panels;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;


/**
 * Panel used for monitoring/managing Items.
 *
 * @author	Team Smart Water
 * @version v1.0 - Mar 25, 2014
 */
public class ItemManagerPanel extends JPanel {
	/* Private Variables */
	private JTable 		m_ItemsTable;
	private JButton 	m_RmvItmBtn;
	private JButton 	m_AddItmBtn;

	
	public class ItemManagerButtonHandler implements ActionListener {
		private ItemManagerPanel parent;

		public ItemManagerButtonHandler(ItemManagerPanel parent) {
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
	public ItemManagerPanel( JTable itemsTable )
	{

		ItemManagerButtonHandler btnHandler = new ItemManagerButtonHandler(this);
		setLayout(new MigLayout("", "[grow][][][][][][][][][][][][][][][][][][grow]", "[grow,top][][grow][][][][][grow]"));
	
		m_ItemsTable = itemsTable;
		add(m_ItemsTable, "cell 0 0 6 10,grow");
		
		m_AddItmBtn = new JButton("Add Item");
		m_AddItmBtn.addActionListener(btnHandler);
		add(m_AddItmBtn, "flowy,cell 7 0,growx");

		m_RmvItmBtn = new JButton("Remove Item");
		m_RmvItmBtn.addActionListener(btnHandler);
		add(m_RmvItmBtn, "cell 7 0,growx");
	}
}
