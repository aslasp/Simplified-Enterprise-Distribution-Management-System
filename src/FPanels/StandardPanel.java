package FPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.Serializable;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StandardPanel extends JPanel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox jcb;
	JLabel label;
	
	int jfHeight,jfWidth;
	public StandardPanel(){
		this.setLayout(null);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize =kit.getScreenSize();
		int screenHeight=screenSize.height;
		int screenWidth=screenSize.width;
		jfHeight=512;
		jfWidth=819;
		//获得了与分辨率匹配的大小
		this.setSize(jfWidth * 4 / 5, jfHeight);
		this.setLocation(jfWidth / 5, 0);
		this.setBackground(Color.white);
		//设置好了位置与大小与颜色
		label=new JLabel("请选择功能：");
		label.setSize(jfWidth * 2 / 5, jfHeight/18);
		label.setLocation(jfWidth * 12/100, jfHeight*3/100);
		label.setFont(new Font("宋体",Font.TRUETYPE_FONT,15));	
		this.add(label);
		this.setVisible(false);
	}

}
