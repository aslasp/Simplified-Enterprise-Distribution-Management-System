package Others;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int jfHeight, jfWidth;

	public AboutFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		jfHeight = 384;
		jfWidth = 274;
		// 获得了与分辨率匹配的大小
		this.setSize(jfWidth, jfHeight);
		this.setLocation((screenWidth - jfWidth) / 2,
				(screenHeight - jfHeight) / 2);
		// 设置好了窗口大小，位置
		this.setLayout(new GridLayout(2, 1));
		JPanel imgPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("GUI/About.png");
				Image img = icon.getImage();
				g.drawImage(img, (jfWidth - icon.getIconWidth()) / 2,
						(jfHeight * 4 / 7 - icon.getIconHeight()) / 2,
						icon.getIconWidth(), icon.getIconHeight(),
						icon.getImageObserver());
			}
		};
		this.add(imgPanel);
		JPanel textPanel = new JPanel();
		JTextArea text = new JTextArea();
		File file = new File("File/About.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = "";
		try {
			s = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (s != null) {
			text.append(s + "\n");
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Font f = new Font("宋体", Font.BOLD, 14);
		text.setFont(f);
		text.setEnabled(false);
		textPanel.add(text);
		this.add(textPanel);
		this.setTitle("About");
		this.repaint();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
