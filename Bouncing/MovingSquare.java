/*
 *	Author: KHP
 */


import java.awt.Color;


public class MovingSquare extends MovingRectangle {
	
	public MovingSquare() { super(); }
	
	public MovingSquare(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, Path pathType) {
		super(x, y, Math.min(w, h), Math.min(w, h), mw, mh, bc, fc, pathType);
	}
	
	public void setWidth(int w) {
		width = w;
		height = w;
	}
	
	public void setHeight(int h) {
		height = h;
		width = h;
	}
	
}
