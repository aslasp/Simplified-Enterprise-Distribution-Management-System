package program;
import java.io.*;
public class ExpCancel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String comName,comModel,nameOfBuyer;
	int cancelNum,exportPrice,totalCancelPrice;
	String delDate;
	public static void expCancelAdd(Export expInfo,int cancelNum) 
			throws IOException, ClassNotFoundException{
		ExpCancel expCancel=new ExpCancel();
		expCancel.comName=expInfo.commodity.name;
		expCancel.comModel=expInfo.commodity.model;
		expCancel.nameOfBuyer=expInfo.nameOfBuyer;
		expCancel.cancelNum=cancelNum;
		expCancel.exportPrice=expInfo.exportPrice;
		expCancel.totalCancelPrice=cancelNum*expCancel.exportPrice;
		expCancel.delDate=IOhelper.dateHelper();
		String filename=
				"File/ExportCancel/"+expCancel.delDate+"-"+expCancel.nameOfBuyer+"-"+expCancel.comName+expCancel.comModel+".ser";
		IOhelper.ADD(expCancel,filename);
		Customer.delToReceive(expCancel.nameOfBuyer,expCancel.totalCancelPrice);
	}
}
