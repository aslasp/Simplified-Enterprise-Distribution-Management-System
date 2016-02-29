package Mgr;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchMgr extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int seHeight,seWidth;
	JTable searchResult;
	JScrollPane srjsp;
	public SearchMgr(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize =kit.getScreenSize();
		int screenHeight=screenSize.height;
		int screenWidth=screenSize.width;
		seHeight=512;
		seWidth=683;
		//获得了与分辨率匹配的大小
		this.setSize(seWidth , seHeight);
		this.setLocation((screenWidth - seWidth) / 2,
				(screenHeight - seHeight) / 2);
		this.setLayout(null);
		this.repaint();
		//
		this.setVisible(true);
	}

}
