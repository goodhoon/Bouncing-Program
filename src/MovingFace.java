/*
 *	Author: KHP
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class MovingFace extends MovingOval {
	
	public MovingFace() { super(); }
	
	public MovingFace(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, Path pathType) {
		super(x, y, w, h, mw, mh, bc, fc, pathType);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(fillColor);
		g2d.fillOval(x, y, width, height);
		
		g2d.drawOval(x+width/5, y+height/5, width/5, height/5);
		g2d.drawOval(x+(width*3/5), y+(height/5), width/5, height/5);
		g2d.setColor(Color.black);
		g2d.drawArc(x+(width/5), y+(height*2/6), width*3/5, height/2, 200, 140);
		g2d.fillOval(x+width/5, y+height/5, width/5, height/5);
		g2d.fillOval(x+(width*3/5), y+ (height/5), width/5, height/5);

		g2d.setPaint(borderColor);
		g2d.drawOval(x, y, width, height);
		
		drawHandles(g);
	}

}