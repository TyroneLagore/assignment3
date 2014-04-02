package Windows;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import Panels.ItemManagerPanel;
import UserIO.WindowComm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditItemWindow extends JFrame {

	private JLabel 				lblItemTitleLg;
	private JTextField 			textField;
	private JTextArea 			m_DescTextArea;
	private JTextField 			m_DropSceneLbl;
	private JTextField 			m_UnlockSceneLbl;
	private WindowComm 			m_CommWindow;
	private ItemManagerPanel 	m_ItmMngrParent;

	
	public class ItemButtonHandler implements ActionListener {
		private EditItemWindow window;

		public ItemButtonHandler(EditItemWindow window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	}
	
	public class ItemMouseHandler implements MouseListener
	{
		private EditItemWindow m_Window;
		
		public ItemMouseHandler( EditItemWindow parentWindow )
		{
			this.m_Window = parentWindow;
		}

		@Override
        public void mousePressed( MouseEvent e )	
		{
			if( e.getSource( ).equals( m_DropSceneLbl ) )
				m_CommWindow.displayMessage( "Drop Scene Lable Clicked!" );
			else if( e.getSource( ).equals( m_UnlockSceneLbl ) )
				m_CommWindow.displayMessage( "Unlock Scene Lable Clicked!" );
		}

		/* Unimplemented, but inherited events as required by MouseListener children. */
		@Override
        public void mouseClicked( MouseEvent arg0 )	{ }

		@Override
        public void mouseEntered( MouseEvent e ) 	{ }

		@Override
        public void mouseExited( MouseEvent e )		{ }

		@Override
        public void mouseReleased( MouseEvent e )	{ }
		
	}
	
	/****************************************************************\
	 * Edit Item Window Function Block								*
	\****************************************************************/
	
	private void closeWindow()
	{
		m_ItmMngrParent.editItemWindowHasClosed();
	}
	
	/****************************************************************\
	 * End Edit Item Window Function Block							*
	\****************************************************************/
	/**
	 * Create the application.
	 */
	public EditItemWindow( ItemManagerPanel parentWindow )
	{
		getContentPane().setLayout(null);
		m_ItmMngrParent = parentWindow;
		
		/* Init some local variables */
		m_CommWindow = new WindowComm( this );
		ItemMouseHandler m_ItmMouseHndlr = new ItemMouseHandler( this );
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				closeWindow();
			}
		});
		
		/* set up location of Window */
		setBounds( 100, 100, 830, 440 );
		Point parentLocation = m_ItmMngrParent.getLocation();
		double parentX = parentLocation.getX();
		double parentY = parentLocation.getY();
		
		setLocation((int)(parentX + 125),(int)(parentY + 125));
		
		lblItemTitleLg = new JLabel("");
		lblItemTitleLg.setBounds(0, 0, 653, 31);
		lblItemTitleLg.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemTitleLg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblItemTitleLg);
		
		textField = new JTextField();
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textField.setBounds(10, 72, 193, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setBounds(10, 47, 63, 14);
		getContentPane().add(lblItemName);
		
		m_DescTextArea = new JTextArea();
		m_DescTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		m_DescTextArea.setLineWrap(true);
		m_DescTextArea.setBounds(10, 148, 299, 225);
		getContentPane().add(m_DescTextArea);
		
		JLabel lblItemDescription = new JLabel("Item Description:");
		lblItemDescription.setBounds(10, 123, 110, 14);
		getContentPane().add(lblItemDescription);
		
		JLabel sceneDroppedLbl = new JLabel("Item Dropped in:");
		sceneDroppedLbl.setBounds(444, 42, 199, 14);
		getContentPane().add(sceneDroppedLbl);
		
		m_DropSceneLbl = new JTextField();
		m_DropSceneLbl.addMouseListener( m_ItmMouseHndlr );
		m_DropSceneLbl.setEditable(false);
		m_DropSceneLbl.setBounds(444, 67, 199, 20);
		getContentPane().add(m_DropSceneLbl);
		m_DropSceneLbl.setColumns(10);
		
		JLabel sceneUnLockTitleLbl = new JLabel("Item Unlocks:");
		sceneUnLockTitleLbl.setBounds(444, 98, 199, 14);
		getContentPane().add(sceneUnLockTitleLbl);
		
		m_UnlockSceneLbl = new JTextField();
		m_UnlockSceneLbl.addMouseListener( m_ItmMouseHndlr );
		m_UnlockSceneLbl.setEditable(false);
		m_UnlockSceneLbl.setColumns(10);
		m_UnlockSceneLbl.setBounds(444, 123, 199, 20);
		getContentPane().add(m_UnlockSceneLbl);
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
}
