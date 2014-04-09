package Windows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import UserIO.WindowComm;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;

public class JournalWindow extends JFrame
{
	private RunGameWindow m_Parent;
	
	public class ButtonHandler implements ActionListener 
	{
		private JournalWindow window;

		public ButtonHandler(JournalWindow window) {
			this.window = window;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			closeWindow();
		}
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
	
	
	public JournalWindow (RunGameWindow parent, String notes, String font)
	{
		m_Parent = parent;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}


		});

		ButtonHandler btnHandler = new ButtonHandler(this);
		setBounds(400, 100, 529, 388);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 361, 328);
		getContentPane().add(scrollPane);
		
		JTextArea m_NoteTextArea = new JTextArea();
		m_NoteTextArea.setLineWrap(true);
		m_NoteTextArea.setWrapStyleWord(true);
		m_NoteTextArea.setFont(new Font(font, Font.PLAIN, 13));
		m_NoteTextArea.setBackground(SystemColor.control);
		scrollPane.setViewportView(m_NoteTextArea);
		m_NoteTextArea.setEditable(false);
		
		m_NoteTextArea.setText(notes);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(btnHandler);
		btnReturn.setBounds(381, 13, 122, 23);
		getContentPane().add(btnReturn);

	}
	public void closeWindow() 
	{
		m_Parent.aWindowHasClosed();
		dispose();	
	}
}
