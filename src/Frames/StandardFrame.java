package Frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import program.Users;
import Others.AboutFrame;
import Others.LoginFrame;
import Others.UserPanel;

//构建一个标准框架,这个框架便是程序主界面，分为左右两个panel
public class StandardFrame extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel leftPanel, rightPanel;
	JButton homeButton, userButton, aboutButton, changeButton, logoutButton;
	int jfHeight, jfWidth;
	Users user;
	UserPanel up;
	JLabel timeLabel;

	public StandardFrame(Users user) {
		up = new UserPanel(user);
		this.setLayout(null);
		this.user = user;
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		jfHeight = 512;
		jfWidth = 819;
		// 获得了与分辨率匹配的大小
		this.setSize(jfWidth, jfHeight);
		this.setLocation((screenWidth - jfWidth) / 2,
				(screenHeight - jfHeight) / 2);
		// 设置好了窗口大小，位置
		Image image = kit.getImage("GUI/Login/icon.png");
		this.setIconImage(image);
		// 设置了图标
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(8, 1));
		leftPanel.setSize(jfWidth * 22 / 100, jfHeight);
		leftPanel.setBackground(new Color(204, 0, 51));
		// 设置左panel

		homeButton = new JButton("主页", new ImageIcon("GUI/Admin/icon-home.png"));
		homeButton.setFont(new Font("宋体", Font.PLAIN, 14));
		homeButton.setForeground(Color.white);
		homeButton.setBackground(new Color(204, 0, 51));
		homeButton.setHorizontalAlignment(SwingConstants.LEFT);
		homeButton.setFocusPainted(false);

		leftPanel.add(homeButton);
		// 设置home按钮
		String name = user.username;
		userButton = new JButton(name, new ImageIcon("GUI/Admin/icon-user.png"));
		userButton.setFont(new Font("宋体", Font.PLAIN, 14));
		userButton.setForeground(Color.white);
		userButton.setBackground(new Color(204, 0, 51));
		userButton.setHorizontalAlignment(SwingConstants.LEFT);
		userButton.setFocusPainted(false);
		leftPanel.add(userButton);
		// 设置账户按钮
		aboutButton = new JButton("关于本程序", new ImageIcon(
				"GUI/Admin/icon-about.png"));
		aboutButton.setFont(new Font("宋体", Font.PLAIN, 14));
		aboutButton.setForeground(Color.white);
		aboutButton.setBackground(new Color(204, 0, 51));
		aboutButton.setHorizontalAlignment(SwingConstants.LEFT);
		aboutButton.setFocusPainted(false);
		aboutButton.addActionListener(new aboutButtonListener());
		leftPanel.add(aboutButton);
		// 设置关于按钮
		changeButton = new JButton("切换用户", new ImageIcon(
				"GUI/Admin/icon-change.png"));
		changeButton.setFont(new Font("宋体", Font.PLAIN, 14));
		changeButton.setForeground(Color.white);
		changeButton.setBackground(new Color(204, 0, 51));
		changeButton.setHorizontalAlignment(SwingConstants.LEFT);
		changeButton.setFocusPainted(false);
		changeButton.addActionListener(new changeButtonListener());
		leftPanel.add(changeButton);
		// 设置切换用户按钮
		logoutButton = new JButton("退出系统", new ImageIcon(
				"GUI/Admin/icon-exit.png"));
		logoutButton.setFont(new Font("宋体", Font.PLAIN, 14));
		logoutButton.setForeground(Color.white);
		logoutButton.setBackground(new Color(204, 0, 51));
		logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
		logoutButton.addActionListener(new logoutButtonListener());
		logoutButton.setFocusPainted(false);
		leftPanel.add(logoutButton);
		// 设置退出按钮
		//
		this.repaint();
		this.addMouseListener(new SFListener());
		this.add(leftPanel);

		this.add(up);
		this.setResizable(false);// 不允许调整窗口大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭时结束进程
		this.setVisible(true);
	}

	class aboutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AboutFrame af = new AboutFrame();
		}
	}

	class changeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			LoginFrame lf = new LoginFrame();
			StandardFrame.this.dispose();
		}

	}

	class logoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}

	}

	class SFListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			StandardFrame.this.repaint();

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			StandardFrame.this.repaint();

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			StandardFrame.this.repaint();

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			StandardFrame.this.repaint();

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			StandardFrame.this.repaint();

		}

	}
}
