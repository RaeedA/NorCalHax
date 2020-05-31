package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchScreen extends Screen
{
    // GUI components
    private JPanel searchPanel;
    
    // Buttons
    private JButton searchButton;
    
    // TextFields
    private JTextField searchField;
    
    public SearchScreen()
    {
        super("Search Recipes");
        setComponents();
    }

    @Override
    protected void setComponents()
    {
        //GUI Components
        searchPanel = new JPanel();
        searchButton = new JButton("Search");
        searchField = new JTextField(20);
        
        
        searchPanel.add( searchField );
        searchPanel.add( searchButton);
        
        add(searchPanel, BorderLayout.CENTER );
        
        // Event Handlers for UI components
        searchButton.addActionListener( new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                showResults(searchField.getText());
            }
        });
        
        searchField.addKeyListener( new KeyListener()
        {
            @Override
            public void keyTyped( KeyEvent e ) {}

            @Override
            public void keyReleased( KeyEvent e )
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    showResults(searchField.getText());
                }
            }

            @Override
            public void keyPressed( KeyEvent e ) {}
        });
    }
    
    private void showResults(String search)
    {
        foodName = search;
        Screen result = new ResultScreen(foodName);
        setVisible(false);
        result.setVisible( true );
    }

    @Override
    protected void resetSearch()
    {
        searchField.setText( null );
    }
    
}
