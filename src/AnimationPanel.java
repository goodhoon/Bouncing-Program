/*
 *	Author: KHP
 */


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.util.ArrayList;


public class AnimationPanel extends JComponent implements Runnable {
    private Thread animationThread = null;
 
    private ArrayList<MovingShape> shapes;
    private int currentWidth=100, currentHeight=50, currentShapeType;
    private Path currentPath=Path.BOUNCING;
    private Color currentBorderColor = Color.orange;
    private Color currentFillColor = Color.yellow;
    private int delay = 30;
    JPopupMenu popup;

    public AnimationPanel() {
		shapes = new ArrayList<MovingShape>();
        Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom;
        popup = new JPopupMenu();
        makePopupMenu();
		addMouseListener( new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				maybeShowPopup(e);
			}
			public void mouseReleased(MouseEvent e) {
				maybeShowPopup(e);
			}
			private void maybeShowPopup(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
            public void mouseClicked( MouseEvent e ) {
                if (animationThread != null) { 
                	boolean found = false;
                    for (MovingShape s : shapes) {
                        if ( s.contains( e.getPoint()) ) {
                            s.setSelected( ! s.isSelected() );
                            found = true;
						}
					}
					if (!found) createNewShape(e.getX(), e.getY());
	    		}
			}

	    });
	}

    protected void createNewShape(int x, int y) {
        Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom;
        switch (currentShapeType) {
            case 0: {
                shapes.add(new MovingRectangle(x, y, currentWidth,	currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
                break;                
            }
            case 1: {
            	shapes.add(new MovingSquare(x, y, currentWidth,	currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
                break;   
            }
            case 2: {
            	shapes.add(new MovingOval(x, y, currentWidth,	currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
                break;   
            }
            case 3: {
            	shapes.add(new MovingFace(x, y, currentWidth,	currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
                break;  
            }
        }
    }

    public void setCurrentShapeType(int s) {
        currentShapeType = s;
    }

    public void setCurrentPathType(int index) {
        currentPath = Path.values()[index];
 		for (MovingShape s : shapes)
			if ( s.isSelected())
				s.setPath(currentPath);
    }

	public int getCurrentWidth() {
		return currentWidth;
	}

	public int getCurrentHeight() {
		return currentHeight;
	}

	public void setCurrentWidth(int w) {
		currentWidth = w;
		for (MovingShape s : shapes)
			if ( s.isSelected())
				s.setWidth(currentWidth);
	}

	public void setCurrentHeight(int h) {
		currentHeight = h;
		for (MovingShape s : shapes)
			if ( s.isSelected())
				s.setHeight(currentHeight);
	}

	public Color getCurrentBorderColor() {
		return currentBorderColor;
	}

	public Color getCurrentFillColor() {
		return currentFillColor;
	}	
	
	public void setCurrentBorderColor(Color bc) {
		currentBorderColor = bc;
		for (MovingShape s : shapes)
			if ( s.isSelected())
				s.setBorderColor(currentBorderColor);
	}

    public void setCurrentFillColor(Color fc) {
        currentFillColor = fc;
		for (MovingShape s : shapes)
			if ( s.isSelected())
				s.setFillColor(currentFillColor);
    }

    public void resetMarginSize() {
        Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom ;
		for (MovingShape s : shapes)
			s.setMarginSize(marginWidth,marginHeight );
    }

    public void clearAllShapes() {
    	shapes.clear();
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paintComponent(Graphics g) {
        for (MovingShape s : shapes) {
            s.move();
		    s.draw(g);
		}
    }

    protected void makePopupMenu() {
        JMenuItem menuItem;
        menuItem = new JMenuItem("Clear All");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAllShapes();
            }
        });
        popup.add(menuItem);
     }

    public void adjustSpeed(int newValue) {
        if (animationThread != null) {
            stop();
            delay = newValue;
            start();
        }
    }

    public void start() {
        animationThread = new Thread(this);
        animationThread.start();
    }

    public void stop() {
        if (animationThread != null) {
            animationThread = null;
        }
    }

    public void run() {
        Thread myThread = Thread.currentThread();
        while(animationThread==myThread) {
            repaint();
            pause(delay);
        }
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep((long)milliseconds);
        } catch(InterruptedException ie) {}
    }
}
