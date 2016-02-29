package Mgr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.Customer;

public class MgrCust extends StandardMgr {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable custInfo;
	JScrollPane jsp;
	String[][] content;
	String[] head = { "姓名", "联系方式", "应收", "应付", "合计" };

	public MgrCust() {
		JButton editButton = new JButton("客户操作", new ImageIcon(
				"GUI/icons/upd.png"));
		editButton.setFocusPainted(false);
		editButton.setSize(jfWidth / 5, jfHeight / 10);
		editButton.setLocation(jfWidth * 5 / 100, jfHeight * 70 / 100);
		editButton.setFont(new Font("宋体", Font.PLAIN, 14));
		editButton.setHorizontalAlignment(SwingConstants.LEFT);
		editButton.addActionListener(new editButtonListener());
		this.add(editButton);
		//
		JButton searchButton = new JButton("查找客户", new ImageIcon(
				"GUI/icons/search.png"));
		searchButton.setFocusPainted(false);
		searchButton.setSize(jfWidth / 5, jfHeight / 10);
		searchButton.setLocation(jfWidth * 30 / 100, jfHeight * 70 / 100);
		searchButton.setFont(new Font("宋体", Font.PLAIN, 14));
		searchButton.setHorizontalAlignment(SwingConstants.LEFT);
		searchButton.addActionListener(new searchButtonListener());
		this.add(searchButton);
		//
		JButton refreshButton = new JButton("更新信息", new ImageIcon(
				"GUI/icons/refresh.png"));
		refreshButton.setFocusPainted(false);
		refreshButton.setSize(jfWidth / 5, jfHeight / 10);
		refreshButton.setLocation(jfWidth * 55 / 100, jfHeight * 70 / 100);
		refreshButton.setFont(new Font("宋体", Font.PLAIN, 14));
		refreshButton.setHorizontalAlignment(SwingConstants.LEFT);
		refreshButton.addActionListener(new refreshButtonListener());
		this.add(refreshButton);
		//
		try {
			content = Customer.customerSho();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		custInfo = new JTable(content, head);
		custInfo.setVisible(true);
		jsp = new JScrollPane();
		jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
		jsp.setLocation(jfWidth / 32, jfHeight / 20);
		jsp.setViewportView(custInfo);
		this.add(jsp);
		//
	}

	class editButtonListener implements ActionListener {
		JFrame editFrame;
		JComboBox jcb,delnamebox,updnamebox;
		JPanel adp, dep, upp;
		JTextField adnameField, adcontactField;
		JTextField upcontactField;
		String addname, addcontact, delname, updname, updcontact;
		JButton addyesb, addexitb, delyesb, delexitb, updyesb, updexitb;
		int ffHeight, ffWidth;

		@Override
		public void actionPerformed(ActionEvent e) {
			editFrame = new JFrame();
			editFrame.setLayout(null);
			editFrame.setTitle("客户操作");
			//
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			ffHeight = 548;
			ffWidth = 455;
			// 获得了与分辨率匹配的大小
			editFrame.setSize(ffWidth, ffHeight);
			editFrame.setLocation((screenWidth - ffWidth) / 2,
					(screenHeight - ffHeight) / 2);
			//
			JLabel jcbl = new JLabel("操作项目：");
			jcbl.setFont(new Font("宋体", Font.TRUETYPE_FONT, 15));
			jcbl.setSize(ffWidth / 5, ffHeight / 20);
			jcbl.setLocation(ffWidth * 10 / 100, ffHeight * 3 / 100);
			editFrame.add(jcbl);
			//
			String[] func = { "请选择操作项目", "增加客户", "删除客户", "修改客户信息" };
			jcb = new JComboBox(func);
			jcb.setSize(ffWidth / 2, ffHeight / 20);
			jcb.setLocation(ffWidth * 38 / 100, ffHeight * 3 / 100);
			jcb.setBackground(Color.white);
			jcb.setFont(new Font("宋体", Font.TRUETYPE_FONT, 15));
			jcb.setFocusable(false);
			jcb.addActionListener(new jcbListener());
			editFrame.add(jcb);
			// //
			adp = new JPanel();
			adp.setLayout(null);
			adp.setSize(ffWidth, ffHeight * 19 / 20);
			adp.setLocation(0, ffHeight * 3 / 100 + ffHeight / 20);
			//
			JLabel adnamel = new JLabel("客户名：");
			adnamel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			adnamel.setSize(ffWidth / 5, ffHeight / 20);
			adnamel.setLocation(ffWidth * 5 / 100, ffHeight * 6 / 100);
			adp.add(adnamel);
			//
			adnameField = new JTextField();
			adnameField.setSize(ffWidth / 2, ffHeight / 20);
			adnameField.setLocation(ffWidth * 20 / 100, ffHeight * 6 / 100);
			adnameField.getDocument().addDocumentListener(
					new adnameFieldListener());
			adp.add(adnameField);
			//
			JLabel adcontactl = new JLabel("联系方式：");
			adcontactl.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			adcontactl.setSize(ffWidth / 5, ffHeight / 20);
			adcontactl.setLocation(ffWidth * 5 / 100, ffHeight * 15 / 100);
			adp.add(adcontactl);
			//
			adcontactField = new JTextField();
			adcontactField.setSize(ffWidth / 2, ffHeight / 20);
			adcontactField.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
			adcontactField.getDocument().addDocumentListener(
					new adcontactFieldListener());
			adp.add(adcontactField);
			//
			addyesb = new JButton("添加");
			addyesb.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			addyesb.setSize(ffWidth * 3 / 10, ffHeight / 20);
			addyesb.setLocation(ffWidth * 10 / 100, ffHeight * 65 / 100);
			addyesb.addActionListener(new addyesbListener());
			adp.add(addyesb);
			//
			addexitb = new JButton("取消");
			addexitb.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			addexitb.setSize(ffWidth * 3 / 10, ffHeight / 20);
			addexitb.setLocation(ffWidth * 55 / 100, ffHeight * 65 / 100);
			addexitb.addActionListener(new exitbListener());
			adp.add(addexitb);
			//
			adp.setVisible(false);
			editFrame.add(adp);
			// //
			dep = new JPanel();
			dep.setLayout(null);
			dep.setSize(ffWidth, ffHeight * 19 / 20);
			dep.setLocation(0, ffHeight * 3 / 100 + ffHeight / 20);
			//
			JLabel denamel = new JLabel("客户名：");
			denamel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			denamel.setSize(ffWidth / 5, ffHeight / 20);
			denamel.setLocation(ffWidth * 15/ 100, ffHeight * 20 / 100);
			dep.add(denamel);
			//
			delnamebox = new JComboBox(Customer.getCustNames());
			delnamebox.setSize(ffWidth / 2, ffHeight / 20);
			delnamebox.setLocation(ffWidth * 35 / 100, ffHeight *20/ 100);
			delnamebox.addActionListener(new delnameboxListener());
			dep.add(delnamebox);
			//
			delyesb = new JButton("删除");
			delyesb.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			delyesb.setSize(ffWidth * 3 / 10, ffHeight / 20);
			delyesb.setLocation(ffWidth * 10 / 100, ffHeight * 65 / 100);
			delyesb.addActionListener(new delyesbListener());
			dep.add(delyesb);
			//
			delexitb = new JButton("取消");
			delexitb.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			delexitb.setSize(ffWidth * 3 / 10, ffHeight / 20);
			delexitb.setLocation(ffWidth * 55 / 100, ffHeight * 65 / 100);
			delexitb.addActionListener(new exitbListener());
			dep.add(delexitb);
			//
			dep.setVisible(false);
			editFrame.add(dep);
			// //
			upp = new JPanel();
			upp.setLayout(null);
			upp.setSize(ffWidth, ffHeight * 19 / 20);
			upp.setLocation(0, ffHeight * 3 / 100 + ffHeight / 20);
			//
			JLabel upnamel = new JLabel("客户名：");
			upnamel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			upnamel.setSize(ffWidth / 5, ffHeight / 20);
			upnamel.setLocation(ffWidth * 5 / 100, ffHeight * 6 / 100);
			upp.add(upnamel);
			//
			updnamebox = new JComboBox(Customer.getCustNames());
			updnamebox.setSize(ffWidth / 2, ffHeight / 20);
			updnamebox.setLocation(ffWidth * 20 / 100, ffHeight * 6 / 100);
			updnamebox.addActionListener(new updnameboxListener());
			upp.add(updnamebox);
			//
			JLabel upcontactl = new JLabel("联系方式：");
			upcontactl.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			upcontactl.setSize(ffWidth / 5, ffHeight / 20);
			upcontactl.setLocation(ffWidth * 5 / 100, ffHeight * 15 / 100);
			upp.add(upcontactl);
			//
			upcontactField = new JTextField();
			upcontactField.setSize(ffWidth / 2, ffHeight / 20);
			upcontactField.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
			upcontactField.getDocument().addDocumentListener(
					new upcontactFieldListener());
			upp.add(upcontactField);
			//
			updyesb = new JButton("修改");
			updyesb.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			updyesb.setSize(ffWidth * 3 / 10, ffHeight / 20);
			updyesb.setLocation(ffWidth * 10 / 100, ffHeight * 65 / 100);
			updyesb.addActionListener(new updyesbListener());
			upp.add(updyesb);
			//
			updexitb = new JButton("取消");
			updexitb.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			updexitb.setSize(ffWidth * 3 / 10, ffHeight / 20);
			updexitb.setLocation(ffWidth * 55 / 100, ffHeight * 65 / 100);
			updexitb.addActionListener(new exitbListener());
			upp.add(updexitb);
			//
			upp.setVisible(false);
			editFrame.add(upp);
			// //
			editFrame.repaint();
			editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			editFrame.setVisible(true);
		}

		class jcbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox) e.getSource();
				switch (temp.getSelectedItem().toString()) {
				case "增加客户": {
					adp.setVisible(true);
					dep.setVisible(false);
					upp.setVisible(false);
				}
					break;
				case "删除客户": {
					adp.setVisible(false);
					dep.setVisible(true);
					upp.setVisible(false);
				}
					break;
				case "修改客户信息": {
					adp.setVisible(false);
					dep.setVisible(false);
					upp.setVisible(true);
				}
					break;
				default: {
					adp.setVisible(false);
					dep.setVisible(false);
					upp.setVisible(false);
				}
				}

			}

		}

		class adnameFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				addname = adnameField.getText();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				addname = adnameField.getText();

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				addname = adnameField.getText();
			}

		}

		class adcontactFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				addcontact = adcontactField.getText();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				addcontact = adcontactField.getText();

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				addcontact = adcontactField.getText();

			}

		}

		class addyesbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("File/Customer/" + addname + ".ser");
				if (!file.exists()) {
					try {
						Customer.customerAdd(addname, addcontact);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "客户添加成功！", "添加成功",
							JOptionPane.INFORMATION_MESSAGE);
					editFrame.dispose();
					MgrCust.this.remove(jsp);
					try {
						content = Customer.customerSho();
					} catch (ClassNotFoundException | IOException ef) {
						// TODO Auto-generated catch block
						ef.printStackTrace();
					}
					custInfo = new JTable(content, head);
					custInfo.setVisible(true);
					jsp = new JScrollPane();
					jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
					jsp.setLocation(jfWidth / 32, jfHeight / 20);
					jsp.setViewportView(custInfo);
					MgrCust.this.add(jsp);
				} else {
					JOptionPane.showMessageDialog(null, "添加失败，客户已经存在！", "添加失败",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}

		class exitbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				editFrame.dispose();
			}

		}

		class delnameboxListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				delname=delnamebox.getSelectedItem().toString();
				
			}
			
		}
		class delyesbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("File/Customer/" + delname + ".ser");
				if (file.exists()) {
					String result = null;
					result = Customer.customerDel(delname);
					switch (result) {
					case "IMPORTED": {
						JOptionPane.showMessageDialog(null,
								"删除失败，客户已经与公司发生关系！", "删除失败",
								JOptionPane.ERROR_MESSAGE);
					}
						break;
					case "OK": {
						JOptionPane.showMessageDialog(null, "客户删除成功！", "删除成功",
								JOptionPane.INFORMATION_MESSAGE);
						editFrame.dispose();
						MgrCust.this.remove(jsp);
						try {
							content = Customer.customerSho();
						} catch (ClassNotFoundException | IOException ef) {
							// TODO Auto-generated catch block
							ef.printStackTrace();
						}
						custInfo = new JTable(content, head);
						custInfo.setVisible(true);
						jsp = new JScrollPane();
						jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
						jsp.setLocation(jfWidth / 32, jfHeight / 20);
						jsp.setViewportView(custInfo);
						MgrCust.this.add(jsp);
					}
						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "删除失败，客户不存在！", "删除失败",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}

		class updnameboxListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				updname=updnamebox.getSelectedItem().toString();
				
			}


		}

		class upcontactFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updcontact = upcontactField.getText();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updcontact = upcontactField.getText();

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updcontact = upcontactField.getText();
			}

		}

		class updyesbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("File/Customer/" + updname + ".ser");
				if (file.exists()) {
					try {
						Customer.customerUpd(updname, updcontact);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "客户信息修改成功！", "修改成功",
							JOptionPane.INFORMATION_MESSAGE);
					editFrame.dispose();
					MgrCust.this.remove(jsp);
					try {
						content = Customer.customerSho();
					} catch (ClassNotFoundException | IOException ef) {
						// TODO Auto-generated catch block
						ef.printStackTrace();
					}
					custInfo = new JTable(content, head);
					custInfo.setVisible(true);
					jsp = new JScrollPane();
					jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
					jsp.setLocation(jfWidth / 32, jfHeight / 20);
					jsp.setViewportView(custInfo);
					MgrCust.this.add(jsp);
				} else {
					JOptionPane.showMessageDialog(null, "修改失败，客户不存在！", "修改失败",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}

	class searchButtonListener implements ActionListener {
		SearchMgr sFrame;
		JTextField snameField;
		String sname = "";
		String[][] resultText = new String[1][5];
		JButton sebutton;

		@Override
		public void actionPerformed(ActionEvent e) {
			sFrame = new SearchMgr();
			sFrame.setTitle("查找客户");
			//
			JLabel snamel = new JLabel("客户名：");
			snamel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			snamel.setSize(sFrame.seWidth / 8, sFrame.seHeight / 20);
			snamel.setLocation(sFrame.seWidth * 30 / 100,
					sFrame.seHeight * 5 / 100);
			sFrame.add(snamel);
			//
			snameField = new JTextField();
			snameField.setSize(sFrame.seWidth / 3, sFrame.seHeight / 20);
			snameField.setLocation(sFrame.seWidth * 41/ 100,
					sFrame.seHeight * 5 / 100);
			snameField.getDocument().addDocumentListener(
					new snameFieldListener());
			sFrame.add(snameField);
			//
			sebutton = new JButton("查找");
			sebutton.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			sebutton.setSize(sFrame.seWidth / 3, sFrame.seHeight / 20);
			sebutton.setLocation(sFrame.seWidth * 35 / 100,
					sFrame.seHeight * 80 / 100);
			sebutton.addActionListener(new sebuttonListener());
			sFrame.add(sebutton);
		}

		class snameFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				sname = snameField.getText();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				sname = snameField.getText();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				sname = snameField.getText();
			}

		}

		class sebuttonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					resultText = Customer.customerFin(sname);
				} catch (ClassNotFoundException | IOException ef) {
					// TODO Auto-generated catch block
					ef.printStackTrace();
				}
				sFrame.searchResult = new JTable(resultText, head);
				sFrame.searchResult.setVisible(true);
				sFrame.srjsp = new JScrollPane();
				sFrame.srjsp.setSize(sFrame.seWidth * 4 / 5,
						sFrame.seHeight / 2);
				sFrame.srjsp.setLocation(sFrame.seWidth * 10 / 100,
						sFrame.seHeight * 20 / 100);
				sFrame.srjsp.setViewportView(sFrame.searchResult);
				sFrame.add(sFrame.srjsp);
				//
			}
		}
	}
	class refreshButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MgrCust.this.remove(jsp);
			try {
				content = Customer.customerSho();
			} catch (ClassNotFoundException | IOException ef) {
				// TODO Auto-generated catch block
				ef.printStackTrace();
			}
			custInfo = new JTable(content, head);
			custInfo.setVisible(true);
			jsp = new JScrollPane();
			jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
			jsp.setLocation(jfWidth / 32, jfHeight / 20);
			jsp.setViewportView(custInfo);
			MgrCust.this.add(jsp);
		}

}
}

