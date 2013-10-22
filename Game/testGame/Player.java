package testGame;

import org.lwjgl.opengl.GL11;

public class Player {

	private int radius;

	public Player(int radius) {
		this.radius = radius;
	}

	public void draw(int x, int y) {

		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glScalef(radius, radius, 1);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for (int i = 0; i <= 100; i++) {

			double angle = Math.PI * 2 * i / 100;
			GL11.glVertex2f((float) Math.cos(angle), (float) Math.sin(angle));
		}

		GL11.glEnd();

		GL11.glPopMatrix();

	}

}
