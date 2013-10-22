package ProjectTwo;

import static org.lwjgl.opengl.GL11.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class ProjectTwo {

	private static List<Triangle> triangles = new ArrayList<Triangle>(16);
	private static List<Square> squares = new ArrayList<Square>(16);

	private float colorRed, colorBlue, colorGreen;
	private int counter;
	private boolean counterBool = true;
	private String area, perimeter, triangleArea, trianglePerim;
	private float redBlack = 1.0f;
	private float blueBlack = 1.0f;
	private float greenBlack = 1.0f;

	public ProjectTwo() {

		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Project Two");
			Display.create();

		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		// initialization code
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 600, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		while (!Display.isCloseRequested()) {
			// Render

			glClear(GL_COLOR_BUFFER_BIT);

			instructions();

			randomFloatGenerator();
			keyCheck();
			mouseCheck();

			draw();

			Display.update();
			Display.sync(60);

		}

		Display.destroy();
	}

	public static void main(String[] args) {
		new ProjectTwo();

	}

	void instructions() {
		glColor3f(redBlack, blueBlack, greenBlack);
		drawString("PRESS T FOR TRIANGLES", 200, 20);
		drawString("PRESS S FOR SQUARES", 200, 35);
		drawString("HOLD DOWN THE LEFT OR RIGHT MOUSE BUTTON", 200, 50);
		drawString("PRESS SPACE TO CLEAR", 200, 65);
		redBlack -= .004;
		blueBlack -= .004;
		greenBlack -= .004;

	}

	void keyCheck() {
		if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
			Triangle triangle = new Triangle(Mouse.getX(), 600 - Mouse.getY(),
					100, colorRed, colorBlue, colorGreen);
			triangles.add(triangle);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			Square square = new Square(Mouse.getX(), 600 - Mouse.getY(), 100,
					colorRed, colorBlue, colorGreen);
			squares.add(square);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			triangles.clear();
			squares.clear();
		}

	}

	void mouseCheck() {
		if (Mouse.isButtonDown(0)) {
			Square square = new Square(Mouse.getX(), 600 - Mouse.getY(),
					75 + counter, colorRed, colorBlue, colorGreen);
			square.drawDilate();
			area = String.valueOf(square.area());
			perimeter = String.valueOf(square.perimeter());
		}

		if (Mouse.isButtonDown(1)) {
			Triangle triangle = new Triangle(Mouse.getX(), 600 - Mouse.getY(),
					75 + counter, colorRed, colorBlue, colorGreen);
			triangle.draw();
			double triangleAreaNum = round(triangle.area(),1);
			triangleArea = String.valueOf(triangleAreaNum);
			trianglePerim = String.valueOf(triangle.perimeter());

			glColor3f(1.0f - colorRed, 1.0f - colorBlue, 1.0f - colorGreen);
			glBegin(GL_LINES);
			glVertex2i(22 + 700, 15);
			glVertex2i(30 + 700, 15);
			glEnd();
			glBegin(GL_LINES);
			glVertex2i(22 + 700, 18);
			glVertex2i(30 + 700, 18);
			glEnd();
			drawString("A ", 10 + 700, 20);
			drawString(triangleArea, 35 + 700, 20);
			drawString(trianglePerim, 35 + 700, 35);
			drawString("P ", 10 + 700, 35);
			glBegin(GL_LINES);
			glVertex2i(22 + 700, 30);
			glVertex2i(30 + 700, 30);
			glEnd();
			glBegin(GL_LINES);
			glVertex2i(22 + 700, 33);
			glVertex2i(30 + 700, 33);
			glEnd();

		}

		if (Mouse.isButtonDown(0)) {
			glColor3f(1.0f - colorRed, 1.0f - colorBlue, 1.0f - colorGreen);
			glBegin(GL_LINES);
			glVertex2i(22, 15);
			glVertex2i(30, 15);
			glEnd();
			glBegin(GL_LINES);
			glVertex2i(22, 18);
			glVertex2i(30, 18);
			glEnd();
			drawString("A ", 10, 20);
			drawString(area, 35, 20);
			drawString(perimeter, 35, 35);
			drawString("P ", 10, 35);
			glBegin(GL_LINES);
			glVertex2i(22, 30);
			glVertex2i(30, 30);
			glEnd();
			glBegin(GL_LINES);
			glVertex2i(22, 33);
			glVertex2i(30, 33);
			glEnd();

		}

		if (counterBool) {
			counter += 3;
		}
		if (!counterBool) {
			counter -= 3;
		}

		if (counter >= 225) {
			counterBool = false;

		}

		if (counter <= 0) {
			counterBool = true;

		}

	}

	public static double round(double d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	void randomFloatGenerator() {
		Random randomGenerator = new Random();
		colorRed = randomGenerator.nextFloat();
		colorBlue = randomGenerator.nextFloat();
		colorGreen = randomGenerator.nextFloat();
	}

	void draw() {
		for (Triangle t : triangles) {
			t.draw();
		}

		for (Square s : squares) {
			s.draw();
		}

	}

	public static void drawString(String s, int x, int y) {
		int startX = x;
		GL11.glBegin(GL11.GL_POINTS);
		for (char c : s.toLowerCase().toCharArray()) {
			if (c == 'a') {
				for (int i = 0; i < 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y - 4);
				}
				x += 8;
			} else if (c == 'b') {
				for (int i = 0; i < 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 1; i <= 6; i++) {
					GL11.glVertex2f(x + i, y);
					GL11.glVertex2f(x + i, y - 4);
					GL11.glVertex2f(x + i, y - 8);
				}
				GL11.glVertex2f(x + 7, y - 5);
				GL11.glVertex2f(x + 7, y - 7);
				GL11.glVertex2f(x + 7, y - 6);
				GL11.glVertex2f(x + 7, y - 1);
				GL11.glVertex2f(x + 7, y - 2);
				GL11.glVertex2f(x + 7, y - 3);
				x += 8;
			} else if (c == 'c') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y);
					GL11.glVertex2f(x + i, y - 8);
				}
				GL11.glVertex2f(x + 6, y - 1);
				GL11.glVertex2f(x + 6, y - 2);

				GL11.glVertex2f(x + 6, y - 6);
				GL11.glVertex2f(x + 6, y - 7);

				x += 8;
			} else if (c == 'd') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y);
					GL11.glVertex2f(x + i, y - 8);
				}
				GL11.glVertex2f(x + 6, y - 1);
				GL11.glVertex2f(x + 6, y - 2);
				GL11.glVertex2f(x + 6, y - 3);
				GL11.glVertex2f(x + 6, y - 4);
				GL11.glVertex2f(x + 6, y - 5);
				GL11.glVertex2f(x + 6, y - 6);
				GL11.glVertex2f(x + 6, y - 7);

				x += 8;
			} else if (c == 'e') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 1; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 0);
					GL11.glVertex2f(x + i, y - 8);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 4);
				}
				x += 8;
			} else if (c == 'f') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 1; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 8);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 4);
				}
				x += 8;
			} else if (c == 'g') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y);
					GL11.glVertex2f(x + i, y - 8);
				}
				GL11.glVertex2f(x + 6, y - 1);
				GL11.glVertex2f(x + 6, y - 2);
				GL11.glVertex2f(x + 6, y - 3);
				GL11.glVertex2f(x + 5, y - 3);
				GL11.glVertex2f(x + 7, y - 3);

				GL11.glVertex2f(x + 6, y - 6);
				GL11.glVertex2f(x + 6, y - 7);

				x += 8;
			} else if (c == 'h') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 4);
				}
				x += 8;
			} else if (c == 'i') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 3, y - i);
				}
				for (int i = 1; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 0);
					GL11.glVertex2f(x + i, y - 8);
				}
				x += 7;
			} else if (c == 'j') {
				for (int i = 1; i <= 8; i++) {
					GL11.glVertex2f(x + 6, y - i);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 0);
				}
				GL11.glVertex2f(x + 1, y - 3);
				GL11.glVertex2f(x + 1, y - 2);
				GL11.glVertex2f(x + 1, y - 1);
				x += 8;
			} else if (c == 'k') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				GL11.glVertex2f(x + 6, y - 8);
				GL11.glVertex2f(x + 5, y - 7);
				GL11.glVertex2f(x + 4, y - 6);
				GL11.glVertex2f(x + 3, y - 5);
				GL11.glVertex2f(x + 2, y - 4);
				GL11.glVertex2f(x + 2, y - 3);
				GL11.glVertex2f(x + 3, y - 4);
				GL11.glVertex2f(x + 4, y - 3);
				GL11.glVertex2f(x + 5, y - 2);
				GL11.glVertex2f(x + 6, y - 1);
				GL11.glVertex2f(x + 7, y);
				x += 8;
			} else if (c == 'l') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 1; i <= 6; i++) {
					GL11.glVertex2f(x + i, y);
				}
				x += 7;
			} else if (c == 'm') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				GL11.glVertex2f(x + 3, y - 6);
				GL11.glVertex2f(x + 2, y - 7);
				GL11.glVertex2f(x + 4, y - 5);

				GL11.glVertex2f(x + 5, y - 6);
				GL11.glVertex2f(x + 6, y - 7);
				GL11.glVertex2f(x + 4, y - 5);
				x += 8;
			} else if (c == 'n') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				GL11.glVertex2f(x + 2, y - 7);
				GL11.glVertex2f(x + 2, y - 6);
				GL11.glVertex2f(x + 3, y - 5);
				GL11.glVertex2f(x + 4, y - 4);
				GL11.glVertex2f(x + 5, y - 3);
				GL11.glVertex2f(x + 6, y - 2);
				GL11.glVertex2f(x + 6, y - 1);
				x += 8;
			} else if (c == 'o' || c == '0') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y - 0);
				}
				x += 8;
			} else if (c == 'p') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y - 4);
				}
				GL11.glVertex2f(x + 6, y - 7);
				GL11.glVertex2f(x + 6, y - 5);
				GL11.glVertex2f(x + 6, y - 6);
				x += 8;
			} else if (c == 'q') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
					if (i != 1)
						GL11.glVertex2f(x + 7, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 8);
					if (i != 6)
						GL11.glVertex2f(x + i, y - 0);
				}
				GL11.glVertex2f(x + 4, y - 3);
				GL11.glVertex2f(x + 5, y - 2);
				GL11.glVertex2f(x + 6, y - 1);
				GL11.glVertex2f(x + 7, y);
				x += 8;
			} else if (c == 'r') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y - 4);
				}
				GL11.glVertex2f(x + 6, y - 7);
				GL11.glVertex2f(x + 6, y - 5);
				GL11.glVertex2f(x + 6, y - 6);

				GL11.glVertex2f(x + 4, y - 3);
				GL11.glVertex2f(x + 5, y - 2);
				GL11.glVertex2f(x + 6, y - 1);
				GL11.glVertex2f(x + 7, y);
				x += 8;
			} else if (c == 's') {
				for (int i = 2; i <= 7; i++) {
					GL11.glVertex2f(x + i, y - 8);
				}
				GL11.glVertex2f(x + 1, y - 7);
				GL11.glVertex2f(x + 1, y - 6);
				GL11.glVertex2f(x + 1, y - 5);
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 4);
					GL11.glVertex2f(x + i, y);
				}
				GL11.glVertex2f(x + 7, y - 3);
				GL11.glVertex2f(x + 7, y - 2);
				GL11.glVertex2f(x + 7, y - 1);
				GL11.glVertex2f(x + 1, y - 1);
				GL11.glVertex2f(x + 1, y - 2);
				x += 8;
			} else if (c == 't') {
				for (int i = 0; i <= 8; i++) {
					GL11.glVertex2f(x + 4, y - i);
				}
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + i, y - 8);
				}
				x += 7;
			} else if (c == 'u') {
				for (int i = 1; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 0);
				}
				x += 8;
			} else if (c == 'v') {
				for (int i = 2; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 6, y - i);
				}
				GL11.glVertex2f(x + 2, y - 1);
				GL11.glVertex2f(x + 5, y - 1);
				GL11.glVertex2f(x + 3, y);
				GL11.glVertex2f(x + 4, y);
				x += 7;
			} else if (c == 'w') {
				for (int i = 1; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				GL11.glVertex2f(x + 2, y);
				GL11.glVertex2f(x + 3, y);
				GL11.glVertex2f(x + 5, y);
				GL11.glVertex2f(x + 6, y);
				for (int i = 1; i <= 6; i++) {
					GL11.glVertex2f(x + 4, y - i);
				}
				x += 8;
			} else if (c == 'x') {
				for (int i = 1; i <= 7; i++)
					GL11.glVertex2f(x + i, y - i);
				for (int i = 7; i >= 1; i--)
					GL11.glVertex2f(x + i, y - 8 - i);
				x += 8;
			} else if (c == 'y') {
				GL11.glVertex2f(x + 4, y);
				GL11.glVertex2f(x + 4, y - 1);
				GL11.glVertex2f(x + 4, y - 2);
				GL11.glVertex2f(x + 4, y - 3);
				GL11.glVertex2f(x + 4, y - 4);

				GL11.glVertex2f(x + 3, y - 5);
				GL11.glVertex2f(x + 2, y - 6);
				GL11.glVertex2f(x + 1, y - 7);
				GL11.glVertex2f(x + 1, y - 8);

				GL11.glVertex2f(x + 5, y - 5);
				GL11.glVertex2f(x + 6, y - 6);
				GL11.glVertex2f(x + 7, y - 7);
				GL11.glVertex2f(x + 7, y - 8);
				x += 8;
			} else if (c == 'z') {
				for (int i = 1; i <= 6; i++) {
					GL11.glVertex2f(x + i, y);
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y - i);
				}
				GL11.glVertex2f(x + 6, y - 7);
				x += 8;
			} else if (c == '1') {
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y);
				}
				for (int i = 1; i <= 8; i++) {
					GL11.glVertex2f(x + 4, y - i);
				}
				GL11.glVertex2f(x + 3, y - 7);
				x += 8;
			} else if (c == '2') {
				for (int i = 1; i <= 6; i++) {
					GL11.glVertex2f(x + i, y);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 8);
				}
				GL11.glVertex2f(x + 1, y - 7);
				GL11.glVertex2f(x + 1, y - 6);

				GL11.glVertex2f(x + 6, y - 7);
				GL11.glVertex2f(x + 6, y - 6);
				GL11.glVertex2f(x + 6, y - 5);
				GL11.glVertex2f(x + 5, y - 4);
				GL11.glVertex2f(x + 4, y - 3);
				GL11.glVertex2f(x + 3, y - 2);
				GL11.glVertex2f(x + 2, y - 1);
				x += 8;
			} else if (c == '3') {
				for (int i = 1; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y);
				}
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 6, y - i);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 4);
				}
				x += 8;
			} else if (c == '4') {
				for (int i = 2; i <= 8; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 7; i++) {
					GL11.glVertex2f(x + i, y - 1);
				}
				for (int i = 0; i <= 4; i++) {
					GL11.glVertex2f(x + 4, y - i);
				}
				x += 8;
			} else if (c == '5') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + i, y - 8);
				}
				for (int i = 4; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				GL11.glVertex2f(x + 1, y - 1);
				GL11.glVertex2f(x + 2, y);
				GL11.glVertex2f(x + 3, y);
				GL11.glVertex2f(x + 4, y);
				GL11.glVertex2f(x + 5, y);
				GL11.glVertex2f(x + 6, y);

				GL11.glVertex2f(x + 7, y - 1);
				GL11.glVertex2f(x + 7, y - 2);
				GL11.glVertex2f(x + 7, y - 3);

				GL11.glVertex2f(x + 6, y - 4);
				GL11.glVertex2f(x + 5, y - 4);
				GL11.glVertex2f(x + 4, y - 4);
				GL11.glVertex2f(x + 3, y - 4);
				GL11.glVertex2f(x + 2, y - 4);
				x += 8;
			} else if (c == '6') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y);
				}
				for (int i = 2; i <= 5; i++) {
					GL11.glVertex2f(x + i, y - 4);
					GL11.glVertex2f(x + i, y - 8);
				}
				GL11.glVertex2f(x + 7, y - 1);
				GL11.glVertex2f(x + 7, y - 2);
				GL11.glVertex2f(x + 7, y - 3);
				GL11.glVertex2f(x + 6, y - 4);
				x += 8;
			} else if (c == '7') {
				for (int i = 0; i <= 7; i++)
					GL11.glVertex2f(x + i, y - 8);
				GL11.glVertex2f(x + 7, y - 7);
				GL11.glVertex2f(x + 7, y - 6);

				GL11.glVertex2f(x + 6, y - 5);
				GL11.glVertex2f(x + 5, y - 4);
				GL11.glVertex2f(x + 4, y - 3);
				GL11.glVertex2f(x + 3, y - 2);
				GL11.glVertex2f(x + 2, y - 1);
				GL11.glVertex2f(x + 1, y);
				x += 8;
			} else if (c == '8') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
					GL11.glVertex2f(x + 7, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y - 0);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 4);
				}
				x += 8;
			} else if (c == '9') {
				for (int i = 1; i <= 7; i++) {
					GL11.glVertex2f(x + 7, y - i);
				}
				for (int i = 5; i <= 7; i++) {
					GL11.glVertex2f(x + 1, y - i);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 8);
					GL11.glVertex2f(x + i, y - 0);
				}
				for (int i = 2; i <= 6; i++) {
					GL11.glVertex2f(x + i, y - 4);
				}
				GL11.glVertex2f(x + 1, y - 0);
				x += 8;
			} else if (c == '.') {
				GL11.glVertex2f(x + 1, y);
				x += 2;
			} else if (c == ',') {
				GL11.glVertex2f(x + 1, y);
				GL11.glVertex2f(x + 1, y - 1);
				x += 2;
			} else if (c == '\n') {
				y -= 10;
				x = startX;
			} else if (c == ' ') {
				x += 8;
			}
		}
		GL11.glEnd();
	}

}
