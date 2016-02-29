package FPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;

import Mgr.MgrAcc;

//当点击账目人员主页时，主面板呈现的panel
public class AccountantPanel extends StandardPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MgrAcc ma;
	public AccountantPanel() throws ClassNotFoundException, IOException{
		String[] functions={"请选择功能","账目管理"};
		jcb=new JComboBox(functions);
		jcb.setSize(jfWidth * 2 / 5, jfHeight/20);
		jcb.setLocation(jfWidth * 25/100, jfHeight*3/100);
		jcb.setBackground(Color.white);
		jcb.setForeground(new Color(252, 135, 5));
		jcb.setFont(new Font("宋体",Font.TRUETYPE_FONT,15));
		jcb.setFocusable(false);
		jcb.addActionListener(new jcbListener());
		//
		ma=new MgrAcc();
		//
		label.setForeground(new Color(252, 135, 5));
		//
		this.add(jcb);
		this.add(ma);
		
	}
	class jcbListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox temp=(JComboBox)e.getSource();
			switch(temp.getSelectedItem().toString()){
			case "账目管理": {
				ma.setVisible(true);
				AccountantPanel.this.repaint();
			}break;
			default:;
			}
		}
	}
}
