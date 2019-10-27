package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class Log {
	
	private static String fileName;
	
	public Log(String fileName) {
		this.fileName=fileName;
	}
	
	public static void write(String msg) {
		try {
			Date date = new Date();
			String time = date.toLocaleString();
			
			BufferedWriter stdout = new BufferedWriter(new FileWriter(fileName, true));
			stdout.write(time);
			stdout.write("\t");
			stdout.write(msg);
			//stdout.newLine();
			
			stdout.flush();
			stdout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
