package DrJQuizRandoms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionRandomIzer {

	public static List<String> questions = new ArrayList<String>();
	public static List<String> answers = new ArrayList<String>();
	public static String keyCode;
	public static int mainRan;

	public static void main(String[] args) {

		FileInput fileIn = new FileInput();
		fileIn.openFile("E:/Eclipse Workspace/AP Comp Scio/src/DrJQuizRandoms/Questions");

		while (fileIn.hasMoreData()) {
			String newLine = fileIn.readLine();

			if (newLine != null) {
				questions.add(newLine.substring(newLine.indexOf(':') + 1,
						newLine.length()));
				answers.add(newLine.substring(0, newLine.indexOf(':')));

			}

		}

		int[] positions = new int[questions.size()];

		for (int i = 0; i < positions.length; i++) {

			positions[i] = i;

		}

		mainRan = (int)(Math.random() * questions.size()) + 1;

		shuffleArray(positions);

		questions(positions);
		answers(positions);

		System.out.println(keyCode);

	}

	private static void questions(int[] p) {
		try {
			File file = new File("questions.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));

			int keyCodeInt = questions.get(mainRan).hashCode();
			keyCode = String.valueOf(keyCodeInt);

			output.write(keyCode);

			output.newLine();

			int count = 0;

			for (String s : answers) {

				output.write(s);
				output.write("     ");
				count++;
				if (count % 4 == 0) {
					output.newLine();
				}

			}
			output.newLine();
			output.newLine();

			for (int i = 0; i < p.length; i++) {
				output.write(String.valueOf(i + 1));
				output.write(".____________________________________");
				output.write(questions.get(p[i]));
				output.newLine();
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private static void answers(int[] p) {
		try {
			File file = new File("answers.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));

			int keyCodeInt = questions.get(mainRan).hashCode();
			keyCode = String.valueOf(keyCodeInt);

			output.write(keyCode);

			output.newLine();

			for (int i = 0; i < p.length; i++) {
				output.write(String.valueOf(i + 1));
				output.write(". ");
				output.write(answers.get(p[i]));
				output.newLine();
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	static void shuffleArray(int[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);

			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
}
