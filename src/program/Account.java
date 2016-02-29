package program;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Account {
	static int ourMoney=10000;//公司的钱
	static int totalToPay=0,totalToReceive=0;//我们欠的钱，别人欠我们的钱
	public static void accountIni() throws IOException, ClassNotFoundException{
		ourMoney=10000;
		accountUpd();
	}
	public static void accountIn(String name,int money) 
			throws ClassNotFoundException, IOException{
		String customerFile="File/Customer/"+name+".ser";
		Customer temp=(Customer)IOhelper.readHelper(customerFile);
		temp.toReceive=temp.toReceive-money;
		temp.total=temp.toReceive-temp.toPay;
		IOhelper.UPD(customerFile, temp, customerFile);
		accountUpd();
		ourMoney=ourMoney+money;
		accountDetAdd("accountIn",name+";"+money);
	}
	public static void accountOut(String name,int money) 
			throws ClassNotFoundException, IOException{
		String customerFile="File/Customer/"+name+".ser";
		Customer temp=(Customer)IOhelper.readHelper(customerFile);
		temp.toPay=temp.toPay-money;
		temp.total=temp.toReceive-temp.toPay;
		IOhelper.UPD(customerFile, temp, customerFile);
		accountUpd();
		ourMoney=ourMoney-money;
		accountDetAdd("accountOut",name+";"+money);
	}
	public static void accountUpd() throws IOException, ClassNotFoundException{
		totalToPay=0;
		totalToReceive=0;
		File customerFolder = new File("File/Customer/");
		String[] customerTest=customerFolder.list();
		for(int i=0;i<customerTest.length;i++){
			Customer temp=(Customer) IOhelper.readHelper("File/Customer/"+customerTest[i]);
			totalToPay=totalToPay+temp.toPay;
			totalToReceive=totalToReceive+temp.toReceive;
		}
		
		String content=ourMoney+";"+totalToReceive+";"+totalToPay;
		String filename="File/Account/Account.txt";
		IOhelper.fileUpd(filename, content);
	}
	public static void accountDetAdd(String type,String detail) throws IOException{
		String date=IOhelper.dateHelper();
		String content=date+";"+type+";"+detail;
		String filename="File/Account/AccountDetail.txt";
		IOhelper.fileWrite(filename, content);
	}
	public static String[][] accountAll() throws IOException, ClassNotFoundException{
		accountUpd();
		String filename="File/Account/Account.txt";
		BufferedReader br=new BufferedReader(new FileReader(filename));
		String[][] result=new String[1][3];
		result[0]=br.readLine().split(";");
		br.close();
		return result;
	}
	public static String[][] accountDet() throws IOException{
		String filename="File/Account/AccountDetail.txt";
		String[] data=IOhelper.fileRead(filename);
		String[][] result=new String[data.length][4];
		for(int i=0;i<data.length;i++){
			String[] temp=data[i].split(";");
			for(int j=0;j<temp.length;j++){
				result[i][j]=temp[j];
			}
		}
		return result;

	}
	
}
