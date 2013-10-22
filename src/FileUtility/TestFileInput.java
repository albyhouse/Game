package FileUtility;

import java.util.ArrayList;

public class TestFileInput {

	public static void main(String[] args) {

		FileInput fileIn = new FileInput();
		fileIn.openFile("E:/Eclipse Workspace/AP Comp Scio/src/FileUtility/FileInputTestFile.txt");

		ArrayList<String> column1 = new ArrayList<String>();
		ArrayList<String> column2 = new ArrayList<String>();
		ArrayList<String> column3 = new ArrayList<String>();

		while (fileIn.hasMoreData()) {

			String newLine = fileIn.readLine();

			if (newLine != null) {
				column1.add(newLine.substring(0, newLine.indexOf(',')));
				column2.add(newLine.substring(newLine.indexOf(',')+1,
						newLine.indexOf(',')+2));
				column3.add(newLine.substring(newLine.indexOf(',')+3,
						newLine.length()));
			}

		}

		for (String s : column1) {
			System.out.println(s);

		}

		for (String s : column2) {
			System.out.println(s);

		}

		for (String s : column3) {
			System.out.println(s);

		}

	}
}
