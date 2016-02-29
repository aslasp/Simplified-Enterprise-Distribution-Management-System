package program;
import java.io.*;

public class ImpCancel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String comName,comModel,nameOfSeller;
	int cancelNum,importPrice,totalCancelPrice;
	String delDate;

	public static void impCancelAdd(Import impInfo,int cancelNum) 
			throws IOException, ClassNotFoundException{
		ImpCancel impCancel=new ImpCancel();
		impCancel.comName=impInfo.commodity.name;
		impCancel.comModel=impInfo.commodity.model;
		impCancel.nameOfSeller=impInfo.nameOfSeller;
		impCancel.cancelNum=cancelNum;
		impCancel.importPrice=impInfo.importPrice;
		impCancel.totalCancelPrice=cancelNum*impCancel.importPrice;
		impCancel.delDate=IOhelper.dateHelper();
		String filename=
				"File/ImportCancel/"+impCancel.delDate+"-"+impCancel.nameOfSeller+"-"+impCancel.comName+impCancel.comModel+".ser";
		IOhelper.ADD(impCancel,filename);
		Customer.delToPay(impCancel.nameOfSeller,impCancel.totalCancelPrice);
	}
}
