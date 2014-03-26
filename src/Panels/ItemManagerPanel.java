package Panels;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

	/**
	 * Create the panel.
	 */
	public ItemManagerPanel( )
	{
		setLayout(null);
		
		m_ItemsTable = new JTable();
		m_ItemsTable.setBounds(10, 287, 299, -276);
		add(m_ItemsTable);
		
		m_AddItmBtn = new JButton("Add Item");
		m_AddItmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		m_AddItmBtn.setBounds(327, 11, 113, 23);
		add(m_AddItmBtn);
		
		m_RmvItmBtn = new JButton("Remove Item");
		m_RmvItmBtn.setBounds(327, 45, 113, 23);
		add(m_RmvItmBtn);

	}
}
