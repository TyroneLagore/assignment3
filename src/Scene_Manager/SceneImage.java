package Scene_Manager;

import javax.swing.ImageIcon;

public class SceneImage 
{
	private String m_ImageName;
	private ImageIcon m_Image;
	
	public SceneImage (String filename, String imageName)
	{
		m_Image = new ImageIcon (filename);
		m_ImageName = imageName;
	}

	public String getImageName() {
		return m_ImageName;
	}

	public ImageIcon getImage() {
		return m_Image;
	}
	
	public String toString()
	{
		return m_ImageName;
	}

}
