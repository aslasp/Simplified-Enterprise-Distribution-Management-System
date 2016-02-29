package program;
import java.io.*;
public class Good implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Good类是存放在Stock里的货物，每个good对象带有货物的必要信息，用于STOCK_SHO功能
	String name,model;
	int impNum,impTotalPrice;//impNumber:进货数量，进货时增加，退货时减少
	int expNum,expTotalPrice;
	int num,totalPrice;
	double  impAveragePrice,expAveragePrice,averagePrice;
	public static void addGood(String name,String model) 
			throws IOException, ClassNotFoundException{
		Good good=new Good();
		good.name=name;
		good.model=model;
		IOhelper.ADD(good,"File/Stock/"+good.name+good.model+".ser");
	}
	public static void getImpInfo(String fileplace) 
			throws ClassNotFoundException, IOException{
		Good temp=(Good)IOhelper.readHelper(fileplace);
		int price=0;//真进货总价
		int impNumber=0;//真进货总数
		File impFolder = new File("File/Import/");
		String[] impList = impFolder.list();
		for(int i=0;i<impList.length;i++){
			Import toCheck=(Import)IOhelper.readHelper("File/Import/"+impList[i]);
			if((toCheck.commodity.name.equals(temp.name))&&(toCheck.commodity.model.equals(temp.model))){
				price=price+(toCheck.importPrice*toCheck.realImpNum);
				impNumber=impNumber+toCheck.realImpNum;
			}
		}
		temp.impNum=impNumber;
		temp.impTotalPrice=price;
		if(impNumber!=0)
			temp.impAveragePrice=price/impNumber;
		else
			temp.impAveragePrice=0.0;
		IOhelper.UPD(fileplace,temp,fileplace);
	}
	public static void getExpInfo(String fileplace) 
			throws ClassNotFoundException, IOException{
		Good temp=(Good)IOhelper.readHelper(fileplace);
		int price=0;//真销售货总价
		int expNum=0;//真销售总数
		File expFolder = new File("File/Export/");
		String[] expList = expFolder.list();
		for(int i=0;i<expList.length;i++){
			Export toCheck=(Export)IOhelper.readHelper("File/Export/"+expList[i]);
			if((toCheck.commodity.name.equals(temp.name))&&(toCheck.commodity.model.equals(temp.model))){
				price=price+(toCheck.exportPrice*toCheck.realExpNum);
				expNum=expNum+toCheck.realExpNum;
			}
		}
		temp.expNum=expNum;
		temp.expTotalPrice=price;
		if(expNum!=0)
			temp.expAveragePrice=price/expNum;
		else
			temp.expAveragePrice=0.0;
		IOhelper.UPD(fileplace,temp,fileplace);
	}
	public static void getMacroInfo(String fileplace) throws ClassNotFoundException, IOException{
		Good temp=(Good)IOhelper.readHelper(fileplace);
		String filename="File/Commodity/"+temp.name+temp.model+".ser";
		Commodity commodity=(Commodity)IOhelper.readHelper(filename);
		temp.num=commodity.num;
		temp.totalPrice= (int) (temp.impAveragePrice*temp.num);
		if(temp.num!=0)
			temp.averagePrice=temp.totalPrice/temp.num;
		else
			temp.averagePrice=0.0;
		IOhelper.UPD(fileplace,temp,fileplace);
	}
	public static void goodUpd(String fileplace) throws ClassNotFoundException, IOException{
		getExpInfo(fileplace);
		getImpInfo(fileplace);
		getMacroInfo(fileplace);
	}
}
