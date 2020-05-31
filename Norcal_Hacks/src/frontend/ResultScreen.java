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
        GridLayout ingredientsLayout = new GridLayout(ingredients.size() + 1, 2);
        ingredientsLayout.setHgap( 25 );
        JPanel ingredientsPanel = new JPanel(ingredientsLayout);
        JPanel instructionsPanel = new JPanel();
        
        // Text Area
        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setText( "Hello" );
        
        // Labels
        JLabel ingredientsLabel = new JLabel("Ingredients");
        //label.setT;
        instructionsPanel.add( makeLabel("Instructions: ", JLabel.RIGHT, JLabel.CENTER) );
        instructionsPanel.add( instructionsArea );
        
        ingredientsPanel.add( makeLabel("Ingredients: ", JLabel.RIGHT, JLabel.CENTER ));
        ingredientsPanel.add( new JLabel() );
        for(int i = 0; i < ingredients.size(); i++)
        {
            ingredientsPanel.add( makeLabel(ingredients.get( i ), JLabel.RIGHT, JLabel.CENTER ) );
            ingredientsPanel.add( new JLabel("0"));
        }
        
        add(ingredientsPanel, BorderLayout.WEST );
        add(instructionsPanel, BorderLayout.EAST );
        // Event Handlers for UI components
    }

    @Override
    protected void resetSearch()
    {
        // TODO Auto-generated method stub
        
    }
    private JLabel makeLabel( String text, int horizPosition, int vertPosition)
    {
        JLabel label = new JLabel();
        if(text != null)
        {
            label.setText( text );
        }
        label.setHorizontalAlignment( horizPosition );
        label.setVerticalAlignment( vertPosition );
        return label;
    }
    
    

}
