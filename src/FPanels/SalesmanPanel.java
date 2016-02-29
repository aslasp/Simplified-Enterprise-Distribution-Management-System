package FPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import Mgr.MgrCust;
import Mgr.MgrExp;
import Mgr.MgrImp;

//当点击销售人员主页时，主面板呈现的panel
public class SalesmanPanel extends StandardPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MgrCust mcu;
	MgrExp me;
	MgrImp mi;
	public SalesmanPanel(){
		String[] functions={"请选择功能","进货管理","销售管理","客户管理"};
		jcb=new JComboBox(functions);
		jcb.setSize(jfWidth * 2 / 5, jfHeight/20);
		jcb.setLocation(jfWidth * 25/100, jfHeight*3/100);
		jcb.setBackground(Color.white);
		jcb.setForeground(new Color(204,0,51));
		jcb.setFont(new Font("宋体",Font.TRUETYPE_FONT,15));
		jcb.setFocusable(false);
		jcb.addActionListener(new jcbListener());
		//
		mcu=new MgrCust();
		me=new MgrExp();
		mi=new MgrImp();
		//
		label.setForeground(new Color(204,0,51));
		//
		this.add(jcb);
		this.add(mcu);
		this.add(me);
		this.add(mi);
	}
	class jcbListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox temp=(JComboBox)e.getSource();
			switch(temp.getSelectedItem().toString()){
			case "进货管理": {
				mcu.setVisible(false);
				me.setVisible(false);
				mi.setVisible(true);
				SalesmanPanel.this.repaint();
			}break;
			case "销售管理": {
				mcu.setVisible(false);
				me.setVisible(true);
				mi.setVisible(false);
				SalesmanPanel.this.repaint();
			}break;
			case "客户管理": {
				mcu.setVisible(true);
				me.setVisible(false);
				mi.setVisible(false);
				SalesmanPanel.this.repaint();
			}break;
			default:;
			}
			
		}
		
	}
}
