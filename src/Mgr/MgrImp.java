package Mgr;

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
import program.Import;

public class MgrImp extends StandardMgr {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable impInfo;
	JScrollPane jsp;
	String[][] content;
	String[] head = { "日期", "单据类型", "客户名", "商品名称", "型号", "数量", "进货单价", "进货总价" };
	JTextField fromDateField, toDateField;
	String fromDate = "0000-0-0", toDate = "9999-12-31";

	public MgrImp() {
		JButton addButton = new JButton("创建进货单", new ImageIcon(
				"GUI/icons/add.png"));
		addButton.setFocusPainted(false);
		addButton.setSize(jfWidth / 5, jfHeight / 10);
		addButton.setLocation(jfWidth * 5 / 100, jfHeight * 70 / 100);
		addButton.setFont(new Font("宋体", Font.PLAIN, 14));
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new addButtonListener());
		this.add(addButton);
		//
		JButton delButton = new JButton("创建退货单", new ImageIcon(
				"GUI/icons/del.png"));
		delButton.setFocusPainted(false);
		delButton.setSize(jfWidth / 5, jfHeight / 10);
		delButton.setLocation(jfWidth * 30 / 100, jfHeight * 70 / 100);
		delButton.setFont(new Font("宋体", Font.PLAIN, 14));
		delButton.setHorizontalAlignment(SwingConstants.LEFT);
		delButton.addActionListener(new delButtonListener());
		this.add(delButton);
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
			content = Import.importSho(fromDate, toDate);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		impInfo = new JTable(content, head);
		impInfo.setVisible(true);
		jsp = new JScrollPane();
		jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
		jsp.setLocation(jfWidth / 32, jfHeight / 20);
		jsp.setViewportView(impInfo);
		this.add(jsp);
		//
		JLabel fdatel = new JLabel("开始时间(年-月-日)：");
		fdatel.setFont(new Font("宋体", Font.PLAIN, 14));
		fdatel.setSize(jfWidth * 2 / 11, jfHeight / 30);
		fdatel.setLocation(jfWidth * 3 / 100, jfHeight / 65);
		this.add(fdatel);
		//
		fromDateField = new JTextField();
		fromDateField.setSize(jfWidth * 2 / 11, jfHeight / 20);
		fromDateField.setLocation(jfWidth * 20 / 100, 0);
		fromDateField.getDocument().addDocumentListener(new fromDateListener());
		this.add(fromDateField);
		//
		JLabel tdatel = new JLabel("截止时间(年-月-日)：");
		tdatel.setFont(new Font("宋体", Font.PLAIN, 14));
		tdatel.setSize(jfWidth * 2 / 11, jfHeight / 30);
		tdatel.setLocation(jfWidth * 40 / 100, jfHeight / 65);
		this.add(tdatel);
		//
		toDateField = new JTextField();
		toDateField.setSize(jfWidth * 2 / 11, jfHeight / 20);
		toDateField.setLocation(jfWidth * 57 / 100, 0);
		toDateField.getDocument().addDocumentListener(new toDateListener());
		this.add(toDateField);
	}

	class fromDateListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			fromDate = fromDateField.getText();

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			fromDate = fromDateField.getText();

		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			fromDate = fromDateField.getText();

		}

	}

	class toDateListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			toDate = toDateField.getText();

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			toDate = toDateField.getText();

		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			toDate = toDateField.getText();

		}

	}

	class addButtonListener implements ActionListener {
		JComboBox custbox, namebox, modelbox;
		JFrame addFrame;
		JPanel adp;
		JTextField adPriceField, adNumField;
		String[] modelboxContent;
		String addcust = "", addname = "", addmodel = "";
		int addPrice, addnum;
		JButton addyesb, addexitb;
		int ffHeight, ffWidth;

		@Override
		public void actionPerformed(ActionEvent e) {
			addFrame = new JFrame();
			addFrame.setLayout(null);
			addFrame.setTitle("创建进货单");
			//
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			ffHeight = 548;
			ffWidth = 455;
			// 获得了与分辨率匹配的大小
			addFrame.setSize(ffWidth, ffHeight);
			addFrame.setLocation((screenWidth - ffWidth) / 2,
					(screenHeight - ffHeight) / 2);
			//
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
			try {
				namebox = new JComboBox(Commodity.getComNames());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			namebox.setSize(ffWidth / 2, ffHeight / 20);
			namebox.setLocation(ffWidth * 20 / 100, ffHeight * 6 / 100);
			namebox.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			namebox.addActionListener(new nameboxListener());
			adp.add(namebox);
			//
			JLabel admodell = new JLabel("型号：");
			admodell.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			admodell.setSize(ffWidth / 5, ffHeight / 20);
			admodell.setLocation(ffWidth * 5 / 100, ffHeight * 15 / 100);
			adp.add(admodell);
			//
			String[] beforeNameIsGiven = { "请选择型号" };
			modelbox = new JComboBox(beforeNameIsGiven);
			modelbox.setSize(ffWidth / 2, ffHeight / 20);
			modelbox.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
			modelbox.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			modelbox.addActionListener(new modelboxListener());
			adp.add(modelbox);
			//
			JLabel adPricel = new JLabel("单价：");
			adPricel.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			adPricel.setSize(ffWidth / 5, ffHeight / 20);
			adPricel.setLocation(ffWidth * 5 / 100, ffHeight * 30 / 100);
			adp.add(adPricel);
			//
			adPriceField = new JTextField();
			adPriceField.setSize(ffWidth / 3, ffHeight / 20);
			adPriceField.setLocation(ffWidth * 20 / 100, ffHeight * 30 / 100);
			adPriceField.getDocument().addDocumentListener(
					new adPriceFieldListener());
			adp.add(adPriceField);
			//
			JLabel adnuml = new JLabel("数量：");
			adnuml.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			adnuml.setSize(ffWidth / 5, ffHeight / 20);
			adnuml.setLocation(ffWidth * 5 / 100, ffHeight * 40 / 100);
			adp.add(adnuml);
			//
			adNumField = new JTextField();
			adNumField.setSize(ffWidth / 3, ffHeight / 20);
			adNumField.setLocation(ffWidth * 20 / 100, ffHeight * 40 / 100);
			adNumField.getDocument().addDocumentListener(
					new adNumFieldListener());
			adp.add(adNumField);
			//
			JLabel adcustl = new JLabel("客户名：");
			adcustl.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			adcustl.setSize(ffWidth / 5, ffHeight / 20);
			adcustl.setLocation(ffWidth * 5 / 100, ffHeight * 50 / 100);
			adp.add(adcustl);
			//
			File cust = new File("File/Customer/");
			String[] custname = cust.list();
			for (int i = 0; i < custname.length; i++) {
				custname[i] = custname[i].replace(".ser", "");
			}
			String[] custs = new String[custname.length + 1];
			custs[0] = "请选择客户";
			for (int i = 1; i < custs.length; i++) {
				custs[i] = custname[i - 1];
			}
			custbox = new JComboBox(custs);
			custbox.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
			custbox.setSize(ffWidth / 2, ffHeight / 20);
			custbox.setLocation(ffWidth * 20 / 100, ffHeight * 50 / 100);
			custbox.addActionListener(new custboxListener());
			adp.add(custbox);
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
			addFrame.add(adp);

			// //
			addFrame.repaint();
			addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			addFrame.setVisible(true);
		}

		class nameboxListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addname = namebox.getSelectedItem().toString();
				adp.remove(modelbox);
				modelboxContent = Commodity.getModel(addname);
				modelbox = new JComboBox(modelboxContent);
				modelbox.setSize(ffWidth / 2, ffHeight / 20);
				modelbox.setLocation(ffWidth * 20 / 100, ffHeight * 15 / 100);
				modelbox.setFont(new Font("宋体", Font.TRUETYPE_FONT, 14));
				modelbox.addActionListener(new modelboxListener());
				modelbox.updateUI();
				adp.add(modelbox);
				adp.repaint();
				adp.updateUI();
			}
		}

		class modelboxListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addmodel = modelbox.getSelectedItem().toString();

			}

		}

		class adPriceFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent e) {
				addPrice = Integer.parseInt(adPriceField.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				addPrice = Integer.parseInt(adPriceField.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				addPrice = Integer.parseInt(adPriceField.getText());
			}

		}

		class adNumFieldListener implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent e) {
				addnum = Integer.parseInt(adNumField.getText());

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				addnum = Integer.parseInt(adNumField.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				addnum = Integer.parseInt(adNumField.getText());
			}

		}

		class custboxListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addcust = custbox.getSelectedItem().toString();
			}

		}

		class addyesbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((addcust.length() != 0) && (addname.length() != 0)
						&& (addmodel.length() != 0) && (addnum != 0)) {
					try {
						Import.importAdd(addcust, addname, addmodel, addnum,
								addPrice);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "进货单添加成功！", "添加成功",
							JOptionPane.INFORMATION_MESSAGE);
					jsp.remove(impInfo);
					MgrImp.this.remove(jsp);
					try {
						content = Import.importSho(fromDate, toDate);
					} catch (ClassNotFoundException | IOException ef) {
						// TODO Auto-generated catch block
						ef.printStackTrace();
					}
					impInfo = new JTable(content, head);
					impInfo.setVisible(true);
					jsp = new JScrollPane();
					jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
					jsp.setLocation(jfWidth / 32, jfHeight / 20);
					jsp.setViewportView(impInfo);
					MgrImp.this.add(jsp);
					addFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "信息不完整！", "添加失败",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}

		class exitbListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				addFrame.dispose();
			}

		}
	}

	class delButtonListener implements ActionListener {
		String delname;
		int selectedRowNum;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			selectedRowNum = impInfo.getSelectedRow();
			if (selectedRowNum != -1) {
				String[] delParameters = new String[4];
				delParameters[0] = (String) impInfo.getValueAt(selectedRowNum,
						0);
				delParameters[1] = (String) impInfo.getValueAt(selectedRowNum,
						2);
				delParameters[2] = (String) impInfo.getValueAt(selectedRowNum,
						3);
				delParameters[3] = (String) impInfo.getValueAt(selectedRowNum,
						4);
				try {
					Import.importDel(delParameters[0], delParameters[1],
							delParameters[2], delParameters[3]);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "退货单添加成功！", "退货成功",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "请先选择一张进货单！", "退货失败",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	class refreshButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MgrImp.this.remove(jsp);
			if ((fromDate.length() != 0) && (toDate.length() != 0)) {
				try {
					content = Import.importSho(fromDate, toDate);
				} catch (ClassNotFoundException | IOException ef) {
					// TODO Auto-generated catch block
					ef.printStackTrace();
				}
			} 
			else {
				fromDate = "0000-0-0";
				toDate = "9999-9-9";
				try {
					content = Import.importSho(fromDate, toDate);
				} catch (ClassNotFoundException | IOException ef) {
					// TODO Auto-generated catch block
					ef.printStackTrace();
				}
			}
			impInfo = new JTable(content, head);
			impInfo.setVisible(true);
			jsp = new JScrollPane();
			jsp.setSize(jfWidth * 8 / 11, jfHeight * 3 / 5);
			jsp.setLocation(jfWidth / 32, jfHeight / 20);
			jsp.setViewportView(impInfo);
			MgrImp.this.add(jsp);
		}

	}
}
