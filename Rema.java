import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

public class Rema {

	public static void main(String args[]) throws IOException {
		File file = new File("/home/bunooboi/data2.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/home/bunooboi/data.txt", false),
				"UTF-8");

		String line = "";
		while ((line = br.readLine()) != null) {
			String newLine = line.substring(0, getABCIndex(line));
			osw.write(newLine + "\n");
			osw.flush();
		}
		br.close();
		osw.close();

		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		osw = new OutputStreamWriter(new FileOutputStream("/home/bunooboi/answer.txt", false), "UTF-8");

		while ((line = br.readLine()) != null) {
			String newLine = line.substring(getABCIndex(line), line.length());
			osw.write(newLine + "\n");
			osw.flush();
		}
		br.close();
		osw.close();
	}

	static int getABCIndex(String str) {
		char c[] = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (isHalfAlphanumeric(c[i])) {
				return i;
			}
		}
		return -1;
	}

	public static boolean isHalfAlphanumeric(char c) {
		return Pattern.matches("^[a-zA-Z]+$", String.valueOf(c));
	}
}