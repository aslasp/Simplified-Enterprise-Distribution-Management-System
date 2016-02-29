package program;

import java.io.*;
import java.util.ArrayList;

public class Export implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Commodity commodity;
	String nameOfBuyer;
	int exportNum, exportPrice, totalPrice;
	String addDate;
	int realExpNum;

	public static void exportAdd(String nob, String name, String model, int eN,
			int eP) throws IOException, ClassNotFoundException {
		Export exp = new Export();
		String commodityFile = "File/Commodity/" + name + model + ".ser";
		// 初始化对象，输入name和model来反序列化指定对象
		exp.commodity = (Commodity) IOhelper.readHelper(commodityFile);
		exp.commodity.expLastLatest = exp.commodity.latest_exportPrice;
		exp.commodity.latest_exportPrice = eP; // 获取最近销售价格
		exp.commodity.num = exp.commodity.num - eN; // 减少商品数量
		IOhelper.UPD(commodityFile, exp.commodity, commodityFile);
		// 保存Commodity对象的最新状态
		exp.nameOfBuyer = nob;
		exp.totalPrice = eN * eP;
		exp.exportNum = exp.realExpNum = eN;
		exp.exportPrice = eP;
		exp.addDate = IOhelper.dateHelper();
		String filename = "File/Export/"+exp.addDate+"-" + exp.nameOfBuyer + "-"
				+ exp.commodity.name + exp.commodity.model + ".ser";
		IOhelper.ADD(exp, filename);
		Customer.addToReceive(nob, exp.totalPrice);
	}

	public static void exportDel(String adddate,String nob, String cname, String cmodel)
			throws IOException, ClassNotFoundException {
		// 退货不删进货单，而是创建一个退货单文件，并且修改客户应付金额
		String filename = "File/Export/"+adddate+"-" + nob + "-" + cname + cmodel + ".ser";
		Export temp = (Export) IOhelper.readHelper(filename);
		int cancelNum = temp.exportNum;
		ExpCancel.expCancelAdd(temp, cancelNum);
		// 因为退货，对应Commodity的数量也将增加
		temp.commodity.num = temp.commodity.num + cancelNum;
		temp.commodity.latest_exportPrice = temp.commodity.expLastLatest;
		temp.realExpNum = temp.realExpNum - cancelNum;
		IOhelper.UPD("File/Commodity/" + cname + cmodel + ".ser",
				temp.commodity, "File/Commodity/" + cname + cmodel + ".ser");
		IOhelper.UPD(filename, temp, filename);
	}
	public static String[][] exportSho(String fromDate, String toDate)
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
		File expHolder = new File("File/Export/");
		String[] expTest = expHolder.list();
		File expCancelFolder = new File("File/ExportCancel/");
		String[] cancelTest = expCancelFolder.list();
		ArrayList<String> goodname = new ArrayList<String>();
		for (int i = 0; i < expTest.length; i++) {
			Export temp = (Export) IOhelper.readHelper("File/Export/"
					+ expTest[i]);
			//
			String[] datesplit=temp.addDate.split("-");
			int year=Integer.parseInt(datesplit[0]);
			int month=Integer.parseInt(datesplit[1]);
			int day=Integer.parseInt(datesplit[2]);
			//
			if((fromY<year)&&(year<toY))
				goodname.add(expTest[i]);
			if((fromY==year)||(year==toY)){
				if((fromM<month)&&(month<toM))
					goodname.add(expTest[i]);
				if((fromM==month)||(month==toM)){
					if((fromD<=day)&&(day<=toD))
						goodname.add(expTest[i]);
				}
			}
		}
		ArrayList<String> gooddel = new ArrayList<String>();
		for (int i = 0; i < cancelTest.length; i++) {
			ExpCancel temp = (ExpCancel) IOhelper
					.readHelper("File/ExportCancel/" + cancelTest[i]);
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
			Export temp = (Export) IOhelper.readHelper("File/Export/"
					+ gname[i]);
			result[i][0] = temp.addDate;
			result[i][1] = "销售";
			result[i][2] = temp.nameOfBuyer;
			result[i][3] = temp.commodity.name;
			result[i][4] = temp.commodity.model;
			result[i][5] = String.valueOf(temp.exportNum);
			result[i][6] = String.valueOf(temp.exportPrice);
			result[i][7] = String.valueOf(temp.totalPrice);
		}
		String[] gdel = (String[]) gooddel.toArray(new String[0]);
		for (int i = gname.length; i < (gdel.length + gname.length); i++) {
			ExpCancel expcancel = (ExpCancel) IOhelper
					.readHelper("File/ExportCancel/" + gdel[i-gname.length]);
			result[i][0] = expcancel.delDate;
			result[i][1] = "退货";
			result[i][2] = expcancel.nameOfBuyer;
			result[i][3] = expcancel.comName;
			result[i][4] = expcancel.comModel;
			result[i][5] = String.valueOf(expcancel.cancelNum);
			result[i][6] = String.valueOf(expcancel.exportPrice);
			result[i][7] = String.valueOf(expcancel.totalCancelPrice);
		}
		return result;

	}
}
