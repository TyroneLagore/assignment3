package UserIO;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.*;

public class WindowComm extends JFrame
{
	private JFrame parent;
	
	public WindowComm (JFrame parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Gets a filename from the windows explorer from the user
	 * @param dialogueType FileDialog.SAVE or FileDialog.LOAD.  Is explorer doing a save or load?
	 * 
	 * @return String - the filename the user has specified - includes directory
	 */
	public String getFileFromExplorer(int dialogueType)
	{
		FileDialog fileGetter = new FileDialog(this);
		fileGetter.setMode(dialogueType);
		fileGetter.setVisible(true);

		
		return fileGetter.getDirectory() + fileGetter.getFile();
	}
	
	
	public int getYesNo(String prompt, String title)
	{
		Object[] options = {"Yes", "No"};
		return JOptionPane.showOptionDialog(parent, prompt, title, 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
				options, options[0]);
	}
	
	public int getYesNoCancel (String prompt, String title)
	{
		Object[] options = {"Yes", "No", "Cancel"};
		
		return JOptionPane.showOptionDialog(parent, 
				"Would you like to save before Quitting?", 
				"Exit", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
				options, options[0]);
	}
	
	public void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(parent , message);
	}
}
