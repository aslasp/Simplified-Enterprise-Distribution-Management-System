package FPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;

import Mgr.MgrCom;
import Mgr.MgrStock;

//当点击库存管理员主页时，主面板呈现的panel
public class StoPanel extends StandardPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MgrCom mc;
	MgrStock ms;
	public StoPanel() throws ClassNotFoundException, IOException{
		String[] functions={"请选择功能","商品管理","库存管理"};
		jcb=new JComboBox(functions);
		jcb.setSize(jfWidth * 2 / 5, jfHeight/20);
		jcb.setLocation(jfWidth * 25/100, jfHeight*3/100);
		jcb.setBackground(Color.white);
		jcb.setForeground(new Color(2,119,79));
		jcb.setFont(new Font("宋体",Font.TRUETYPE_FONT,15));
		jcb.setFocusable(false);
		jcb.addActionListener(new jcbListener());
		//
		mc=new MgrCom();
		ms=new MgrStock();
		//
		label.setForeground(new Color(2,119,79));
		//
		this.add(jcb);
		this.add(mc);
		this.add(ms);
	}
	class jcbListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox temp=(JComboBox)e.getSource();
			switch(temp.getSelectedItem().toString()){
			case "商品管理": {
				mc.setVisible(true);
				ms.setVisible(false);
				StoPanel.this.repaint();
			}break;
			case "库存管理": {
				mc.setVisible(false);
				ms.setVisible(true);
				StoPanel.this.repaint();
			}break;
			default:;
			}
		}
	}
}
