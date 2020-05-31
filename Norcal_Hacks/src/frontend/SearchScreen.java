package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import backend.CookBook;

public class SearchScreen extends JFrame
{
    private JPanel searchPanel;
    private JButton searchButton;
    private JTextField searchField;
    private CookBook book;
    
    public SearchScreen()
    {
        super("Search");
        setComponents();
        
        book = new CookBook();
        
        //Wrap up
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo( null );
        pack();
        setVisible(true);
    }

    private void setComponents()
    {
        //GUI Components
        searchPanel = new JPanel();
        searchPanel.setLayout( new BorderLayout() );
        
        searchButton = new JButton("Find Recipe!");
        searchPanel.add( searchButton, BorderLayout.EAST);
        
        searchField = new JTextField(20);
        searchField.setSize( 30 ,20 );
        searchPanel.add( searchField, BorderLayout.CENTER );
        
        add(searchPanel);
        
        // Event Handlers for UI components
        searchButton.addActionListener( new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                showResults(searchField.getText());
            }
        }
        );
        
        searchField.addKeyListener( new KeyListener()
        {
            @Override
            public void keyTyped( KeyEvent e ) {}


            @Override
            public void keyPressed( KeyEvent e ) {}

            @Override
            public void keyReleased( KeyEvent e )
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    showResults(searchField.getText());
                }
            }
        }
        );
    }
    
    private void showResults(String search)
    {
        book.getLinks( search );
        setVisible(false);
        LoadingScreen screen = new LoadingScreen(book);
    }
}
