package Classes;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import ProjectThree.DrawString;

public class visualsort {

	private int w = 1200;
	private int h = 800;

	private static boolean check = true;
	private static int[] list = new int[240];

	public visualsort() {
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
		Keyboard.enableRepeatEvents(true);
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);

			int x = 0;
			int y = 0;

			for (int i = 0; i < list.length; i++) {

				DrawString
						.drawString(String.valueOf(list[i]), 100 + x, 100 + y);

				if ((i + 1) % 20 == 0 && i != 0) {
					y += 50;
					x = -50;
				}

				x += 50;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)
					&& !Keyboard.isRepeatEvent()) {
				check = true;
				// sort(list);

			}

			if (check) {
				bubbleSort(list);
			}

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	public static void bubbleSort(int[] a) {

		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {

				if (a[i] > a[j]) {// switch a & j
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					// Display.update();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;

				}

			}

		}

	}

	public static void main(String[] args) {
		if (check) {
			for (int i = 0; i < list.length; i++) {

				list[i] = ((int) (Math.random() * 1000));

			}
			check = false;
		}
		new visualsort();

	}
}