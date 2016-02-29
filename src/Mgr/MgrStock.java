package Mgr;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import program.Stock;

public class MgrStock extends StandardMgr {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel, dogpanel;
	JDialog stoF;
	JScrollPane jsp;
	String[] head = { "名称", "型号", "进货数量", "进货均价", "进货总价", "销售数量", "销售均价",
			"销售总价", "库存数量", "库存均价", "库存总价" };

	public MgrStock() {
		panel = new JPanel() {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("GUI/kabigon.png");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};
		panel.setSize(jfWidth, jfHeight);
		panel.setLocation(0, 0);
		panel.addMouseListener(new sbListener());
		panel.setVisible(true);
		this.add(panel);
		//
		dogpanel = new JPanel() {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("GUI/kabigon-doge.png");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};
		dogpanel.setSize(jfWidth, jfHeight);
		dogpanel.setLocation(0, 0);
		dogpanel.setVisible(false);
		this.add(dogpanel);
	}

	class sbListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			panel.setVisible(false);
			dogpanel.setVisible(true);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			panel.setVisible(true);
			dogpanel.setVisible(false);
			stoF=new JDialog();
			stoF.setTitle("库存管理");
			stoF.setSize(jfWidth*4/3, jfHeight*4/3);
			stoF.setLocation(jfWidth/10, jfHeight/10);
			JTable stoInfo = null;
				try {
					stoInfo = new JTable(Stock.StockSho(), head);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			jsp = new JScrollPane();
			jsp.setViewportView(stoInfo);
			
			stoF.add(jsp);
			stoF.setModal(true);
			stoF.setVisible(true);
		}

	}
}
