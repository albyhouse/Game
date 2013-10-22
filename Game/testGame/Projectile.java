package testGame;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Projectile {

	private int x, y, radius;

	public Projectile(int x, int y, int radius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public void draw(int x, int y, double slope) {
		double r = radius;

		glColor3f(1.0f, 0.0f, 0.0f);

		glBegin(GL_LINE_LOOP);

		for (double i = 0; i < 6.28; i += .1) {
			glVertex2f((float) (x + (r * Math.PI * Math.cos(i))),
					(float) (y + (r * Math.PI * Math.sin(i))));
		}

		glEnd();

	}

}
