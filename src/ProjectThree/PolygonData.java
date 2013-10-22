package ProjectThree;

import java.awt.Color;

public class PolygonData {

	private int numSides, sideLength;
	private Color color;

	public PolygonData(int numSides, int sideLength, Color color) {
		this.numSides = numSides;
		this.sideLength = sideLength;
		this.color = color;

	}

	public double calculatePerimeter() {
		return numSides * sideLength;
	}

	public double calculateArea() {
		return ((sideLength * sideLength) * numSides)
				/ (4 * Math.tan(Math.PI / numSides));

	}

	public int getNumSides() {
		return numSides;
	}

	public void setNumSides(int numSides) {
		this.numSides = numSides;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
