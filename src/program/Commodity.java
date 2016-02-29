//已完成，最后一次查错：2014.5.5
package program;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Commodity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name, model;
	int num, default_importPrice, default_exportPrice, latest_importPrice,
			latest_exportPrice;
	int impLastLatest, expLastLatest;

	// 增
	public static void commodityAdd(String n, String m, int dpp, int dsp)
			throws IOException, ClassNotFoundException {
		Commodity commodity = new Commodity();
		commodity.name = n;
		commodity.model = m;
		commodity.default_importPrice = dpp;
		commodity.default_exportPrice = dsp;
		IOhelper.ADD(commodity, "File/Commodity/" + commodity.name
				+ commodity.model + ".ser");
		Good.addGood(commodity.name, commodity.model);
	}

	// 只能修改default-importPrice,default-exportPrice
	public static void commodityUpd(String name, String model,
			int default_purchasePrice, int default_sellingPrice)
			throws IOException, ClassNotFoundException {
		String fileToDel = "File/Commodity/" + name + model + ".ser";
		// 读取旧版商品对象，获得num，latest-importPrice，latest-importPrice
		Commodity temp = (Commodity) IOhelper.readHelper(fileToDel);
		temp.default_importPrice = default_purchasePrice;
		temp.default_exportPrice = default_sellingPrice;
		// 现在，temp对象就是更新过的对象
		IOhelper.UPD(fileToDel, temp, fileToDel);
	}

	// 删
	public static String commodityDel(String name, String model)
			throws IOException, ClassNotFoundException {
		String fileToDel = "File/Commodity/" + name + model + ".ser";
		// 检查这个商品是否曾经被进货
		File importFolder = new File("File/Import/");
		String[] importTest = importFolder.list();
		boolean hasImport = false;
		for (int i = 0; i < importTest.length; i++) {
			Import fileToCheck = (Import) IOhelper.readHelper("File/Import/"
					+ importTest[i]);
			if ((fileToCheck.commodity.name.equals(name))
					&& (fileToCheck.commodity.model.equals(model))) {
				hasImport = true;
				break;
			}
		}
		// 检查完毕
		if (hasImport)
			return "IMPORTED";
		else {
			IOhelper.DEL(fileToDel);
			IOhelper.DEL("File/Stock/" + name + model + ".ser");
			return "OK";
		}
	}

	public static String[][] commodityFin(String name, String model)
			throws ClassNotFoundException, IOException {
//		String filename = "File/Commodity/" + name + model + ".ser";
//		File fileToFind = new File(filename);
//		String[][] result = new String[1][7];
//		if (fileToFind.exists()) {
//			System.out.println("exist");
//			Commodity temp = (Commodity) IOhelper.readHelper(filename);
//			result[0][0] = temp.name;
//			result[0][1] = temp.model;
//			result[0][2] = String.valueOf(temp.num);
//			result[0][3] = String.valueOf(temp.default_importPrice);
//			result[0][4] = String.valueOf(temp.default_exportPrice);
//			result[0][5] = String.valueOf(temp.latest_importPrice);
//			result[0][6] = String.valueOf(temp.latest_exportPrice);
//			return result;
//		} else {
//			String[][] no = null;
//			return no;
//		}
		File folder = new File("File/Commodity/");
		String[] namelist = folder.list();
		ArrayList<String> goodname=new ArrayList<String>();
		for (int i = 0; i < namelist.length; i++) {
			if (namelist[i].indexOf(name+model) != -1) {
				goodname.add(namelist[i]);
			}
		}
		String[] toshow=(String[])goodname.toArray(new String[0]);
		String[][] result=new String[toshow.length][7];
		for (int i = 0; i < toshow.length; i++) {
			Commodity temp = (Commodity) IOhelper.readHelper("File/Commodity/"
					+ toshow[i]);
			result[i][0] = temp.name;
			result[i][1] = temp.model;
			result[i][2] = String.valueOf(temp.num);
			result[i][3] = String.valueOf(temp.default_importPrice);
			result[i][4] = String.valueOf(temp.default_exportPrice);
			result[i][5] = String.valueOf(temp.latest_importPrice);
			result[i][6] = String.valueOf(temp.latest_exportPrice);
		}
		return result;
	}

	public static String[][] commoditynameFin(String sname) throws ClassNotFoundException, IOException {
		File folder = new File("File/Commodity/");
		String[] namelist = folder.list();
		ArrayList<String> goodname=new ArrayList<String>();
		for (int i = 0; i < namelist.length; i++) {
			if (namelist[i].indexOf(sname) != -1) {
				goodname.add(namelist[i]);
			}
		}
		String[] toshow=(String[])goodname.toArray(new String[0]);
		String[][] result=new String[toshow.length][7];
		for (int i = 0; i < toshow.length; i++) {
			Commodity temp = (Commodity) IOhelper.readHelper("File/Commodity/"
					+ toshow[i]);
			result[i][0] = temp.name;
			result[i][1] = temp.model;
			result[i][2] = String.valueOf(temp.num);
			result[i][3] = String.valueOf(temp.default_importPrice);
			result[i][4] = String.valueOf(temp.default_exportPrice);
			result[i][5] = String.valueOf(temp.latest_importPrice);
			result[i][6] = String.valueOf(temp.latest_exportPrice);
		}
		return result;
	}

	public static String[][] commoditySho() throws IOException,
			ClassNotFoundException {
		File commodityFolder = new File("File/Commodity/");
		String[] test = commodityFolder.list();
		String[][] result = new String[test.length][7];
		for (int i = 0; i < test.length; i++) {
			Commodity temp = (Commodity) IOhelper.readHelper("File/Commodity/"
					+ test[i]);
			result[i][0] = temp.name;
			result[i][1] = temp.model;
			result[i][2] = String.valueOf(temp.num);
			result[i][3] = String.valueOf(temp.default_importPrice);
			result[i][4] = String.valueOf(temp.default_exportPrice);
			result[i][5] = String.valueOf(temp.latest_importPrice);
			result[i][6] = String.valueOf(temp.latest_exportPrice);
		}
		return result;
	}
	public static String[] getComNames() throws ClassNotFoundException, IOException{
		File commodityFolder = new File("File/Commodity/");
		String[] test = commodityFolder.list();
		String[] result = new String[test.length];
		for(int i=0;i<test.length;i++){
			Commodity temp = (Commodity) IOhelper.readHelper("File/Commodity/"
					+ test[i]);
			result[i]=temp.name;
		}
		ArrayList<String> toReturn=new ArrayList<String>();
		toReturn.add("请选择商品名");
		for(int i=0;i<result.length;i++){
			boolean repeat=false;
			for(int j=0;j<toReturn.size();j++){
				if(toReturn.get(j).equals(result[i])){
					repeat=true;
					break;
				}
			}
			if(!repeat)
				toReturn.add(result[i]);
		}
		return (String[])toReturn.toArray(new String[0]);
	}
	public static String[] getModel(String name){
		File commodityFolder = new File("File/Commodity/");
		String[] test = commodityFolder.list();
		ArrayList<String> models=new ArrayList<String>();
		
		for(int i=0;i<test.length;i++){
			if(test[i].indexOf(name)!=-1){
				test[i]=test[i].replace(name,"");
				test[i]=test[i].replace(".ser", "");
				models.add(test[i]);
			}
		}
        models.add(0,"请选择型号");
		String[] result=(String[])models.toArray(new String[0]);
		return result;	
	}
}
