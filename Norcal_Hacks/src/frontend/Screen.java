package frontend;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
@SuppressWarnings("serial")
public abstract class Screen extends JFrame
{
    protected String foodName;
    protected int width;
    protected int height;
    public Screen(String title)
    {
        super(title);
        setUp();
    }
     
    protected void setUp()
    {
        
        // get computer dimensions and set frame size to half of it
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        width = screenWidth / 2;
        height = screenHeight / 2;
        
        setSize(width, height);
        
        // position frame in center of screen
        setLocationRelativeTo(null);
        // Setting frame behavior when user closes the Frame
        addWindowListener( new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit( 0 );
            }
        });
    }
    
    protected abstract void setComponents();
    protected abstract void resetSearch();
    
}
