package ProjectThree;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import java.awt.*;

public class Polygon {

	public static double area(double s, double n) {
		return ((s * s) * n) / (4 * Math.tan(Math.PI / n));
	}

	public static double perimeter(double s, double n) {
		return s * n;
	}

	public static void draw(double s, int n, int x, int y, Color color) {

		double radius = s / (2 * Math.sin(180 / n));
		double angleAdd = 360.0 / n;
		double angle = (180 / 2) + (180 / n);

		float r = (float) (color.getRed() / 255.0);
		float g = (float) (color.getBlue() / 255.0);
		float b = (float) (color.getGreen() / 255.0);
		glColor3f(r, g, b);
		
		glBegin(GL_POLYGON);

		for (int i = 0; i < n + 1; i++) {
			double xcoord = x + radius * Math.cos(Math.toRadians(angle));
			double ycoord = y + radius * Math.sin(Math.toRadians(angle));

			glVertex2d(xcoord, ycoord);
			angle += angleAdd;

		}

		glEnd();

	}

}
