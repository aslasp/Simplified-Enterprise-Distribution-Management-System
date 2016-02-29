package Others;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.Client;
import program.IOhelper;
import program.Users;
public class LoginFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id,key;
	JTextField idField;
	JPasswordField passwordField;
	int screenHeight,screenWidth,jfHeight,jfWidth;
	public LoginFrame(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize =kit.getScreenSize();
		screenHeight=screenSize.height;
		screenWidth=screenSize.width;
		jfHeight=307;
		jfWidth=456;
		//获得了与分辨率匹配的大小
		this.setSize(jfWidth,jfHeight);
		this.setLocation((screenWidth-jfWidth)/2,(screenHeight-jfHeight)/2);
		//设置好了窗口大小，位置
        this.setTitle("欢迎使用进销存系统");
        Image image=kit.getImage("GUI/Login/icon.png");
        this.setIconImage(image);
        //设置了图标
        
        //功能panel
        JPanel panel=new JPanel(){
        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {  
            ImageIcon icon = new ImageIcon("GUI/Login/background.jpeg");  
            Image img = icon.getImage();  
            g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());  
        }  
        };
        panel.setSize(jfWidth,jfHeight);
        panel.setLocation(0,0);
        //搞了一个和frame一样size的panel出来
        idField=new JTextField();
        idField.setSize(jfWidth/2,jfHeight/10);
        idField.setLocation(jfWidth*2/7,jfHeight/5);
        idField.getDocument().addDocumentListener(new idFieldListener());
        idField.setVisible(true);
        //设置好了一个用户名文本域
        passwordField=new JPasswordField();
        passwordField.setSize(jfWidth/2,jfHeight/10);
        passwordField.setLocation(jfWidth*2/7,jfHeight*2/5);
        passwordField.addKeyListener(new passwordFieldListener());
        passwordField.setVisible(true);
        //设置好了一个密码域
        JButton loginButton=new JButton("登 录");
        Font buttonFont=new Font("登 录", Font.PLAIN, 13);
        loginButton.setFont(buttonFont);
        loginButton.setSize(jfWidth/5,jfHeight/10);
        loginButton.setLocation(jfWidth*2/9,jfHeight*3/5);
        loginButton.addActionListener(new LoginListener());
        loginButton.setVisible(true);
        //设置好了登录按钮
        JButton helpButton=new JButton("帮 助");
        Font helpButtonFont=new Font("帮 助", Font.PLAIN, 13);
        helpButton.setFont(helpButtonFont);
        helpButton.setSize(jfWidth/5,jfHeight/10);
        helpButton.setLocation(jfWidth*4/7,jfHeight*3/5);
        helpButton.addActionListener(new helpListener());
        helpButton.setVisible(true);
        //设置好了帮助按钮
        JLabel idLabel=new JLabel("用户名");
        Font idLabelFont=new Font("用户名", Font.BOLD, 14);
        idLabel.setFont(idLabelFont);
        idLabel.setSize(jfWidth/5,jfHeight/11);
        idLabel.setLocation(jfWidth/7,jfHeight/5);
        idLabel.setVisible(true);
        //设置了用户名标签
        JLabel passwordLabel=new JLabel("密码");
        Font passwordLabelFont=new Font("密码", Font.BOLD, 14);
        passwordLabel.setFont(passwordLabelFont);
        passwordLabel.setSize(jfWidth/5,jfHeight/11);
        passwordLabel.setLocation(jfWidth/7,jfHeight*2/5);
        passwordLabel.setVisible(true);
        //设置了密码标签
        JLabel mylabel=new JLabel("made by 王宁 131250079");
        Font mylabelFont=new Font("made by 王宁 131250079", Font.ITALIC, 11);
        mylabel.setFont(mylabelFont);
        mylabel.setSize(jfWidth/2,jfHeight/11);
        mylabel.setLocation(jfWidth*4/6,jfHeight*9/11);
        mylabel.setVisible(true);
        //设置了我的标签
        panel.setLayout(null);
        panel.add(helpButton);
        panel.add(idLabel);
        panel.add(passwordLabel);
        panel.add(mylabel);
        panel.add(loginButton);
        panel.add(idField);
        panel.add(passwordField);
        //把组件添加到panel
        panel.setVisible(true);
       
        //向LoginFrame添加组件
        this.add(panel);
        this.repaint();
        this.setResizable(false);//不允许调整窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭时结束进程
        this.setVisible(true);//窗口可见
	}
	//下面的类是用户名文本域的事件监听器
	class idFieldListener implements DocumentListener{
		@Override
		public void changedUpdate(DocumentEvent d) {
			LoginFrame.this.id=LoginFrame.this.idField.getText();
		}

		@Override
		public void insertUpdate(DocumentEvent d) {
			LoginFrame.this.id=LoginFrame.this.idField.getText();
		}

		@Override
		public void removeUpdate(DocumentEvent d) {
			LoginFrame.this.id=LoginFrame.this.idField.getText();
		}
	}
	//下面的类是密码域的事件监听器
	class passwordFieldListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {	
		}
		@Override
		public void keyReleased(KeyEvent e) {
			JPasswordField jpf=(JPasswordField) e.getSource();
			char[]p=jpf.getPassword();
			LoginFrame.this.key=new String(p);		
		}
		@Override
		public void keyTyped(KeyEvent e) {		
		}
	}
	//下面的类是按钮的事件监听器
	class LoginListener implements ActionListener ,Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String filename="File/Users/"+LoginFrame.this.id+".ser";
			File file=new File(filename);
			if(file.exists()){
				Users user = null;
				try {
					user = (Users)IOhelper.readHelper(filename);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				if(user.password.equals(LoginFrame.this.key)){
					//用户名密码匹配，登录成功
					try {
						Client.ClientDistinguish(user);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					LoginFrame.this.dispose();
					}
				else{
					//密码错误
					JOptionPane.showMessageDialog(null, "童鞋你用户名或密码输错了！",
							"错误：用户名或密码错误", JOptionPane.ERROR_MESSAGE);
				}
				}
			else{
				JOptionPane.showMessageDialog(null, "皇上，臣妾做不到啊！", "错误：用户名不存在", JOptionPane.ERROR_MESSAGE);
			}
			}
		}
	class helpListener implements ActionListener{		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFrame helpFrame=new JFrame();
		helpFrame.setSize(jfWidth,jfHeight*2/3);
		helpFrame.setLocation((screenWidth-jfWidth)/2,(screenHeight-jfHeight)/2);
		JPanel textPanel=new JPanel();
		textPanel.setSize(jfWidth,jfHeight*2/3);
		textPanel.setLocation(0, 0);
		JTextArea jta=new JTextArea();
		jta.setSize(jfWidth,jfHeight*2/3);
		jta.setLocation(0,0);
		File file=new File("File/Welcome.txt");
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = "";
		try {
			s = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(s!=null){
		jta.append(s+"\n");
		try {
			s=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		Font f=new Font("宋体",Font.PLAIN,15);
		jta.setFont(f);
		jta.setEnabled(false);
		textPanel.setLayout(null);
		textPanel.add(jta);
		helpFrame.add(textPanel);
		helpFrame.setTitle("帮助");
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpFrame.setVisible(true);
	}
	}
}
