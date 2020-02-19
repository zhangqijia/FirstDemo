package sheffield2; /**
   @version 1.30 2000-06-03
   @author Cay Horstmann
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class MouseTest
{
    public static void main(String[] args)
    {
        MouseFrame frame = new MouseFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * A frame containing a panel for testing mouse operations
 */
class MouseFrame extends JFrame
{
    public MouseFrame()
    {
        setTitle("MouseTest");
        setSize(300, 200);

        // add panel to frame

        MousePanel panel = new MousePanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);
    }

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
}

/**
 * A panel with mouse operations for adding and removing squares.
 */
class MousePanel extends JPanel
{
    public MousePanel()
    {
        squares = new ArrayList();
        current = null;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // draw all squares
        for (int i = 0; i < squares.size(); i++)
            g2.draw((Rectangle2D) squares.get(i));
    }

    /**
     * Finds the first square containing a point.
     * 
     * @param p
     *            a point
     * @return the index of the first square that contains p
     */
    public Rectangle2D find(Point2D p)
    {
        for (int i = 0; i < squares.size(); i++)
        {
            Rectangle2D r = (Rectangle2D) squares.get(i);
            if (r.contains(p))
                return r;
        }

        return null;
    }

    /**
     * Adds a square to the collection.
     * 
     * @param p
     *            the center of the square
     */
    public void add(Point2D p)
    {
        double x = p.getX();
        double y = p.getY();

        current = new Rectangle2D.Double(x - SIDELENGTH / 2,
                y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
        squares.add(current);
        repaint();
    }

    /**
     * Removes a square from the collection.
     * 
     * @param s
     *            the square to remove
     */
    public void remove(Rectangle2D s)
    {
        if (s == null)
            return;
        if (s == current)
            current = null;
        squares.remove(s);
        repaint();
    }

    private static final int SIDELENGTH = 10;
    private ArrayList squares;
    private Rectangle2D current;
    // the square containing the mouse cursor

    private class MouseHandler extends MouseAdapter
    {
        public void mousePressed(MouseEvent event)
        {
            // add a new square if the cursor isn't inside a square
            current = find(event.getPoint());
            if (current == null)
                add(event.getPoint());
        }

        public void mouseClicked(MouseEvent event)
        {
            // remove the current square if double clicked
            current = find(event.getPoint());
            if (current != null && event.getClickCount() >= 2)
                remove(current);
        }
    }

    private class MouseMotionHandler implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent event)
        {
            // set the mouse cursor to cross hairs if it is inside
            // a rectangle

            if (find(event.getPoint()) == null)
                setCursor(Cursor.getDefaultCursor());
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }

        public void mouseDragged(MouseEvent event)
        {
            if (current != null)
            {
                int x = event.getX();
                int y = event.getY();

                // drag the current rectangle to center it at (x, y)
                current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2,
                        SIDELENGTH, SIDELENGTH);
                repaint();
            }
        }
    }
}
