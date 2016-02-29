package program;
import java.io.*;
public class Stock{

	public static String[][] StockSho() throws ClassNotFoundException, IOException{
		File stockFolder = new File("File/Stock/");
		String[] goodList=stockFolder.list();
		String[][] result=new String[goodList.length][11];
		for(int i=0;i<goodList.length;i++){
			String filename="File/Stock/"+goodList[i];
			Good.goodUpd(filename);
			Good temp=(Good) IOhelper.readHelper(filename);
			result[i][0]=temp.name;
			result[i][1]=temp.model;
			result[i][2]=String.valueOf(temp.impNum);
			result[i][3]=String.valueOf(temp.impAveragePrice);
			result[i][4]=String.valueOf(temp.impTotalPrice);
			result[i][5]=String.valueOf(temp.expNum);
			result[i][6]=String.valueOf(temp.expAveragePrice);
			result[i][7]=String.valueOf(temp.expTotalPrice);
			result[i][8]=String.valueOf(temp.num);
			result[i][9]=String.valueOf(temp.averagePrice);
			result[i][10]=String.valueOf(temp.totalPrice);
		}
		return result;
	}
}
