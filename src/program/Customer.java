package program;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name, contact;
	int toReceive, toPay, total;// 客户欠我们的钱，我们欠客户的钱

	public static void customerAdd(String name, String contact)
			throws ClassNotFoundException, IOException {
		Customer customer = new Customer();
		customer.name = name;
		customer.contact = contact;
		customer.toPay = 0;
		customer.toReceive = 0;
		customer.total = 0;
		String filename = "File/Customer/" + name + ".ser";
		IOhelper.ADD(customer, filename);
	}

	public static void customerUpd(String name, String contact)
			throws ClassNotFoundException, IOException {
		String filename = "File/Customer/" + name + ".ser";
		Customer temp = (Customer) IOhelper.readHelper(filename);
		temp.contact = contact;
		IOhelper.UPD(filename, temp, filename);
	}

	public static String customerDel(String name) {
		boolean canDel = true;
		File checkImpFolder = new File("File/Import/");
		String[] checkImp = checkImpFolder.list();
		File checkExpFolder = new File("File/Export/");
		String[] checkExp = checkExpFolder.list();
		for (int i = 0; i < checkImp.length; i++) {
			if (checkImp[i].indexOf(name + "-") != -1) {
				canDel = false;
				break;
			}
		}
		for (int i = 0; i < checkExp.length; i++) {
			if (checkExp[i].indexOf(name + "-") != -1) {
				canDel = false;
				break;
			}
		}
		if (canDel) {
			String filename = "File/Customer/" + name + ".ser";
			IOhelper.DEL(filename);
			return "OK";
		}
		else{
			return "IMPORTED";
		}
		
	}

	public static String[][] customerFin(String name) throws ClassNotFoundException,
			IOException {
		File folder = new File("File/Customer/");
		String[] namelist = folder.list();
		ArrayList<String> goodname=new ArrayList<String>();
		for (int i = 0; i < namelist.length; i++) {
			if (namelist[i].indexOf(name) != -1) {
				goodname.add(namelist[i]);
			}
		}
		String[] toshow=(String[])goodname.toArray(new String[0]);
		String[][] result=new String[toshow.length][5];
		for (int i = 0; i < toshow.length; i++) {
			Customer temp = (Customer) IOhelper.readHelper("File/Customer/"
					+ toshow[i]);
			result[i][0] = temp.name;
			result[i][1] = temp.contact;
			result[i][2] = String.valueOf(temp.toReceive);
			result[i][3] = String.valueOf(temp.toPay);
			result[i][4] = String.valueOf(temp.total);
		}
		return result;
	}

	public static String[][] customerSho() throws ClassNotFoundException,
			IOException {
		File customerFolder = new File("File/Customer/");
		String[] test = customerFolder.list();
		String[][] result = new String[test.length][5];
		for (int i = 0; i < test.length; i++) {
			Customer temp = (Customer) IOhelper.readHelper("File/Customer/"
					+ test[i]);
			result[i][0] = temp.name;
			result[i][1] = temp.contact;
			result[i][2] = String.valueOf(temp.toReceive);
			result[i][3] = String.valueOf(temp.toPay);
			result[i][4] = String.valueOf(temp.total);
		}
		return result;
	}

	public static void addToPay(String nameOfCustomer, int toPay)
			throws ClassNotFoundException, IOException {
		String filename = "File/Customer/" + nameOfCustomer + ".ser";
		File file = new File(filename);
		if (!file.exists())
			System.out.println("先登记客户！");
		Customer temp = (Customer) IOhelper.readHelper(filename);
		temp.toPay = temp.toPay + toPay;
		temp.total = temp.toReceive - temp.toPay;
		IOhelper.UPD(filename, temp, filename);
		
	}

	public static void delToPay(String nameOfCustomer, int cancelMoney)
			throws ClassNotFoundException, IOException {
		String filename = "File/Customer/" + nameOfCustomer + ".ser";
		Customer temp = (Customer) IOhelper.readHelper(filename);
		temp.toPay = temp.toPay - cancelMoney;
		temp.total = temp.toReceive - temp.toPay;
		IOhelper.UPD(filename, temp, filename);
	}

	public static void addToReceive(String nameOfCustomer, int toReceive)
			throws ClassNotFoundException, IOException {
		String filename = "File/Customer/" + nameOfCustomer + ".ser";
		Customer temp = (Customer) IOhelper.readHelper(filename);
		temp.toReceive = temp.toReceive + toReceive;
		temp.total = temp.toReceive - temp.toPay;
		IOhelper.UPD(filename, temp, filename);
	}

	public static void delToReceive(String nameOfCustomer, int cancelMoney)
			throws ClassNotFoundException, IOException {
		String filename = "File/Customer/" + nameOfCustomer + ".ser";
		Customer temp = (Customer) IOhelper.readHelper(filename);
		temp.toReceive = temp.toReceive - cancelMoney;
		temp.total = temp.toReceive - temp.toPay;
		IOhelper.UPD(filename, temp, filename);
	}
	public static String[] getCustNames(){
		File folder=new File("File/Customer/");
		String[] custs=folder.list();
		for(int i=0;i<custs.length;i++)
			custs[i]=custs[i].replace(".ser", "");
		String[] result=new String[custs.length+1];
		result[0]="请选择客户";
		for(int i=1;i<result.length;i++){
			result[i]=custs[i-1];
		}
		return result;
	}
	
}
