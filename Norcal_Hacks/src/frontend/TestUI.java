package frontend;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestUI
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater( new Runnable() 
        {
            public void run()
            {
                JFrame frame = new TestFrame("test");
                frame.setVisible( true );
            }
        });
    }
}
