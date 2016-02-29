package FPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JComboBox;

import Mgr.MgrAcc;
import Mgr.MgrCom;
import Mgr.MgrCust;
import Mgr.MgrExp;
import Mgr.MgrImp;
import Mgr.MgrStock;
import Mgr.MgrUser;
//当点击管理员主页时，主面板呈现的panel
public class AdminPanel extends StandardPanel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MgrAcc ma;
	MgrCom mc;
	MgrCust mcu;
	MgrExp me;
	MgrImp mi;
	MgrStock ms;
	MgrUser mu;
	public AdminPanel() throws ClassNotFoundException, IOException{
		String[] functions={"请选择功能","账户管理","商品管理","库存管理","进货管理","销售管理","客户管理","账目管理"};
		jcb=new JComboBox(functions);
		jcb.setSize(jfWidth * 2 / 5, jfHeight/20);
		jcb.setLocation(jfWidth * 25/100, jfHeight*3/100);
		jcb.setBackground(Color.white);
		jcb.setForeground(new Color(44,128,197));
		jcb.setFont(new Font("宋体",Font.TRUETYPE_FONT,15));
		jcb.setFocusable(false);
		jcb.addActionListener(new jcbListener());
		//
		ma=new MgrAcc();
		mc=new MgrCom();
		mcu=new MgrCust();
		me=new MgrExp();
		mi=new MgrImp();
		ms=new MgrStock();
		mu=new MgrUser();
		//
		label.setForeground(new Color(44,128,197));
		//
		this.add(jcb);
		this.add(ma);
		this.add(mc);
		this.add(mcu);
		this.add(me);
		this.add(mi);
		this.add(ms);
		this.add(mu);
	}
	class jcbListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox temp=(JComboBox)e.getSource();
			switch(temp.getSelectedItem().toString()){
			case "账户管理": {
				ma.setVisible(false);
				mc.setVisible(false);
				mcu.setVisible(false);
				me.setVisible(false);
				mi.setVisible(false);
				ms.setVisible(false);
				mu.setVisible(true);
				AdminPanel.this.repaint();
			}break;
			case "商品管理": {
				ma.setVisible(false);
				mc.setVisible(true);
				mcu.setVisible(false);
				me.setVisible(false);
				mi.setVisible(false);
				ms.setVisible(false);
				mu.setVisible(false);
				AdminPanel.this.repaint();
			}break;
			case "库存管理": {
				ma.setVisible(false);
				mc.setVisible(false);
				mcu.setVisible(false);
				me.setVisible(false);
				mi.setVisible(false);
				ms.setVisible(true);
				mu.setVisible(false);
				AdminPanel.this.repaint();
			}break;
			case "进货管理": {
				ma.setVisible(false);
				mc.setVisible(false);
				mcu.setVisible(false);
				me.setVisible(false);
				mi.setVisible(true);
				ms.setVisible(false);
				mu.setVisible(false);
				AdminPanel.this.repaint();
			}break;
			case "销售管理": {
				ma.setVisible(false);
				mc.setVisible(false);
				mcu.setVisible(false);
				me.setVisible(true);
				mi.setVisible(false);
				ms.setVisible(false);
				mu.setVisible(false);
				AdminPanel.this.repaint();
			}break;
			case "客户管理": {
				ma.setVisible(false);
				mc.setVisible(false);
				mcu.setVisible(true);
				me.setVisible(false);
				mi.setVisible(false);
				ms.setVisible(false);
				mu.setVisible(false);
				AdminPanel.this.repaint();
			}break;
			case "账目管理": {
				ma.setVisible(true);
				mc.setVisible(false);
				mcu.setVisible(false);
				me.setVisible(false);
				mi.setVisible(false);
				ms.setVisible(false);
				mu.setVisible(false);
				AdminPanel.this.repaint();
			}break;
			default:;
			}
		}
		
	}
}
