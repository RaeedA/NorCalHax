package frontend;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import backend.CookBook;

public class LoadingScreen extends JFrame
{
    private JProgressBar bar;
    private CookBook book;
    
    public LoadingScreen(CookBook b)
    {
        super("Loading!");
        book = b;
        
        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout(panel, BoxLayout.PAGE_AXIS) );
        JLabel label = new JLabel("loading...");
        panel.add( label);
        bar = book.getBar();
        panel.add( bar );
        add(panel);
        
        book.startRunnerThread();

        //Wrap up
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo( null );
        setVisible(true);
    }
    
    public void finish()
    {
        ResultScreen result = new ResultScreen(book);
    }
}
