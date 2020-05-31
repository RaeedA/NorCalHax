package frontend;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class ResultScreen extends Screen
{
    public ResultScreen(String foodToSearch)
    {
        super("Recipe for " + foodToSearch);
        foodName = foodToSearch;
    }

    @Override
    protected void setComponents()
    {
        //temp variables
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add( "eggs" );
        ingredients.add( "milk" );
        ingredients.add( "butter" );
        // GUI components
        GridLayout ingredientsLayout = new GridLayout(ingredients.size(), 2);
        ingredientsLayout.setHgap( 25 );
        JPanel ingredientsPanel = new JPanel(ingredientsLayout);
        
        // Buttons
        JButton searchButton = new JButton("Search");
        
        // TextFields
        JTextField searchField = new JTextField(20);
        
        
        //searchPanel.add( searchField );
        //searchPanel.add( searchButton);
        for(int i = 0; i < ingredients.size(); i++)
        {
            ingredientsPanel.add( new JLabel(ingredients.get( i )) );
            ingredientsPanel.add( new JLabel("0"));
        }
        
        add(ingredientsPanel, BorderLayout.WEST );
        
        // Event Handlers for UI components
    }

    @Override
    protected void resetSearch()
    {
        // TODO Auto-generated method stub
        
    }
    
    

}
