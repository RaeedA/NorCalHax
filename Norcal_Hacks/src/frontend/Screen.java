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
        
        setSize(screenWidth/2, screenHeight/2);
        
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
    public String getFoodName()
    {
        return foodName;
    }
    
    protected abstract void setComponents();
    protected abstract void resetSearch();
    
}
