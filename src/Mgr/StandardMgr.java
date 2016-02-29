package Mgr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class StandardMgr extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int jfHeight,jfWidth;
	public StandardMgr(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize =kit.getScreenSize();
		int screenHeight=screenSize.height;
		int screenWidth=screenSize.width;
		jfHeight=512;
		jfWidth=819;
		//获得了与分辨率匹配的大小
		this.setSize(jfWidth * 4 / 5, jfHeight);
		this.setLocation(jfWidth/100,jfHeight*10/100);
		this.setBackground(Color.white);
		this.setLayout(null);
		this.repaint();
		//
		this.setVisible(false);
	}
}
