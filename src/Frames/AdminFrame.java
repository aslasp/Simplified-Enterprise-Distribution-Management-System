package Frames;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import program.Users;
import FPanels.AdminPanel;

public class AdminFrame extends StandardFrame implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AdminPanel ap;
	public AdminFrame(Users user) throws ClassNotFoundException, IOException{
        super(user);
		this.setTitle("进销存系统-账户管理员");
		// 设置了窗口标题
		ap=new AdminPanel();
		leftPanel.setBackground(new Color(44,128,197));
		rightPanel = new JPanel(){
			/**
			 * 
			 */
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("GUI/anohana.png");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, icon.getIconWidth(),
				icon.getIconHeight(), icon.getImageObserver());
	}
		};
		rightPanel.setSize(jfWidth * 4 / 5, jfHeight);
		rightPanel.setLocation(jfWidth / 5, 0);
		rightPanel.setBackground(Color.WHITE);
		this.add(rightPanel);
		// 设置右panel
		homeButton.setBackground(new Color(44,128,197));
		userButton.setBackground(new Color(44,128,197));
		aboutButton.setBackground(new Color(44,128,197));
		changeButton.setBackground(new Color(44,128,197));
		logoutButton.setBackground(new Color(44,128,197));
		userButton.addActionListener(new userButtonListener());
		homeButton.addActionListener(new homeButtonListener());
		this.add(ap);
	}
	class homeButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			rightPanel.setVisible(false);
			up.setVisible(false);
			ap.setVisible(true);
			AdminFrame.this.repaint();
		}
		
	}
	class userButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rightPanel.setVisible(false);
			ap.setVisible(false);
			up.setVisible(true);
			AdminFrame.this.repaint();
		}
		
	}
}
