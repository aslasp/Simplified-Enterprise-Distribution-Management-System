package Mgr;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.Account;

public class MgrAcc extends StandardMgr {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable accDet,accAll;
	JScrollPane jsp,jsAll;
	String[] head = { "日期", "类型", "客户", "金额" };
	String[] headAll={"账目总额","公司应收","公司应付"};
	public MgrAcc() throws ClassNotFoundException, IOException {
		JButton addButton = new JButton("创建单据", new ImageIcon(
				"GUI/icons/add.png"));
		addButton.setFocusPainted(false);
		addButton.setSize(jfWidth / 5, jfHeight / 10);
		addButton.setLocation(jfWidth * 5 / 100, jfHeight * 70 / 100);
		addButton.setFont(new Font("宋体", Font.PLAIN, 14));
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new addButtonListener());
		//
		JButton iniButton = new JButton("初始化", new ImageIcon(
				"GUI/icons/initial.png"));
		iniButton.setFocusPainted(false);
		iniButton.setSize(jfWidth / 5, jfHeight / 10);
		iniButton.setLocation(jfWidth * 30 / 100, jfHeight * 70 / 100);
		iniButton.setFont(new Font("宋体", Font.PLAIN, 14));
		iniButton.setHorizontalAlignment(SwingConstants.LEFT);
		iniButton.addActionListener(new iniButtonListener());
		//
		JButton refreshButton = new JButton("更新账目", new ImageIcon(
				"GUI/icons/refresh.png"));
		refreshButton.setFocusPainted(false);
		refreshButton.setSize(jfWidth / 5, jfHeight / 10);
		refreshButton.setLocation(jfWidth * 55 / 100, jfHeight * 70 / 100);
		refreshButton.setFont(new Font("宋体", Font.PLAIN, 14));
		refreshButton.setHorizontalAlignment(SwingConstants.LEFT);
		refreshButton.addActionListener(new refreshButtonListener());
		//
		accAll=new JTable(Account.accountAll(), headAll);
		jsAll = new JScrollPane();
		jsAll.setSize(jfWidth * 3 / 5, jfHeight /13);
		jsAll.setLocation(jfWidth /10, jfHeight /40);
		jsAll.setViewportView(accAll);
		MgrAcc.this.add(jsAll);
		//
		accDet=new JTable(Account.accountDet(), head);
		jsp = new JScrollPane();
		jsp.setSize(jfWidth * 3 / 5, jfHeight *55/100);
		jsp.setLocation(jfWidth / 10, jfHeight*10 / 100);
		jsp.setViewportView(accDet);
		MgrAcc.this.add(jsp);
		//
		this.add(addButton);
		this.add(iniButton);
		this.add(refreshButton);
	}

	class addButtonListener implements ActionListener {
		JFrame addF;
		JRadioButton accin, accout;
		JTextField namet;
		JTextField moneyt;
		JButton yesb, exitb;
		String name;
		String addtype = "";
		int money;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			addF = new JFrame();
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			int jfHeight = 384;
			int jfWidth = 341;
			// 获得了与分辨率匹配的大小
			addF.setSize(jfWidth, jfHeight);
			addF.setLocation((screenWidth - jfWidth) / 2,
					(screenHeight - jfHeight) / 2);
			// 设置好了窗口大小，位置
			addF.setTitle("创建单据");
			addF.setLayout(null);
			//
			JLabel typel = new JLabel("单据类型:");
			typel.setFont(new Font("宋体", Font.PLAIN, 14));
			typel.setSize(jfWidth / 4, jfHeight / 12);
			typel.setLocation(jfWidth * 10 / 100, jfHeight * 10 / 100);
			addF.add(typel);
			//
			accin = new JRadioButton("收款单", false);
			accin.setFont(new Font("宋体", Font.PLAIN, 14));
			accin.setSize(jfWidth / 5, jfHeight / 12);
			accin.setLocation(jfWidth * 35 / 100, jfHeight * 10 / 100);
			accin.addActionListener(new accinListener());
			addF.add(accin);
			//
			accout = new JRadioButton("付款单", false);
			accout.setFont(new Font("宋体", Font.PLAIN, 14));
			accout.setSize(jfWidth / 5, jfHeight / 12);
			accout.setLocation(jfWidth * 60 / 100, jfHeight * 10 / 100);
			accout.addActionListener(new accoutListener());
			addF.add(accout);
			//
			ButtonGroup type = new ButtonGroup();
			type.add(accin);
			type.add(accout);
			//
			JLabel namel = new JLabel("客户姓名:");
			namel.setFont(new Font("宋体", Font.PLAIN, 14));
			namel.setSize(jfWidth / 4, jfHeight / 12);
			namel.setLocation(jfWidth * 10 / 100, jfHeight * 30 / 100);
			addF.add(namel);
			//
			namet = new JTextField();
			namet.setSize(jfWidth / 2, jfHeight / 12);
			namet.setLocation(jfWidth * 30 / 100, jfHeight * 30 / 100);
			namet.getDocument().addDocumentListener(new nListener());
			addF.add(namet);
			//
			JLabel moneyl = new JLabel("金额:");
			moneyl.setFont(new Font("宋体", Font.PLAIN, 14));
			moneyl.setSize(jfWidth / 4, jfHeight / 12);
			moneyl.setLocation(jfWidth * 10 / 100, jfHeight * 50 / 100);
			addF.add(moneyl);
			//
			moneyt = new JTextField();
			moneyt.setSize(jfWidth / 2, jfHeight / 12);
			moneyt.setLocation(jfWidth * 30 / 100, jfHeight * 50 / 100);
			moneyt.getDocument().addDocumentListener(new mListener());
			addF.add(moneyt);
			//
			yesb = new JButton("创建");
			yesb.setFont(new Font("宋体", Font.PLAIN, 14));
			yesb.setSize(jfWidth / 3, jfHeight / 12);
			yesb.setLocation(jfWidth * 12 / 100, jfHeight * 70 / 100);
			yesb.addActionListener(new yesListener());
			addF.add(yesb);
			//
			exitb = new JButton("取消");
			exitb.setFont(new Font("宋体", Font.PLAIN, 14));
			exitb.setSize(jfWidth / 3, jfHeight / 12);
			exitb.setLocation(jfWidth * 50 / 100, jfHeight * 70 / 100);
			exitb.addActionListener(new exitListener());
			addF.add(exitb);
			//
			addF.repaint();
			addF.setVisible(true);
		}

		class accinListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton in = (JRadioButton) e.getSource();
				if (in.isSelected())
					addtype = new String("in");

			}

		}

		class accoutListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton out = (JRadioButton) e.getSource();
				if (out.isSelected())
					addtype = new String("out");

			}

		}

		class nListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent e) {
				name = new String(namet.getText());

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				name = new String(namet.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				name = new String(namet.getText());
			}

		}

		class mListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent e) {
				money = Integer.parseInt(new String(moneyt.getText()));
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				money = Integer.parseInt(new String(moneyt.getText()));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				money = Integer.parseInt(new String(moneyt.getText()));
			}

		}

		class yesListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("File/Customer/" + name + ".ser");
				if (file.exists()) {
					switch (addtype) {
					case "in": {
						try {
							Account.accountIn(name, money);
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "收款单添加成功", "添加成功",
								JOptionPane.INFORMATION_MESSAGE);
						addF.dispose();
					}
						break;
					case "out": {
						try {
							Account.accountOut(name, money);
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "付款单添加成功", "添加成功",
								JOptionPane.INFORMATION_MESSAGE);
						addF.dispose();
					}
						break;
					default:
						JOptionPane.showMessageDialog(null, "未选择单据类型", "错误",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "客户不存在！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}

		class exitListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				addF.dispose();

			}

		}
	}

	class iniButtonListener implements ActionListener {
		JFrame sureFrame;
		JButton bt;

		@Override
		public void actionPerformed(ActionEvent e) {
			sureFrame = new JFrame();
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			int jfHeight = 153;
			int jfWidth = 341;
			// 获得了与分辨率匹配的大小
			sureFrame.setSize(jfWidth, jfHeight);
			sureFrame.setLocation((screenWidth - jfWidth) / 2,
					(screenHeight - jfHeight) / 2);
			// 设置好了窗口大小，位置
			sureFrame.setTitle("确认初始化");
			sureFrame.setLayout(null);
			//
			JLabel text = new JLabel("确定要初始化账目？");
			text.setFont(new Font("宋体", Font.PLAIN, 13));
			text.setSize(jfWidth / 2, jfHeight / 4);
			text.setLocation(jfWidth / 4, jfHeight / 20);
			//
			bt = new JButton("我确定要初始化");
			bt.setFont(new Font("宋体", Font.PLAIN, 13));
			bt.setSize(jfWidth / 2, jfHeight / 6);
			bt.setLocation(jfWidth * 2 / 10, jfHeight * 10 / 20);
			bt.setFocusPainted(false);
			bt.addActionListener(new btListener());
			//
			sureFrame.add(text);
			sureFrame.add(bt);
			sureFrame.setVisible(true);
		}

		class btListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Account.accountIni();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "账目初始化成功", "初始化成功",
						JOptionPane.INFORMATION_MESSAGE);
				sureFrame.dispose();
			}

		}
	}

	class refreshButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MgrAcc.this.remove(jsAll);
			MgrAcc.this.remove(jsp);
			try {
				accAll=new JTable(Account.accountAll(), headAll);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jsAll = new JScrollPane();
			jsAll.setSize(jfWidth * 3 / 5, jfHeight /13);
			jsAll.setLocation(jfWidth /10, jfHeight /40);
			jsAll.setViewportView(accAll);
			MgrAcc.this.add(jsAll);
			//
			try {
				accDet=new JTable(Account.accountDet(), head);
				
			} catch (IOException f) {
				// TODO Auto-generated catch block
				f.printStackTrace();
			}
			jsp = new JScrollPane();
			jsp.setSize(jfWidth * 3 / 5, jfHeight * 55 /100);
			jsp.setLocation(jfWidth / 10, jfHeight*10/100);
			jsp.setViewportView(accDet);
			MgrAcc.this.add(jsp);
		}

	}
}
