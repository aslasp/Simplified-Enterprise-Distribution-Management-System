package program;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
public class Import implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Commodity commodity;
	String nameOfSeller;
	int importNum,importPrice,totalPrice;
	String addDate;
	int realImpNum;
	public static void importAdd(String nos,String name,String model,int iN,int iP) 
			throws IOException, ClassNotFoundException{
		Import imp=new Import();
		String commodityFile="File/Commodity/"+name+model+".ser";
		imp.commodity=(Commodity)IOhelper.readHelper(commodityFile);
		imp.commodity.impLastLatest=imp.commodity.latest_importPrice;
		imp.commodity.latest_importPrice = iP;  //获取最近进货价格
		imp.commodity.num = imp.commodity.num + iN;  //增加商品数量
		IOhelper.UPD(commodityFile,imp.commodity,commodityFile);
		//保存Commodity对象的最新状态
		imp.nameOfSeller=nos;
		imp.totalPrice=iN*iP;     
		imp.importNum=imp.realImpNum=iN;
		imp.importPrice=iP;
		imp.addDate = IOhelper.dateHelper();
		String filename="File/Import/"+imp.addDate+"-"+
				imp.nameOfSeller+"-"+imp.commodity.name+imp.commodity.model+".ser";
		IOhelper.ADD(imp, filename);
		Customer.addToPay(nos,imp.totalPrice);
	}
	public static void importDel(String adddate,String nos,String cname,String cmodel)
			throws IOException, ClassNotFoundException{
		//退货不删进货单，而是创建一个退货单文件，并且修改客户应收金额
		String filename="File/Import/"+adddate+"-"+nos+"-"+cname+cmodel+".ser";
		Import temp=(Import)IOhelper.readHelper(filename);
		int cancelNum=temp.importNum;
		ImpCancel.impCancelAdd(temp,cancelNum);
		//因为退货，对应Commodity的数量也将减少
		temp.commodity.num=temp.commodity.num-cancelNum;
		temp.commodity.latest_importPrice=temp.commodity.impLastLatest;
		temp.realImpNum=temp.realImpNum-cancelNum;
		IOhelper.UPD("File/Commodity/"+cname+cmodel+".ser",temp.commodity,
				"File/Commodity/"+cname+cmodel+".ser");
		IOhelper.UPD(filename, temp, filename);
	}
	public static String[][] importSho(String fromDate, String toDate)
			throws IOException, ClassNotFoundException {
		String[] fromsplit=fromDate.split("-");
		int fromY=Integer.parseInt(fromsplit[0]);
		int fromM=Integer.parseInt(fromsplit[1]);
		int fromD=Integer.parseInt(fromsplit[2]);
		//
		String[] tosplit=toDate.split("-");
		int toY=Integer.parseInt(tosplit[0]);
		int toM=Integer.parseInt(tosplit[1]);
		int toD=Integer.parseInt(tosplit[2]);
		//
		File impFolder = new File("File/Import/");
		String[] impTest = impFolder.list();
		File impCancelFolder = new File("File/ImportCancel/");
		String[] cancelTest = impCancelFolder.list();
		ArrayList<String> goodname = new ArrayList<String>();
		for (int i = 0; i < impTest.length; i++) {
			Import temp = (Import) IOhelper.readHelper("File/Import/"
					+ impTest[i]);
			//
			String[] datesplit=temp.addDate.split("-");
			int year=Integer.parseInt(datesplit[0]);
			int month=Integer.parseInt(datesplit[1]);
			int day=Integer.parseInt(datesplit[2]);
			//
			if((fromY<year)&&(year<toY))
				goodname.add(impTest[i]);
			if((fromY==year)||(year==toY)){
				if((fromM<month)&&(month<toM))
					goodname.add(impTest[i]);
				if((fromM==month)||(month==toM)){
					if((fromD<=day)&&(day<=toD))
						goodname.add(impTest[i]);
				}
			}
		}
		ArrayList<String> gooddel = new ArrayList<String>();
		for (int i = 0; i < cancelTest.length; i++) {
			ImpCancel temp = (ImpCancel) IOhelper
					.readHelper("File/ImportCancel/" + cancelTest[i]);
			String[] datesplit=temp.delDate.split("-");
			int year=Integer.parseInt(datesplit[0]);
			int month=Integer.parseInt(datesplit[1]);
			int day=Integer.parseInt(datesplit[2]);
			//
			if((fromY<year)&&(year<toY))
				gooddel.add(cancelTest[i]);
			if((fromY==year)||(year==toY)){
				if((fromM<month)&&(month<toM))
					gooddel.add(cancelTest[i]);
				if((fromM==month)||(month==toM)){
					if((fromD<=day)&&(day<=toD))
						gooddel.add(cancelTest[i]);
				}
			}
		}
		String[][] result = new String[goodname.size() + gooddel.size()][8];
		String[] gname = (String[]) goodname.toArray(new String[0]);
		for (int i = 0; i < gname.length; i++) {
			Import temp = (Import) IOhelper.readHelper("File/Import/"
					+ gname[i]);
			result[i][0] = temp.addDate;
			result[i][1] = "进货";
			result[i][2] = temp.nameOfSeller;
			result[i][3] = temp.commodity.name;
			result[i][4] = temp.commodity.model;
			result[i][5] = String.valueOf(temp.importNum);
			result[i][6] = String.valueOf(temp.importPrice);
			result[i][7] = String.valueOf(temp.totalPrice);
		}
		String[] gdel = (String[]) gooddel.toArray(new String[0]);
		for (int i = gname.length; i < (gdel.length + gname.length); i++) {
			ImpCancel impcancel = (ImpCancel) IOhelper
					.readHelper("File/ImportCancel/" + gdel[i-gname.length]);
			result[i][0] = impcancel.delDate;
			result[i][1] = "退货";
			result[i][2] = impcancel.nameOfSeller;
			result[i][3] = impcancel.comName;
			result[i][4] = impcancel.comModel;
			result[i][5] = String.valueOf(impcancel.cancelNum);
			result[i][6] = String.valueOf(impcancel.importPrice);
			result[i][7] = String.valueOf(impcancel.totalCancelPrice);
		}
		return result;

	}
}
