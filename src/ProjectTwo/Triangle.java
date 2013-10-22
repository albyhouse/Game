package ProjectTwo;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

public class Triangle {

	private int s;
	private int x, y;
	private float r, g, b;
	

	public Triangle(int x, int y, int s, float r, float g, float b) {
		this.x = x;
		this.y = y;
		this.s = s;
		this.r = r;
		this.b = b;
		this.g = g;

	}

	public void draw() {
		glColor3f(r, g, b);

		glBegin(GL_TRIANGLES);
		glVertex2d(x, y - (2 * ((s / 2) / Math.sqrt(3))));
		glVertex2d(x - (s / 2), y + (s / 2) / Math.sqrt(3));
		glVertex2d(x + (s / 2), y + (s / 2) / Math.sqrt(3));
		glEnd();

	}
	
	
	
	public double perimeter() {
		return s * 3;
	}
	
	public double area() {
		
		return ((Math.sqrt(3)* s)/2) * (.5 * s);
	}

	public int getS() {
		return s;
	}

	public void setS(int sideLength) {
		this.s = sideLength;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

}
