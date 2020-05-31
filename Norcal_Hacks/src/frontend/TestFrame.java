package frontend;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestFrame extends JFrame
{
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    
    public TestFrame(String title)
    {
        super(title);
        
        // get computer dimensions and set frame size to half of it
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        
        setSize(screenWidth/2, screenHeight/2);
        
        // position frame in center of screen
        setLocationRelativeTo(null);
        
        // GUI components
        JPanel panel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel displayPanel = new JPanel();
        JPanel anotherPanel = new JPanel();
        //searchPanel.setBackground(Color.ORANGE);
        searchPanel.add( new JTextField(20) );
        displayPanel.add( new JTextArea());
        anotherPanel.add(new JTextArea());
        
        
        panel.add(searchPanel, BorderLayout.NORTH );
        panel.add(displayPanel, BorderLayout.CENTER);
        panel.add(anotherPanel, BorderLayout.SOUTH);
        panel.setBackground( Color.ORANGE );
        add(panel);
        
        // Setting frame behavior when user closes the Frame
        addWindowListener( new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit( 0 );
            }
        });
    }
}
