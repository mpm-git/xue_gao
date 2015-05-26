package per.cz.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SVGEdit {

	public SVGEdit() {

	}


	public static void handleEndWhitespace(String fileRPath, String fileWPath) {
		try {
			FileReader reader = new FileReader(fileRPath);
			BufferedReader bfReader = new BufferedReader(reader);
			FileWriter writer = new FileWriter(fileWPath);
			BufferedWriter bfwriter = new BufferedWriter(writer);
			String text = null;
			while ((text = bfReader.readLine()) != null) {
				text = text.trim();
				bfwriter.write(text);
				bfwriter.write("\r");
				bfwriter.write("\n");
				bfwriter.flush();
			}
			bfReader.close();
			bfwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void handle_Tab_ENTER(String fileRPath, String fileWPath) {
		try {
			FileReader reader = new FileReader(fileRPath);
			BufferedReader bfReader = new BufferedReader(reader);
			FileWriter writer = new FileWriter(fileWPath);
			BufferedWriter bfwriter = new BufferedWriter(writer);
			String text = null;
			while ((text = bfReader.readLine()) != null) {
				text = text.trim();
				text.replaceAll("\\s", " ");
				text.replaceAll("\"", "\'");
				bfwriter.write(text);
				bfwriter.write("\t");
				bfwriter.flush();
			}
			bfReader.close();
			bfwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String file="D:\\tomcat-5.5.23\\webapps\\Flex4Diy\\temp\\2012-10-18\\121018010431984.svg";
		String filePath=new File(file).getParent();
		String fileName=new File(file).getName();
//		System.out.println(s);
		String s="\"afsdfds\"";
		System.out.println(s.replaceAll("\"", "&&"));
		handle_Tab_ENTER(file,filePath+File.separator+"Handler"+fileName);
//		handle_Tab_ENTER(args[0],System.getProperty("user.dir")+File.separator+"Handler"+s);
	}
}
