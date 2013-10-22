package ProjectThree;

import java.awt.Color;
import java.util.ArrayList;

public class FileReader {

	public static ArrayList<Integer> column1 = new ArrayList<Integer>();
	public static ArrayList<Integer> column2 = new ArrayList<Integer>();
	public static ArrayList<Color> column3 = new ArrayList<Color>();

	public static void main(String[] args) {

		FileInput fileIn = new FileInput();
		fileIn.openFile("E:/Eclipse Workspace/AP Comp Scio/src/ProjectThree/PolygonDataFile.txt");

		while (fileIn.hasMoreData()) {
			String newLine = fileIn.readLine();

			if (newLine != null) {

				column1.add(Integer.parseInt(newLine.substring(0,
						newLine.indexOf(','))));
				column2.add(Integer.parseInt(newLine.substring(
						newLine.indexOf(',') + 1, newLine.indexOf(',') + 3)));
				String colorA = newLine.substring(newLine.indexOf(',') + 2,
						newLine.length());
				Color color = new Color(255, 255, 255);

				if (colorA.equals("red")) {
					color = Color.RED;
				}
				if (colorA.equals("blue")) {
					color = Color.BLUE;
				}
				if (colorA.equals("yellow")) {
					color = Color.YELLOW;
				}
				if (colorA.equals("black")) {
					color = Color.BLACK;
				}

				column3.add(color);

			}

		}

	}
}