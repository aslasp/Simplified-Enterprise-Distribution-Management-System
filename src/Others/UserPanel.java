package Others;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import program.IOhelper;
import program.Users;
//这个类实现了第二个按钮的对应面板
public class UserPanel extends JPanel implements Serializable{
	Users user;
	String oldkey,newkey;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserPanel(Users user){
		this.user=user;
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize =kit.getScreenSize();
		int screenHeight=screenSize.height;
		int screenWidth=screenSize.width;
		int jfHeight=512;
		int jfWidth=819;
		//获得了与分辨率匹配的大小
		this.setSize(jfWidth * 4 / 5, jfHeight);
		this.setLocation(jfWidth / 5, 0);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		//
		JPanel infoPanel=new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("GUI/giant.png");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};
		infoPanel.setLayout(new GridLayout(15,1));
		JLabel name=new JLabel("   用户名："+user.username);
		name.setFont(new Font("宋体", Font.BOLD, 18));
		name.setForeground(new Color(201,129,80));
		name.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel type=new JLabel("   用户类型："+user.role);
		type.setFont(new Font("宋体", Font.BOLD, 18));
		type.setForeground(new Color(201,129,80));
		type.setHorizontalAlignment(SwingConstants.LEFT);
		infoPanel.setBackground(Color.white);
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(name);
		infoPanel.add(type);
		
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());
		
		
		//
		JPanel CPanel=new JPanel();	
		CPanel.setBackground(new Color(253,250,241));
		JButton changePasswordButton=new JButton("修改密码", new ImageIcon(
				"GUI/Admin/icon-password.png"));
		changePasswordButton.setFocusPainted(false); 
		changePasswordButton.setSize(jfWidth /6, jfHeight/16);
		changePasswordButton.setLocation(jfWidth*35/100, jfHeight*35/100);
		changePasswordButton.setHorizontalAlignment(SwingConstants.CENTER);
		changePasswordButton.setFont(new Font("宋体", Font.PLAIN, 14));
		changePasswordButton.setForeground(Color.white);
		changePasswordButton.setBackground(new Color(40,49,68));
		changePasswordButton.addActionListener(new changeKeyListener());
		CPanel.add(changePasswordButton);
		infoPanel.add(CPanel);
		infoPanel.add(new JLabel());
		//
		this.add(infoPanel,BorderLayout.CENTER);
		this.repaint();
		this.setVisible(false);
	}
	class changeKeyListener implements ActionListener,Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JFrame ckf;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ckf=new JFrame();
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize =kit.getScreenSize();
			int screenHeight=screenSize.height;
			int screenWidth=screenSize.width;
			int jfHeight=307;
			int jfWidth=455;
			//获得了与分辨率匹配的大小
			ckf.setSize(jfWidth,jfHeight);
			ckf.setLocation((screenWidth-jfWidth)/2,(screenHeight-jfHeight)/2);
			//设置好了窗口大小，位置
			ckf.setTitle("修改密码");
			//
			JPanel panel=new JPanel(){
				protected void paintComponent(Graphics g) {
					ImageIcon icon = new ImageIcon("GUI/jieni.jpg");
					Image img = icon.getImage();
					g.drawImage(img, 0, 0, icon.getIconWidth(),
							icon.getIconHeight(), icon.getImageObserver());
				}
			};
			panel.setSize(jfWidth,jfHeight);
			panel.setLocation(0,0);
			panel.setLayout(null);
			ckf.add(panel);
			JLabel label=new JLabel("用户名：     "+UserPanel.this.user.username);
			label.setFont(new Font("用户名", Font.PLAIN, 12));
			label.setSize(jfWidth,jfHeight/10);
			label.setLocation(jfWidth/7,jfHeight*26/100);
			//
			JLabel oldlabel=new JLabel("旧密码：");
			oldlabel.setFont(new Font("用户名", Font.PLAIN, 12));
			oldlabel.setSize(jfWidth/5,jfHeight/11);
	        oldlabel.setLocation(jfWidth/7,jfHeight*39/100);
			JPasswordField oldfield=new JPasswordField();
			oldfield.setSize(jfWidth*45/100,jfHeight/10);
	        oldfield.setLocation(jfWidth*2/7,jfHeight*39/100);
			oldfield.addKeyListener(new oldkeyListener());
			//
			JLabel newlabel=new JLabel("新密码：");
			newlabel.setFont(new Font("用户名", Font.PLAIN, 12));
			newlabel.setSize(jfWidth/5,jfHeight/11);
	        newlabel.setLocation(jfWidth/7,jfHeight*55/100);
			JPasswordField newfield=new JPasswordField();
			newfield.setSize(jfWidth*45/100,jfHeight/10);
	        newfield.setLocation(jfWidth*2/7,jfHeight*55/100);
			newfield.addKeyListener(new newkeyListener());
			//
			JButton button=new JButton("确认修改");
			button.setFont(new Font("帮 助", Font.PLAIN, 13));
			button.setSize(jfWidth/5,jfHeight/10);
	        button.setLocation(jfWidth*39/100,jfHeight*75/100);
	        button.addActionListener(new buttonListener());
			//
			panel.add(label);
			panel.add(oldlabel);
			panel.add(oldfield);
			panel.add(newlabel);
			panel.add(newfield);
			panel.add(button);
			ckf.repaint();
			
			ckf.setResizable(false);
			ckf.setVisible(true);
			
		}
		class oldkeyListener implements KeyListener{

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				JPasswordField jpf=(JPasswordField) e.getSource();
				char[]p=jpf.getPassword();
				UserPanel.this.oldkey=new String(p);	
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		class newkeyListener implements KeyListener{
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				JPasswordField jpf=(JPasswordField) e.getSource();
				char[]p=jpf.getPassword();
				UserPanel.this.newkey=new String(p);
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		class buttonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(user.password.equals(oldkey)){
					String filename="File/Users/"+user.username+".ser";
					user.password=new String(newkey);
					try {
						IOhelper.UPD(filename, user, filename);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "修改成功！", "修改成功", JOptionPane.INFORMATION_MESSAGE);
					ckf.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "原密码不正确！", "修改失败", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
