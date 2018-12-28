/*
 *	Author: KHP
 */


import java.awt.*;


public class MovingRectangle extends MovingShape {

	public MovingRectangle() {
		this(10, 20, 50, 20, 500, 500, Color.orange, Color.yellow, Path.FALLING);
	}

	public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, Path pathType) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		marginWidth = mw;
		marginHeight = mh;
		borderColor = bc;
		fillColor = fc;
        setPath(pathType);
	}

    public void drawHandles(Graphics g) {
        if (isSelected()) {
            g.setColor(Color.black);
            g.fillRect(x -2, y-2, 4, 4);
            g.fillRect(x + width -2, y + height -2, 4, 4);
            g.fillRect(x -2, y + height -2, 4, 4);
            g.fillRect(x + width -2, y-2, 4, 4);
        }
    }

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(fillColor);
		g2d.fillRect(x, y, width, height);
		g2d.setPaint(borderColor);
		g2d.drawRect(x, y, width, height);
		drawHandles(g);
	}

	public boolean contains(Point mousePt) {
		return (x <= mousePt.x && mousePt.x <= (x + width + 1)	&&	y <= mousePt.y && mousePt.y <= (y + height + 1));
	}
	
}