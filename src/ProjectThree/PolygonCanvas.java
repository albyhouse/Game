package ProjectThree;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class PolygonCanvas {

	public static ArrayList<Integer> polySides = new ArrayList<Integer>();
	public static ArrayList<Integer> polySideLength = new ArrayList<Integer>();
	public static ArrayList<Color> polycolor = new ArrayList<Color>();

	private static ArrayList<PolygonData> polygonData = new ArrayList<PolygonData>();

	private float liner = 1.0f;
	private float lineb = 1.0f;
	private float lineg = 1.0f;

	private int w = 1200;
	private int h = 800;

	private String sort = "nothing";

	public PolygonCanvas() {
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			Display.setTitle("Hello World");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, w, h, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);

			bg();
			buttons();
			mouseCheck();

			Polygon.draw(100, 9, 100, 100, Color.red);

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	public static void main(String[] args) {
		new PolygonCanvas();

	}

	public void bg() {
		glColor3f(.3f, .3f, .3f);
		glBegin(GL_QUADS);
		glVertex2i(0, 0);
		glVertex2i(0, 800);
		glVertex2i(1200, 800);
		glVertex2i(1200, 0);
		glEnd();
	}

	public void mouseCheck() {
		if (Mouse.isButtonDown(0)) {
			// Check if button is clicked
			if (Mouse.getX() >= 1040 && Mouse.getX() <= 1180
					&& h - Mouse.getY() <= 130 && h - Mouse.getY() >= 50) {
				// sort by side length
				sort = "side length";
			}

			if (Mouse.getX() >= 1040 && Mouse.getX() <= 1180
					&& h - Mouse.getY() <= 230 && h - Mouse.getY() >= 150) {
				// sort by area
				sort = "area";

			}

			if (Mouse.getX() >= 1040 && Mouse.getX() <= 1180
					&& h - Mouse.getY() <= 330 && h - Mouse.getY() >= 250) {
				// sort by perimeter
				sort = "perimeter";

			}

			if (Mouse.getX() >= 1040 && Mouse.getX() <= 1180
					&& h - Mouse.getY() <= 430 && h - Mouse.getY() >= 350) {
				// sort by color
				sort = "color";

			}

			if (Mouse.getX() >= 1040 && Mouse.getX() <= 1180
					&& h - Mouse.getY() <= 530 && h - Mouse.getY() >= 450) {
				// sort by sides
				sort = "sides";

			}

			if (Mouse.getX() >= 1040 && Mouse.getX() <= 1180
					&& h - Mouse.getY() <= 630 && h - Mouse.getY() >= 550) {
				// exit
				Display.destroy();
				System.exit(0);
			}

		}
	}

	public void buttons() {
		glBegin(GL_LINES);
		for (int i = 1000; i < 1010; i++) {
			glColor3f(liner, lineb, lineg);
			glVertex2i(i, 0);
			glVertex2i(i, 800);
		}
		glEnd();

		glBegin(GL_QUADS);

		for (int i = 1; i < 12; i += 2) {
			glVertex2i(1040, 50 * i);
			glVertex2i(1180, 50 * i);
			glVertex2i(1180, (50 * i) + 80);
			glVertex2i(1040, (50 * i) + 80);

		}
		for (int i = 1; i < 12; i += 2) {
			glColor3f(0.3f, 0.3f, 0.3f);
			glVertex2i(1050, (50 * i) + 10);
			glVertex2i(1170, (50 * i) + 10);
			glVertex2i(1170, (50 * i) + 70);
			glVertex2i(1050, (50 * i) + 70);

		}

		glEnd();
		glColor3f(liner, lineb, lineg);
		DrawString.drawString("Side Length", 1050 + 20, 95);
		DrawString.drawString("Area", 1050 + 40, 195);
		DrawString.drawString("Perimeter", 1050 + 25, 295);
		DrawString.drawString("Color", 1050 + 40, 395);
		DrawString.drawString("Sides", 1050 + 40, 495);
		DrawString.drawString("Exit", 1050 + 40, 595);

		DrawString.drawString("Sorting by", 10, 12);
		DrawString.drawString(sort, 94, 12);
	}

}
