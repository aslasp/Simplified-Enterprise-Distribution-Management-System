package Mgr;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.Role;
import program.Users;

public class MgrUser extends StandardMgr {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable userInfo;
	JScrollPane jsp;
	String[] head = { "用户名", "用户类型" };
	public MgrUser() throws ClassNotFoundException, IOException {
		JButton addUserButton = new JButton("添加用户", new ImageIcon(
				"GUI/icons/add.png"));
		addUserButton.setFocusPainted(false);
		addUserButton.setSize(jfWidth / 5, jfHeight / 10);
		addUserButton.setLocation(jfWidth * 5 / 100, jfHeight * 70 / 100);
		addUserButton.setFont(new Font("宋体", Font.PLAIN, 14));
		addUserButton.setHorizontalAlignment(SwingConstants.LEFT);
		addUserButton.addActionListener(new addUserButtonListener());
		//
		JButton delUserButton = new JButton("删除用户", new ImageIcon(
				"GUI/icons/del.png"));
		delUserButton.setFocusPainted(false);
		delUserButton.setSize(jfWidth / 5, jfHeight / 10);
		delUserButton.setLocation(jfWidth * 30 / 100, jfHeight * 70 / 100);
		delUserButton.setFont(new Font("宋体", Font.PLAIN, 14));
		delUserButton.setHorizontalAlignment(SwingConstants.LEFT);
		delUserButton.addActionListener(new delUserButtonListener());
		//
		JButton refreshButton = new JButton("更新信息", new ImageIcon(
				"GUI/icons/refresh.png"));
		refreshButton.setFocusPainted(false);
		refreshButton.setSize(jfWidth / 5, jfHeight / 10);
		refreshButton.setLocation(jfWidth * 55 / 100, jfHeight * 70 / 100);
		refreshButton.setFont(new Font("宋体", Font.PLAIN, 14));
		refreshButton.setHorizontalAlignment(SwingConstants.LEFT);
		refreshButton.addActionListener(new refreshButtonListener());
		//
		String[][] content = null;
		try {
			content = Users.getUserInfo();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userInfo = new JTable(content, head);
		userInfo.setVisible(true);
		
		jsp = new JScrollPane();
		jsp.setSize(jfWidth * 3 / 5, jfHeight * 2 / 5);
		jsp.setLocation(jfWidth / 10, jfHeight / 8);
		jsp.setViewportView(userInfo);
		this.add(jsp);
		this.add(addUserButton);
		this.add(delUserButton);
		this.add(refreshButton);
	}

	class addUserButtonListener implements ActionListener, Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JFrame auFrame;
		JComboBox rolebox;
		JPasswordField passwordfield;
		JTextField namefield;
		String newname, newkey;
		Role newrole;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			auFrame = new JFrame();
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			int jfHeight = 307;
			int jfWidth = 455;
			// 获得了与分辨率匹配的大小
			auFrame.setSize(jfWidth, jfHeight);
			auFrame.setLocation((screenWidth - jfWidth) / 2,
					(screenHeight - jfHeight) / 2);
			// 设置好了窗口大小，位置
			auFrame.setTitle("添加用户");
			auFrame.setLayout(null);
			//
			JLabel namelabel = new JLabel("用户名：");
			namelabel.setSize(jfWidth / 5, jfHeight / 9);
			namelabel.setLocation(jfWidth / 10, jfHeight / 9);
			namelabel.setFont(new Font("用户名", Font.PLAIN, 14));
			auFrame.add(namelabel);
			//
			namefield = new JTextField();
			namefield.setSize(jfWidth / 2, jfHeight / 10);
			namefield.setLocation(jfWidth * 3 / 10, jfHeight / 9);
			namefield.getDocument().addDocumentListener(new nListener());
			auFrame.add(namefield);
			//
			JLabel passwordlabel = new JLabel("密码：");
			passwordlabel.setSize(jfWidth / 5, jfHeight / 9);
			passwordlabel.setLocation(jfWidth / 10, jfHeight * 3 / 9);
			passwordlabel.setFont(new Font("用户名", Font.PLAIN, 14));
			auFrame.add(passwordlabel);
			//
			passwordfield = new JPasswordField();
			passwordfield.setSize(jfWidth / 2, jfHeight / 10);
			passwordfield.setLocation(jfWidth * 3 / 10, jfHeight * 3 / 9);
			passwordfield.addKeyListener(new pListener());
			auFrame.add(passwordfield);
			//
			String[] rolelist = { "请选择用户类型", "管理员", "销售人员", "财务人员", "仓库专员" };
			rolebox = new JComboBox(rolelist);
			rolebox.setFont(new Font("用户名", Font.PLAIN, 13));
			rolebox.setSize(jfWidth / 2, jfHeight / 11);
			rolebox.setLocation(jfWidth * 3 / 11, jfHeight * 7 / 14);
			rolebox.addActionListener(new roleboxListener());
			auFrame.add(rolebox);
			//
			JButton sureButton = new JButton("确认");
			sureButton.setFont(new Font("用户名", Font.PLAIN, 13));
			sureButton.setSize(jfWidth / 6, jfHeight / 10);
			sureButton.setLocation(jfWidth * 1 / 10, jfHeight * 7 / 10);
			sureButton.addActionListener(new sureListener());
			auFrame.add(sureButton);
			//
			JButton exitButton = new JButton("取消");
			exitButton.setFont(new Font("用户", Font.PLAIN, 13));
			exitButton.setSize(jfWidth / 6, jfHeight / 10);
			exitButton.setLocation(jfWidth * 6 / 10, jfHeight * 7 / 10);
			exitButton.addActionListener(new exitListener());
			auFrame.add(exitButton);
			//
			auFrame.repaint();
			auFrame.setVisible(true);
		}

		class nListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent e) {
				newname = new String(namefield.getText());

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				newname = new String(namefield.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				newname = new String(namefield.getText());
			}

		}

		class pListener implements KeyListener {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				char[] p;
				JPasswordField jpf = (JPasswordField) e.getSource();
				p = jpf.getPassword();
				newkey = new String(p);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		}

		class roleboxListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				switch (jcb.getSelectedItem().toString()) {
				case "管理员":
					newrole = Role.Administrator;
					break;
				case "销售人员":
					newrole = Role.Salesman;
					break;
				case "财务人员":
					newrole = Role.Accountant;
					break;
				case "仓库专员":
					newrole = Role.Storekeeper;
					break;
				default:
					;
				}

			}

		}

		class sureListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Users.addUser(newname, newkey, newrole);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "添加成功！", "添加成功",
						JOptionPane.INFORMATION_MESSAGE);
				MgrUser.this.remove(jsp);
				try {
					userInfo=new JTable(Users.getUserInfo(),head);
				} catch (ClassNotFoundException | IOException de) {
					// TODO Auto-generated catch block
					de.printStackTrace();
				}
				userInfo.repaint();
				jsp = new JScrollPane();
				jsp.setSize(jfWidth * 3 / 5, jfHeight * 2 / 5);
				jsp.setLocation(jfWidth / 10, jfHeight / 8);
				jsp.setViewportView(userInfo);
				MgrUser.this.add(jsp);
				auFrame.dispose();
				
			}

		}

		class exitListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				auFrame.dispose();

			}

		}
	}

	class delUserButtonListener implements ActionListener {
		String delname;
		int selectedRowNum;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			selectedRowNum=userInfo.getSelectedRow();
			if(selectedRowNum!=-1){
				Users.delUser((String)userInfo.getValueAt(selectedRowNum, 0));
				JOptionPane.showMessageDialog(null, "删除成功！", "删除成功",
						JOptionPane.INFORMATION_MESSAGE);
				MgrUser.this.remove(jsp);
				try {
					userInfo=new JTable(Users.getUserInfo(),head);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userInfo.repaint();
				jsp = new JScrollPane();
				jsp.setSize(jfWidth * 3 / 5, jfHeight * 2 / 5);
				jsp.setLocation(jfWidth / 10, jfHeight / 8);
				jsp.setViewportView(userInfo);
				MgrUser.this.add(jsp);
			}
			else{
				JOptionPane.showMessageDialog(null, "你还没选择客户呢", "错误",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class refreshButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MgrUser.this.remove(jsp);
			try {
				userInfo=new JTable(Users.getUserInfo(),head);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userInfo.repaint();
			jsp = new JScrollPane();
			jsp.setSize(jfWidth * 3 / 5, jfHeight * 2 / 5);
			jsp.setLocation(jfWidth / 10, jfHeight / 8);
			jsp.setViewportView(userInfo);
			MgrUser.this.add(jsp);
		}

	}
}
