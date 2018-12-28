/*
 *	Author: KHP
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


public class MovingOval extends MovingRectangle {
	
	public MovingOval() { super(); }
	
	public MovingOval(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, Path pathType) {
		super(x, y, w, h, mw, mh, bc, fc, pathType);
	}
	
    public void drawHandles(Graphics g) {
        if (isSelected()) {
            g.setColor(Color.black);
            g.fillOval(x -2, y-2, 4, 4);
            g.fillOval(x + width -2, y + height -2, 4, 4);
            g.fillOval(x -2, y + height -2, 4, 4);
            g.fillOval(x + width -2, y-2, 4, 4);
        }
    }

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(fillColor);
		g2d.fillOval(x, y, width, height);
		g2d.setPaint(borderColor);
		g2d.drawOval(x, y, width, height);
		drawHandles(g);
	}

	public boolean contains(Point mousePt) {
		double dx, dy;
		Point EndPt = new Point(x + width, y + height);
		dx = (2 * mousePt.x - x - EndPt.x) / (double) width;
		dy = (2 * mousePt.y - y - EndPt.y) / (double) height;
		return dx * dx + dy * dy < 1.0;
	}
	
}
