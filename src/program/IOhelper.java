package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class IOhelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void fileWrite(String filename, String content)
			throws IOException {
		File file = new File(filename);
		if (!file.exists())
			file.createNewFile();
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.close();
	}

	public static String[] fileRead(String filename) throws IOException {
		File file = new File(filename);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		ArrayList<String> result = new ArrayList<String>();
		while ((line = br.readLine()) != null)
			result.add(line);
		if (result.size() != 0)
			return (String[]) result.toArray(new String[0]);
		else {
			String[] blank = { "目前没有记录" };
			return blank;
		}
	}

	public static void fileUpd(String filename, String content)
			throws IOException {
		IOhelper.DEL(filename);
		IOhelper.fileWrite(filename, content);
	}

	public static Object readHelper(String filename)
			throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object temp = ois.readObject();
		ois.close();
		return temp;
	}

	public static void writeHelper(Object obj, String filename)
			throws ClassNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
	}

	public static void ADD(Object sth, String filename) throws IOException,
			ClassNotFoundException {
		File file = new File(filename);
		if (!file.exists())
			file.createNewFile();
		IOhelper.writeHelper(sth, filename);
	}

	public static void DEL(String fileToDel) {
		File n = new File(fileToDel);
		if (n.exists())
			n.delete();
	}

	public static void UPD(String fileToDel, Object obj, String filename)
			throws IOException, ClassNotFoundException {
		IOhelper.DEL(fileToDel);
		IOhelper.ADD(obj, filename);
	}

	public static String dateHelper() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int minute = ca.get(Calendar.MINUTE);
		int second = ca.get(Calendar.SECOND);
		String nowDate = year + "-" + month + "-" + day + "-" + hour + "-"
				+ minute + "-" + second;
		return nowDate;
	}

}
