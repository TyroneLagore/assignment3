package Windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Game_System.Item;
import Game_System.Player;
import Scene_Manager.Scene;
import Scene_Manager.SceneManager;
import UserIO.WindowComm;

import javax.swing.*;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;

/**
 * 
 * @author Tyrone Lagore
 * @version April 6, 2014
 */

public class RunGameWindow extends JFrame
{
	private MainWindow m_Parent;
	private Player m_Player;
	private SceneManager m_SceneManager;
	private WindowComm m_WindowComm;
	private JLabel m_lblTitle;
	private JTextArea m_SceneDescTextArea;
	private Scene m_CurrentScene;
	private JRadioButton []m_Choices;
	private ButtonGroup m_ButtonGroup;
	private static final int NUM_CHOICES = 4;
	private JButton btnGo;
	private JButton btnInventory;
	private JButton btnJournal;
	private JButton btnBacktrack;
	private JTextArea m_NoteTextArea;
	
	public class ButtonHandler implements ActionListener 
	{
		private RunGameWindow window;

		public ButtonHandler(RunGameWindow window) {
			this.window = window;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(btnGo))
				getNextScene();
			else if (e.getSource().equals(btnInventory))
				openInventory();
			else if (e.getSource().equals(btnJournal))
				openJournal();
			else if (e.getSource().equals(btnBacktrack))
				backTrackSelected();

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
	
	
	public RunGameWindow (MainWindow parent, SceneManager sceneManager)
	{
		m_SceneManager = sceneManager;
		m_Player = m_SceneManager.getPlayer();
		m_Parent = parent;
		m_Player.clearPlayerInventory();
		m_SceneManager.clearVisitedScenes();
		m_WindowComm = new WindowComm(this);
		getContentPane().setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}
		});
		
		m_ButtonGroup = new ButtonGroup();
		
		m_Choices = new JRadioButton[NUM_CHOICES];
		
		m_WindowComm = new WindowComm(this);
		ButtonHandler btnHandler = new ButtonHandler(this);
		setBounds(100, 100, 540, 407);
		
		m_lblTitle = new JLabel();
		m_lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 23));
		m_lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		m_lblTitle.setBounds(10, 31, 504, 39);
		getContentPane().add(m_lblTitle);
		
		JPanel m_DescriptionBorderPanel = new JPanel();
		m_DescriptionBorderPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		m_DescriptionBorderPanel.setBounds(26, 81, 370, 133);
		getContentPane().add(m_DescriptionBorderPanel);
		m_DescriptionBorderPanel.setLayout(null);
		
		m_SceneDescTextArea = new JTextArea();
		m_SceneDescTextArea.setEditable(false);
		m_SceneDescTextArea.setBounds(10, 16, 350, 110);
		m_DescriptionBorderPanel.add(m_SceneDescTextArea);
		m_SceneDescTextArea.setBackground(SystemColor.control);
		m_SceneDescTextArea.setWrapStyleWord(true);
		m_SceneDescTextArea.setLineWrap(true);
		
		btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(btnHandler);
		btnInventory.setBounds(406, 301, 108, 23);
		getContentPane().add(btnInventory);
		
		btnGo = new JButton("Go");
		btnGo.addActionListener(btnHandler);
		btnGo.setBounds(10, 335, 115, 23);
		getContentPane().add(btnGo);
		
		m_NoteTextArea = new JTextArea();
		m_NoteTextArea.setText("");
		m_NoteTextArea.setBounds(406, 81, 108, 133);
		getContentPane().add(m_NoteTextArea);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		lblNote.setBounds(406, 56, 108, 14);
		getContentPane().add(lblNote);
		
		btnJournal = new JButton("Journal");
		btnJournal.addActionListener(btnHandler);
		btnJournal.setBounds(406, 267, 108, 23);
		getContentPane().add(btnJournal);
		
		btnBacktrack = new JButton("Backtrack");
		btnBacktrack.addActionListener(btnHandler);
		btnBacktrack.setBounds(406, 335, 108, 23);
		getContentPane().add(btnBacktrack);
		m_CurrentScene = m_SceneManager.getStartScene();
		loadSceneInfo (m_SceneManager.getStartScene());
	}
	

	private void closeWindow()
	{
		m_Parent.testWindowHasClosed();
		dispose();
	}
	
	private void openInventory(){}
	
	private void openJournal()
	{
		JournalWindow jw = new JournalWindow(this, m_SceneManager.getNotes());
		toggleButtons(false);
		jw.run();
		
	}
	
	private void getNextScene()
	{
		if (m_CurrentScene.equals(m_SceneManager.getEndScene()))
			closeWindow();
		
		int selected = -1;
		for (int i = 0; i < m_CurrentScene.getConnections().size(); i++)
			if (m_Choices[i].isSelected())
				selected = i;
		
		if (selected != -1)
			loadSceneInfo(  m_CurrentScene.getConnections().get( selected) );
	}
	
	private void backTrackSelected()
	{
		BacktrackWindow btw = new BacktrackWindow(this, m_SceneManager.getVisitedScenes());
		toggleButtons(false);
		btw.run();
	}
	
	private void loadSceneInfo( Scene nextScene )
	{
		Item unlockItem = nextScene.getUnlockItem();
		String addedText = nextScene.getDropItem() != null ? 
				"You receive loot: " + nextScene.getDropItem().getName() + "\n\n" : "";
		

		if (unlockItem == null || m_Player.inventoryContains(unlockItem))
		{
			m_CurrentScene.setNote(m_NoteTextArea.getText());
			
			m_CurrentScene = nextScene;
			m_SceneManager.addVisitedScene(m_CurrentScene);
			
			m_NoteTextArea.setText(m_CurrentScene.getNote());
			
			if (addedText.length() > 0)
				m_Player.addItem(nextScene.getDropItem());
			
			m_lblTitle.setText(m_CurrentScene.getTitle());
			m_SceneDescTextArea.setText(addedText + m_CurrentScene.getDesc());
			generateChoices();
			
			if (m_CurrentScene.equals(m_SceneManager.getEndScene()))
				btnGo.setText("Exit");
			else
				btnGo.setText("Go");
				
		}else
			m_WindowComm.displayMessage("You require a " + unlockItem.getName() + " to enter that"
					+ " scene!.");
	}
	
	private void generateChoices()
	{
		int x = 15;
		int y = 230;
		int width = 380;
		int height = 20;
		int numButtons = m_ButtonGroup.getButtonCount();
		
		ArrayList<String> connectionLabels = m_CurrentScene.getConnectionLabels();

		
		for (int i = 0; i < numButtons; i++)
		{
			getContentPane().remove(m_Choices[i]);
			m_ButtonGroup.remove(m_Choices[i]);
		}
		
		revalidate();
		repaint();
		
		for(int i = 0; i < m_CurrentScene.getConnections().size(); i++)
		{
			m_Choices[i] = new JRadioButton(connectionLabels.get(i));
			m_Choices[i].setBounds(x, y, width, height);
			getContentPane().add(m_Choices[i]);
			m_ButtonGroup.add(m_Choices[i]);
			
			y+= 25;
		}	
		
		if (m_CurrentScene.getConnections().size() > 0)
			m_Choices[0].setSelected(true);
	}


	public void aWindowHasClosed() 
	{
		toggleButtons(true);
	}
	
	public void toggleButtons(boolean bToggle)
	{
		btnBacktrack.setEnabled(bToggle);
		btnGo.setEnabled(bToggle);
		btnInventory.setEnabled(bToggle);
		btnJournal.setEnabled(bToggle);
	}


	public void goToScene(Scene whereToGo) 
	{
		loadSceneInfo ( whereToGo );
		
	}

}
