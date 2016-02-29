package program;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	public Role role;
	public static void addUser(String username,String password,Role role) throws ClassNotFoundException, IOException{
		String filename="File/Users/"+username+".ser";
		boolean wrongname=false;
		File usersFolder = new File("File/Users/");
		String[] usersTest=usersFolder.list();
		for(int i=0;i<usersTest.length;i++){
			if(usersTest[i].equals(username+".ser")){
				System.out.println("用户名已存在！");
				wrongname=true;
			}
		}
		if(!wrongname){
			Users user=new Users();
			user.username=username;
			user.password=password;
			user.role=role;
			IOhelper.writeHelper(user,filename);
		}
	}
	public static void delUser(String username){
		String filename="File/Users/"+username+".ser";
		IOhelper.DEL(filename);
	}
	public static String[][] getUserInfo() throws ClassNotFoundException, IOException{
		File userFolder=new File("File/Users/");
		String[] userTest=userFolder.list();
		String[][] info=new String[userTest.length][2];
		for(int i=0;i<userTest.length;i++){
			Users temp=(Users) IOhelper.readHelper("File/Users/"+userTest[i]);
			info[i][0]=temp.username;
			info[i][1]=temp.role.toString();
		}
		return info;
	}
}
