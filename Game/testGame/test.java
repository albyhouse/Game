package testGame;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class test {

	private int w = 1200;
	private int h = 800;

	private int playerX = 100;
	private int playerY = 100;
	private int movement = 4;

	private Player player;

	public test() {
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
			keyCheck();
			aim();
			glColor3f(1.0f, 1.0f, 1.0f);
			
			player = new Player(13);
			player.draw(playerX, playerY);

			Display.update();
			Display.sync(60);
		}

		Display.destroy();

	}

	private void keyCheck() {
		if (Mouse.isButtonDown(0)) {
			// Projectile
			
			double m = //slope
			
			Projectile projectile = new Projectile(playerX, playerY, m) 

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			movement = 7;

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			if (playerY > 12) {
				playerY -= movement;
			}

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			if (playerY < 788) {
				playerY += movement;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			if (playerX < 1188) {
				playerX += movement;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			if (playerX > 12) {
				playerX -= movement;
			}
		}
		movement = 4;
	}

	private void aim() {
		glBegin(GL_LINES);
		glColor3f(1.0f, 0.0f, 0.0f);
		glVertex2i(playerX, playerY);
		glVertex2i(Mouse.getX(), h - Mouse.getY());
		glEnd();
	}

	private void bg() {
		glColor3f(0.5f, 0.5f, 0.5f);
		glBegin(GL_QUADS);
		glVertex2i(0, 0);
		glVertex2i(0, 800);
		glVertex2i(1200, 800);
		glVertex2i(1200, 0);
		glEnd();

	}

	public static void main(String[] args) {

		new test();

	}
}