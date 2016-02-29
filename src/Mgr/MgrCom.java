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

import program.Commodity;

public class MgrCom extends StandardMgr {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable comInfo;
	JScrollPane jsp;
	String[][] content;
	String[] head = { "商品名称", "型号", "数量", "默认进价", "默认售价", "最近一次进价", "最近一次售价" };

	public MgrCom() {
		JButton editButton = new JButton("商品操作", new ImageIcon(
				"GUI/icons/upd.png"));
		editButton.setFocusPainted(false);
		editButton.setSize(jfWidth / 5, jfHeight / 10);
		editButton.setLocation(jfWidth * 5 / 100, jfHeight * 70 / 100);
		editButton.setFont(new Font("宋体", Font.PLAIN, 14));
		editButton.setHorizontalAlignment(SwingConstants.LEFT);
		editButton.addActionListener(new editButtonListener());
		this.add(editButton);
		//
		JButton searchButton = new JButton("查找商品", new ImageIcon(
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
			content = Commodity.commoditySho();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comInfo = new JTable(content, head);
		comInfo.setVisible(true);
		jsp = new JScrollPane();
		jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
		jsp.setLocation(jfWidth / 32, jfHeight / 20);
		jsp.setViewportView(comInfo);
		this.add(jsp);
		//
	}

	class editButtonListener implements ActionListener {
		JFrame editFrame;
		JComboBox jcb,delnamebox,delmodelbox,updnamebox,updmodelbox;
		JPanel adp, dep, upp;
		JTextField adnameField, admodelField, adImpPriceField, adExpPriceField;
		JTextField  updImpPriceField,updExpPriceField;
		String addname, addmodel, delname, delmodel, updname, updmodel;
		int addImpPrice, addExpPrice, updImpPrice, updExpPrice;
		JButton addyesb, addexitb, delyesb, delexitb, updyesb, updexitb;
		int ffHeight, ffWidth;
		String[] modelboxC;
		@Override
		public void actionPerformed(ActionEvent e) {
			editFrame = new JFrame();
			editFrame.setLayout(null);
			editFrame.setTitle("商品操作");
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
			String[] func = { "请选择操作项目", "增加商品", "删除商品", "修改商品信息" };
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
			JLabel adnamel = new JLabel("商品名：");
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
			JLabel admodell = new JLabel("型号：");
			admodell.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			admodell.setSize(ffWidth / 5, ffHeight / 20);
			admodell.setLocation(ffWidth * 5 / 100, ffHeight * 15 / 100);
			adp.add(admodell);
			//
			admodelField = new JTextField();
			admodelField.setSize(ffWidth / 2, ffHeight / 20);
			admodelField.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
			admodelField.getDocument().addDocumentListener(
					new admodelFieldListener());
			adp.add(admodelField);
			//
			JLabel adimpPricel = new JLabel("默认进价：");
			adimpPricel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			adimpPricel.setSize(ffWidth / 5, ffHeight / 20);
			adimpPricel.setLocation(ffWidth * 5 / 100, ffHeight * 30 / 100);
			adp.add(adimpPricel);
			//
			adImpPriceField = new JTextField();
			adImpPriceField.setSize(ffWidth / 3, ffHeight / 20);
			adImpPriceField
					.setLocation(ffWidth * 20 / 100, ffHeight * 30 / 100);
			adImpPriceField.getDocument().addDocumentListener(
					new adImpPriceFieldListener());
			adp.add(adImpPriceField);
			//
			JLabel adexpPricel = new JLabel("默认售价：");
			adexpPricel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			adexpPricel.setSize(ffWidth / 5, ffHeight / 20);
			adexpPricel.setLocation(ffWidth * 5 / 100, ffHeight * 40 / 100);
			adp.add(adexpPricel);
			//
			adExpPriceField = new JTextField();
			adExpPriceField.setSize(ffWidth / 3, ffHeight / 20);
			adExpPriceField
					.setLocation(ffWidth * 20 / 100, ffHeight * 40 / 100);
			adExpPriceField.getDocument().addDocumentListener(
					new adExpPriceFieldListener());
			adp.add(adExpPriceField);
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
			//
			JLabel denamel = new JLabel("商品名：");
			denamel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			denamel.setSize(ffWidth / 5, ffHeight / 20);
			denamel.setLocation(ffWidth * 5 / 100, ffHeight * 6 / 100);
			dep.add(denamel);
			//
			String[] comNames=null;
			try {
				comNames = Commodity.getComNames();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			delnamebox = new JComboBox(comNames);
			delnamebox.setSize(ffWidth / 2, ffHeight / 20);
			delnamebox.setLocation(ffWidth * 20 / 100, ffHeight * 6 / 100);
			delnamebox.addActionListener(new delnameboxListener());
			dep.add(delnamebox);
			//
			JLabel demodell = new JLabel("型号：");
			demodell.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			demodell.setSize(ffWidth / 5, ffHeight / 20);
			demodell.setLocation(ffWidth * 5 / 100, ffHeight * 15 / 100);
			dep.add(demodell);
			//
			String[] defaultcontent={"请选择型号"};
			delmodelbox = new JComboBox(defaultcontent);
			delmodelbox.setSize(ffWidth / 2, ffHeight / 20);
			delmodelbox.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
			delmodelbox.addActionListener(
					new delmodelboxListener());
			dep.add(delmodelbox);
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
			JLabel upnamel = new JLabel("商品名：");
			upnamel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			upnamel.setSize(ffWidth / 5, ffHeight / 20);
			upnamel.setLocation(ffWidth * 5 / 100, ffHeight * 6 / 100);
			upp.add(upnamel);
			//
			updnamebox = new JComboBox(comNames);
			updnamebox.setSize(ffWidth / 2, ffHeight / 20);
			updnamebox.setLocation(ffWidth * 20 / 100, ffHeight * 6 / 100);
			updnamebox.addActionListener(new updnameboxListener());
			upp.add(updnamebox);
			//
			JLabel upmodell = new JLabel("型号：");
			upmodell.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			upmodell.setSize(ffWidth / 5, ffHeight / 20);
			upmodell.setLocation(ffWidth * 5 / 100, ffHeight * 15 / 100);
			upp.add(upmodell);
			//
			updmodelbox = new JComboBox(defaultcontent);
			updmodelbox.setSize(ffWidth / 2, ffHeight / 20);
			updmodelbox.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
			updmodelbox.addActionListener(new updmodelboxListener());
			upp.add(updmodelbox);
			//
			JLabel updimpPricel = new JLabel("默认进价：");
			updimpPricel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			updimpPricel.setSize(ffWidth / 5, ffHeight / 20);
			updimpPricel.setLocation(ffWidth * 5 / 100, ffHeight * 30 / 100);
			upp.add(updimpPricel);
			//
			updImpPriceField = new JTextField();
			updImpPriceField.setSize(ffWidth / 3, ffHeight / 20);
			updImpPriceField.setLocation(ffWidth * 20 / 100,
					ffHeight * 30 / 100);
			updImpPriceField.getDocument().addDocumentListener(
					new updImpPriceFieldListener());
			upp.add(updImpPriceField);
			//
			JLabel upexpPricel = new JLabel("默认售价：");
			upexpPricel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			upexpPricel.setSize(ffWidth / 5, ffHeight / 20);
			upexpPricel.setLocation(ffWidth * 5 / 100, ffHeight * 40 / 100);
			upp.add(upexpPricel);
			//
			updExpPriceField = new JTextField();
			updExpPriceField.setSize(ffWidth / 3, ffHeight / 20);
			updExpPriceField.setLocation(ffWidth * 20 / 100,
					ffHeight * 40 / 100);
			updExpPriceField.getDocument().addDocumentListener(
					new updExpPriceFieldListener());
			upp.add(updExpPriceField);
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
				case "增加商品": {
					adp.setVisible(true);
					dep.setVisible(false);
					upp.setVisible(false);
				}
					break;
				case "删除商品": {
					adp.setVisible(false);
					dep.setVisible(true);
					upp.setVisible(false);
				}
					break;
				case "修改商品信息": {
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

		class admodelFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				addmodel = admodelField.getText();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				addmodel = admodelField.getText();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				addmodel = admodelField.getText();
			}

		}

		class adImpPriceFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent e) {
				addImpPrice = Integer.parseInt(adImpPriceField.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				addImpPrice = Integer.parseInt(adImpPriceField.getText());

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				addImpPrice = Integer.parseInt(adImpPriceField.getText());

			}

		}

		class adExpPriceFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent e) {
				addExpPrice = Integer.parseInt(adExpPriceField.getText());

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				addExpPrice = Integer.parseInt(adExpPriceField.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				addExpPrice = Integer.parseInt(adExpPriceField.getText());
			}

		}

		class addyesbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("File/Commodity/" + addname + addmodel
						+ ".ser");
				if (!file.exists()) {
					try {
						Commodity.commodityAdd(addname, addmodel, addImpPrice,
								addExpPrice);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "商品添加成功！", "添加成功",
							JOptionPane.INFORMATION_MESSAGE);
					jsp.remove(comInfo);
					MgrCom.this.remove(jsp);
					try {
						content = Commodity.commoditySho();
					} catch (ClassNotFoundException | IOException ef) {
						// TODO Auto-generated catch block
						ef.printStackTrace();
					}
					comInfo = new JTable(content, head);
					comInfo.setVisible(true);
					jsp = new JScrollPane();
					jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
					jsp.setLocation(jfWidth / 32, jfHeight / 20);
					jsp.setViewportView(comInfo);
					MgrCom.this.add(jsp);
					editFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "添加失败，该商品已经存在！",
							"添加失败", JOptionPane.ERROR_MESSAGE);
				}
			}

		}

		class exitbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				editFrame.dispose();
			}

		}

		class delnameboxListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				delname=delnamebox.getSelectedItem().toString();
				dep.remove(delmodelbox);
				String[] modelboxC=Commodity.getModel(delname);
				delmodelbox = new JComboBox(modelboxC);
				delmodelbox.setSize(ffWidth / 2, ffHeight / 20);
				delmodelbox.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
				delmodelbox.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
				delmodelbox.addActionListener(new delmodelboxListener());
				delmodelbox.updateUI();
				dep.add(delmodelbox);
				dep.repaint();
				dep.updateUI();
			}
		}
		class delmodelboxListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				delmodel=delmodelbox.getSelectedItem().toString();
				
			}
			
		}

		class delyesbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("File/Commodity/" + delname + delmodel
						+ ".ser");
				if (file.exists()) {
					String result = null;
					try {
						result = Commodity.commodityDel(delname, delmodel);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					switch (result) {
					case "IMPORTED": {
						JOptionPane.showMessageDialog(null, "删除失败，商品已经进货！",
								"删除失败", JOptionPane.ERROR_MESSAGE);
					}
						break;
					case "OK": {
						JOptionPane.showMessageDialog(null, "商品删除成功！", "删除成功",
								JOptionPane.INFORMATION_MESSAGE);
						jsp.remove(comInfo);
						MgrCom.this.remove(jsp);
						try {
							content = Commodity.commoditySho();
						} catch (ClassNotFoundException | IOException ef) {
							// TODO Auto-generated catch block
							ef.printStackTrace();
						}
						comInfo = new JTable(content, head);
						comInfo.setVisible(true);
						jsp = new JScrollPane();
						jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
						jsp.setLocation(jfWidth / 32, jfHeight / 20);
						jsp.setViewportView(comInfo);
						MgrCom.this.add(jsp);
						editFrame.dispose();
					}
						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "删除失败，商品不存在！", "删除失败",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}

		class updnameboxListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				updname=updnamebox.getSelectedItem().toString();
				upp.remove(updmodelbox);
				String[] upmodelcon=Commodity.getModel(updname);
				updmodelbox = new JComboBox(upmodelcon);
				updmodelbox.setSize(ffWidth / 2, ffHeight / 20);
				updmodelbox.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
				updmodelbox.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
				updmodelbox.addActionListener(new updmodelboxListener());
				updmodelbox.updateUI();
				upp.add(updmodelbox);
				upp.repaint();
				upp.updateUI();
				
			}

		}

		class updmodelboxListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				updmodel=updmodelbox.getSelectedItem().toString();
				
			}
			
		}

		class updImpPriceFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updImpPrice = Integer.parseInt(updImpPriceField.getText());

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updImpPrice = Integer.parseInt(updImpPriceField.getText());

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updImpPrice = Integer.parseInt(updImpPriceField.getText());
			}

		}

		class updExpPriceFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updExpPrice = Integer.parseInt(updExpPriceField.getText());

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updExpPrice = Integer.parseInt(updExpPriceField.getText());

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updExpPrice = Integer.parseInt(updExpPriceField.getText());
			}

		}

		class updyesbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("File/Commodity/" + updname + updmodel
						+ ".ser");
				if (file.exists()) {
					try {
						Commodity.commodityUpd(updname, updmodel, updImpPrice,
								updExpPrice);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "商品信息修改成功！", "修改成功",
							JOptionPane.INFORMATION_MESSAGE);
					jsp.remove(comInfo);
					MgrCom.this.remove(jsp);
					try {
						content = Commodity.commoditySho();
					} catch (ClassNotFoundException | IOException ef) {
						// TODO Auto-generated catch block
						ef.printStackTrace();
					}
					comInfo = new JTable(content, head);
					comInfo.setVisible(true);
					jsp = new JScrollPane();
					jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
					jsp.setLocation(jfWidth / 32, jfHeight / 20);
					jsp.setViewportView(comInfo);
					MgrCom.this.add(jsp);
					editFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "修改失败，商品不存在！", "修改失败",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}

	class searchButtonListener implements ActionListener {
		SearchMgr sFrame;
		JTextField snameField, smodelField;
		String sname = "", smodel = "";
		String[][] resultText = new String[1][7];
		JButton sebutton;

		@Override
		public void actionPerformed(ActionEvent e) {
			sFrame = new SearchMgr();
			sFrame.setTitle("查找商品");
			//
			JLabel snamel = new JLabel("商品名：");
			snamel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			snamel.setSize(sFrame.seWidth / 8, sFrame.seHeight / 20);
			snamel.setLocation(sFrame.seWidth * 2 / 100,
					sFrame.seHeight * 5 / 100);
			sFrame.add(snamel);
			//
			snameField = new JTextField();
			snameField.setSize(sFrame.seWidth / 3, sFrame.seHeight / 20);
			snameField.setLocation(sFrame.seWidth * 13 / 100,
					sFrame.seHeight * 5 / 100);
			snameField.getDocument().addDocumentListener(
					new snameFieldListener());
			sFrame.add(snameField);
			//
			JLabel smodell = new JLabel("型号：");
			smodell.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			smodell.setSize(sFrame.seWidth / 8, sFrame.seHeight / 20);
			smodell.setLocation(sFrame.seWidth * 52 / 100,
					sFrame.seHeight * 5 / 100);
			sFrame.add(smodell);
			//
			smodelField = new JTextField();
			smodelField.setSize(sFrame.seWidth / 3, sFrame.seHeight / 20);
			smodelField.setLocation(sFrame.seWidth * 60 / 100,
					sFrame.seHeight * 5 / 100);
			smodelField.getDocument().addDocumentListener(
					new smodelFieldListener());
			sFrame.add(smodelField);
			
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

		class smodelFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				smodel = smodelField.getText();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				smodel = smodelField.getText();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				smodel = smodelField.getText();
			}
		}

		class sebuttonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((sname.length() != 0) && (smodel.length() != 0)) {

					try {
						resultText = Commodity.commodityFin(sname, smodel);
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
				if ((sname.length() != 0) && (smodel.length() == 0)) {
					// sFrame.remove(sFrame.srjsp);
					try {
						resultText = Commodity.commoditynameFin(sname);
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
	}

	class refreshButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			jsp.remove(comInfo);
			MgrCom.this.remove(jsp);
			try {
				content = Commodity.commoditySho();
			} catch (ClassNotFoundException | IOException ef) {
				// TODO Auto-generated catch block
				ef.printStackTrace();
			}
			comInfo = new JTable(content, head);
			comInfo.setVisible(true);
			jsp = new JScrollPane();
			jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
			jsp.setLocation(jfWidth / 32, jfHeight / 20);
			jsp.setViewportView(comInfo);
			MgrCom.this.add(jsp);
		}

	}
}
