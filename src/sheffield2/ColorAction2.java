package sheffield2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorAction2 implements ActionListener
{

    public ColorAction2(Color c, JComponent comp)
    {
        theColor = c;
        theComponent = comp;
    }

    public void actionPerformed(ActionEvent e)
    {
        theComponent.setBackground(theColor);
    }

    private JComponent theComponent;
    private Color theColor;
}
