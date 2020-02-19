package sheffield2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonPanel6 extends JPanel
{

    // use Action object

    public ButtonPanel6()
    {
        makeButton("green", new ImageIcon("sheffield2/greenball.gif"), Color.GREEN);
        makeButton("red", new ImageIcon("sheffield2/redball.gif"), Color.RED);
        makeButton("blue", new ImageIcon("sheffield2/blueball.gif"), Color.BLUE);
    }
    

    private void makeButton(String name, Icon icon, Color col)
    {
        JButton button = new JButton(new ColorAction(name, icon, col));
        add(button);
    }

    private class ColorAction extends AbstractAction
    {
        public ColorAction(String name, Icon icon, Color col)
        {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue("colour", col);
        }
        public void actionPerformed(ActionEvent ae)
        {
            setBackground((Color) getValue("colour"));
        }
    }

    public static void main(String[] args)
    {
        JFrame cf = new CentreFrame();
        JPanel bp = new ButtonPanel6();
        cf.add(bp);
        cf.setVisible(true);
    }

}
